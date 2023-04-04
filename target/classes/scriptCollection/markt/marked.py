import requests

def get_stock_price(ticker_symbol):
    response = requests.get('https://api.iextrading.com/1.0/stock/' + ticker_symbol + '/price')
    if response.status_code == 200:
        return response.json()
    else:
        return None

def get_crypto_price(ticker_symbol):
    response = requests.get('https://api.coinmarketcap.com/v1/ticker/' + ticker_symbol + '/')
    if response.status_code == 200:
        json_data = response.json()
        return float(json_data[0]['price_usd'])
    else:
        return None

def get_gold_price():
    response = requests.get('https://www.goldapi.io/api/XAU/USD')
    if response.status_code == 200:
        json_data = response.json()
        return float(json_data['price'])
    else:
        return None

def get_silver_price():
    response = requests.get('https://www.goldapi.io/api/XAG/USD')
    if response.status_code == 200:
        json_data = response.json()
        return float(json_data['price'])
    else:
        return None

def get_nft_price(nft_name):
    response = requests.get('https://api.opensea.io/api/v1/assets?order_direction=desc&offset=0&limit=1&search[query]=' + nft_name)
    if response.status_code == 200:
        json_data = response.json()
        return float(json_data['assets'][0]['last_sale']['total_price'])
    else:
        return None

ticker_symbol = input("Enter stock symbol: ")
stock_price = get_stock_price(ticker_symbol)
if stock_price is not None:
    print("Stock price: $" + str(stock_price))
else:
    print("Invalid stock symbol")

crypto_symbol = input("Enter cryptocurrency symbol: ")
crypto_price = get_crypto_price(crypto_symbol)
if crypto_price is not None:
    print("Cryptocurrency price: $" + str(crypto_price))
else:
    print("Invalid cryptocurrency symbol")

gold_price = get_gold_price()
if gold_price is not None:
    print("Gold price: $" + str(gold_price))
else:
    print("Failed to get gold price")

silver_price = get_silver_price()
if silver_price is not None:
    print("Silver price: $" + str(silver_price))
else:
    print("Failed to get silver price")

nft_name = input("Enter NFT name: ")
nft_price = get_nft_price(nft_name)
if nft_price is not None:
    print("NFT price: $" + str(nft_price))
else:
    print("Failed to get NFT price")
