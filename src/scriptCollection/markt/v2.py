import requests
import json
import time
from datetime import datetime

from marked import get_gold_price, get_silver_price

# Define API keys
stock_api_key = "5EMUIJRYVXR303GJ"
crypto_api_key = "9328916b-f88f-49c8-84f1-dcde69021646"
nft_api_key = "YOUR_NFT_API_KEY_HERE"

# API urls
stock_api_url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=" #"https://financialmodelingprep.com/api/v3/quote-short/" "https://cloud.iexapis.com/stable/stock/"
crypto_api_url = "https://api.nomics.com/v1/currencies/ticker"
nft_api_url = "https://api.opensea.io/api/v1/assets"

# Symbols and names
# Define stock symbols
#stock_symbols = ["AAPL", "MSFT", "AMZN", "GOOG", "FB", "TSLA"]
# Define cryptocurrency symbols
#crypto_symbols = ["BTC", "ETH", "XRP", "LTC", "BCH", "ETH-USD", "LTC-USD", "DOGE-USD", "ADA-USD" ]
# Define NFT names
#nft_names = ["CryptoKitties", "Axie Infinity", "The Sandbox", "Decentraland", "My Crypto Heroes","Legendary Sword of the Dragon", "Pixelated Panda", "CryptoKitties", "CyberPunk2021", "MoonCat"]
# dictionary for symbols and names
symbol_dict = {
    'Gold': 'XAU',
    'Silver': 'XAG',
    'Apple Inc.': 'AAPL',
    'Tesla Inc.': 'TSLA',
    'Bitcoin': 'BTC',
    'Ethereum': 'ETH',
    'CryptoPunk': 'PUNK',
    'Bored Ape Yacht Club': 'BAYC'
}

# function to calculate market conditions
def calculate_market_conditions():
    symbol = input("Enter the symbol/name of the asset: ")
    if symbol in symbol_dict:
        symbol = symbol_dict[symbol]
    else:
        print("Sorry, this asset is not supported.")
        return
    
    price = float(input("Enter the current price: "))
    prev_close = float(input("Enter the previous close price: "))
    change = (price - prev_close) / prev_close * 100
    print("Change: {:.2f}%".format(change))

    if change >= 5:
        print("Market is bullish.")
    elif change <= -5:
        print("Market is bearish.")
    else:
        print("Market is neutral.")

# Functions to get prices
def get_stock_price(symbol):
    url = stock_api_url + symbol + "/quote?token=" + stock_api_key #&apikey=
    response = requests.get(url)
    if response.status_code == 200:
        data = json.loads(response.content)
        if "latestPrice" in data:
            return data["latestPrice"]
    return "Failed to get stock price"

def get_crypto_price(symbol):
    url = crypto_api_url + "?key=" + crypto_api_key + "&ids=" + symbol
    response = requests.get(url)
    if response.status_code == 200:
        data = json.loads(response.content)[0]
        if "price" in data:
            return data["price"]
    return "Failed to get cryptocurrency price"

def get_nft_price(name):
    url = nft_api_url + "?format=json&limit=1&search[query]=" + name
    headers = {"X-API-KEY": nft_api_key}
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        data = json.loads(response.content)["assets"]
        if len(data) > 0 and "last_sale" in data[0]:
            return data[0]["last_sale"]["payment_token"]["usd_price"]
    return "Failed to get NFT price"

# Get user input
symbol = input("Enter stock symbol: ")
price = get_stock_price(symbol)
if price == "Failed to get stock price":
    symbol = input("Invalid stock symbol\nEnter cryptocurrency symbol: ")
    price = get_crypto_price(symbol)
    if price == "Failed to get cryptocurrency price":
        print("Invalid cryptocurrency symbol")
else:
    print("Current " + symbol + " stock price: $" + str(price))

nft_name = input("Enter NFT name: ")
nft_price = get_nft_price(nft_name)
if nft_price == "Failed to get NFT price":
    print("Failed to get NFT price")
else:
    print("Current " + nft_name + " NFT price: $" + str(nft_price))

def get_gold_price():
    url = "https://www.goldapi.io/api/XAU/USD"
    headers = {"x-access-token": "goldapi-3q6rle8h0t0i-io"}
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        data = json.loads(response.content)["price"]
        return data
    return "Failed to get gold price"

def get_silver_price():
    url = "https://www.goldapi.io/api/XAG/USD"
    headers = {"x-access-token": "goldapi-3q6rle8h0t0i-io"}
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        data = json.loads(response.content)["price"]
        return data
    return "Failed to get silver price"

# Function to write information to file
def write_info_to_file(name, price, recommendation):
    with open(name + ".txt", "a") as f:
        f.write("Price: " + str(price) + "\n")
        f.write("Recommendation: " + recommendation + "\n\n")


# Market analysis
while True:
    # Get stock prices
    for symbol in symbol_dict:
        price = get_stock_price(symbol)
        if price != "Failed to get stock price":
            if float(price) < 100:
                write_info_to_file(symbol, price, "buy")
            elif float(price) > 200:
                write_info_to_file(symbol, price, "sell")

    # Get cryptocurrency prices
    for symbol in symbol_dict:
        price = get_crypto_price(symbol)
        if price != "Failed to get cryptocurrency price":
            try:
                price = float(price)
                if price < 5000:
                    write_info_to_file(symbol, price, "buy")
                elif price > 10000:
                    write_info_to_file(symbol, price, "sell")
            except ValueError:
                print("Invalid cryptocurrency price for symbol", symbol)

    # Get NFT prices
    for name in symbol_dict:
        price = get_nft_price(name)
        if price != "Failed to get NFT price":
            if float(price) < 1000:
                write_info_to_file(name, price, "buy")
            elif float(price) > 5000:
                write_info_to_file(name, price, "sell")

    # Get gold and silver prices
    gold_price = get_gold_price()
    if gold_price != "Failed to get gold price":
        if float(gold_price) < 1500:
            write_info_to_file("Gold", gold_price, "buy")
        elif float(gold_price) > 2000:
            write_info_to_file("Gold", gold_price, "sell")

    silver_price = get_silver_price()
    if silver_price != "Failed to get silver price":
        if float(silver_price) < 20:
            write_info_to_file("Silver", silver_price, "buy")
        elif float(silver_price) > 30:
            write_info_to_file("Silver", silver_price, "sell")

    print("Market analysis complete. Check output files for recommendations.")
    time.sleep(3600) # Wait for one hour before doing the analysis again
