import json
import requests
import time

STOCK_API_URL = 'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&interval=5min&symbol='
STOCK_API_KEY = 'F0Y42VVFXCCETRLR'
CRYPTO_API_URL = 'https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_INTRADAY&symbol='
CRYPTO_API_KEY = 'F0Y42VVFXCCETRLR'

def get_crypto_price(symbol):
    url = CRYPTO_API_URL + symbol + '&market=USD&apikey=' + CRYPTO_API_KEY
    response = requests.get(url)
    if response.status_code == 200:
        data = json.loads(response.content)
        latest_data = data['TIME_SERIES_INTRADAY'][list(data['TIME_SERIES_INTRADAY'].keys())[0]]
        return float(latest_data['4b. close (USD)'])
    else:
        print('Failed to get crypto price')

def get_crypto_statistics(symbol):
    url = CRYPTO_API_URL + symbol + '&market=USD&apikey=' + CRYPTO_API_KEY
    response = requests.get(url)
    if response.status_code == 200:
        data = json.loads(response.content)
        close_prices = [float(v['4b. close (USD)']) for k, v in data['TIME_SERIES_INTRADAY'].items()]
        sma50 = sum(close_prices[-50:]) / 50
        sma200 = sum(close_prices[-200:]) / 200
        rsi = 100 - (100 / (1 + (sum([1 for p in close_prices[-14:] if p > close_prices[-15]]) / 14)))
        return {'sma50': sma50, 'sma200': sma200, 'rsi': rsi}
    else:
        print('Failed to get crypto statistics')

def get_precious_metal_price(symbol):
    url = STOCK_API_URL + symbol + "&apikey=" + STOCK_API_KEY
    response = requests.get(url)
    if response.status_code == 200:
        data = json.loads(response.content)
        print(data)
        latest_data = data['Time Series (Digital Currency Intraday)'][list(data['Time Series (Digital Currency Intraday)'].keys())[0]]
        return float(latest_data['4. close'])
    else:
        print("Failed to get precious metal price")
        

def get_precious_metal_statistics(symbol):
    url = STOCK_API_URL + symbol + "&apikey=" + STOCK_API_KEY
    response = requests.get(url)
    if response.status_code == 200:
        data = json.loads(response.content)
        close_prices = [float(v['4. close']) for k, v in data['Time Series (Digital Currency Intraday)'].items()]
        sma50 = sum(close_prices[-50:]) / 50
        sma200 = sum(close_prices[-200:]) / 200
        rsi = 100 - (100 / (1 + (sum([1 for p in close_prices[-14:] if p > close_prices[-15]]) / 14)))
        return {'sma50': sma50, 'sma200': sma200, 'rsi': rsi}
    else:
        print("Failed to get precious metal statistics")

get_precious_metal_price('XAUUSD')
get_precious_metal_statistics('XAUUSD')

def save_entry_exit_points(entry, exit):
    with open('trading_points.txt', 'a') as file:
        file.write('Entry Point: {}\n'.format(entry))
        file.write('Exit Point: {}\n'.format(exit))

def run_analysis():
    while True:
        precious_metal_price = get_precious_metal_price('XAUUSD')
        precious_metal_statistics = get_precious_metal_statistics('XAUUSD')
        crypto_price = get_crypto_price('BTC')
        crypto_statistics = get_crypto_statistics('BTC')
        print('Precious Metal Price: ${}'.format(precious_metal_price))
        print('Precious Metal Statistics: {}'.format(precious_metal_statistics))
        print('Crypto Price: ${}'.format(crypto_price))
        print('Crypto Statistics: {}'.format(crypto_statistics))
        if precious_metal_statistics['sma50'] > precious_metal_statistics['sma200'] and crypto_statistics['rsi'] < 30:
            save_entry_exit_points('Buy Precious Metal and Crypto', 'Sell Precious Metal and Crypto')
        elif precious_metal_statistics['sma50'] < precious_metal_statistics['sma200'] and crypto_statistics['rsi'] > 70:
            save_entry_exit_points('Sell Precious Metal and Crypto', 'Buy Precious Metal and Crypto')
time.sleep(300) # Run analysis every 5 minutes

run_analysis()
