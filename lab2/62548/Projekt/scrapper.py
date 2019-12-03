import requests as requests
from bs4 import BeautifulSoup as bs
import time as time
import json as json
import base64 as base64
import enum as enum


def getMaxPagesInput():
    print('Insert max number of pages you want to check. If you want check all pages then write large number')
    value = input()
    # fix this while
    while not value.isnumeric():
        print("Please enter a number")
        value = input()
    return int(value)


def getInput(list, types, type):
    print('Available', types, ':')
    for element in list:
        print('\t', element)
    print('Write your', type, ':')
    print('If you don\'t want to select ' + type + ' write \'None\'')
    name = input()
    list.append("None")
    while name not in list:
        print ("Write correct", type, ':')
        name = input()
    if name == "None":
        name = ''
    return name


def getListAvaibleBrandsAndModels(url, name):
    brandList = []
    mainPage = requests.get(startPage)
    page = bs(mainPage.content, 'html.parser')
    json_data = page.find('section', class_="om-offers-list").get("data-facets")
    loaded_json = json.loads(json_data)
    filtr = 'filter_enum_' + name
    for element in loaded_json[filtr]:
        if element == element.lower():
            brandList.append(element.capitalize())
    return brandList


def inputPriceRange(url):
    print("Input price range")
    print("From:")
    priceFrom = input()
    while not priceFrom.isnumeric():
        print("Enter a correct number")
        priceFrom = input()
    print("To:")
    priceTo = input()
    while (not priceTo.isnumeric()) or int(priceFrom) > int(priceTo):
        print("Enter a correct number")
        priceTo = input()
    filterPriceFrom = '?search%5Bfilter_float_price%3Afrom%5D='
    filterPriceTo = '&search%5Bfilter_float_price%3Ato%5D='

    urlList = []
    urlList.append(url)
    urlList.append(filterPriceFrom)
    urlList.append(priceFrom)
    urlList.append(filterPriceTo)
    urlList.append(priceTo)
    return ''.join(urlList)


def inputMileageRange(url):
    print("Input mileage range")
    print("From:")
    mileageFrom = input()
    while not mileageFrom.isnumeric():
        print("Enter a correct number")
        mileageFrom = input()
    print("To:")
    mileageTo = input()
    while (not mileageTo.isnumeric()) or int(mileageFrom) > int(mileageTo):
        print("Enter a correct number")
        mileageTo = input()
    filterMileageFrom = '&search%5Bfilter_float_mileage%3Afrom%5D='
    filterMileageTo = '&search%5Bfilter_float_mileage%3Ato%5D='

    urlList = []
    urlList.append(url)
    urlList.append(filterMileageFrom)
    urlList.append(mileageFrom)
    urlList.append(filterMileageTo)
    urlList.append(mileageTo)
    return ''.join(urlList)


def str_append(start, end):
    list = []
    list.append(start)
    list.append(end.lower())
    list.append('/')
    return ''.join(list)


def getAsBase64(url):
    if url == 'No image found':
        return ''
    else:
        return base64.b64encode(requests.get(url).content).decode('utf-8')


def getFuelType(url):
    fuelList = []
    print("Input type of fuel")
    print("Available types fo fuel")
    for fuel in FuelType:
        print('\t', fuel.name)
        fuelList.append(fuel.name)
    fuelType = input()
    while fuelType not in fuelList:
        print("Wrong type of fuel")
        fuelType = input()
    urlList = []
    defaultFuelLink = '&search%5Bfilter_enum_fuel_type%5D%5B0%5D='
    urlList.append(url)
    urlList.append(defaultFuelLink)
    urlList.append(fuelType)
    return ''.join(urlList)


class FuelType(enum.Enum):
    petrol = 1
    diesel = 2
    electric = 3
    etanol = 4
    hybrid = 5
    hidrogen = 6


class Scrapper:

    def __init__(self, maxPages):
        self.maxPages = maxPages
        self.pageCounter = 0
        self.JsonList = []

    def addToJson(self, title, subtitle, image, year, mileage, engine_capacity, fuel_type, price, link):
        row = {"title": title, "subtitle": subtitle, "image": getAsBase64(image), "imageUrl": image, "year": year,
               "mileage": mileage, "engine_capacity": engine_capacity, "fuel_type": fuel_type, "price": price,
               "link": link}
        self.JsonList.append(row)

    def saveJson(self):
        print("saveJson")
        with open("otomoto.json", "w") as write_file:
            json.dump(self.JsonList, write_file)
        write_file.close()

    def getCarsListFromPage(self, page):
        self.pageCounter = self.pageCounter + 1
        carsList = page.findAll('article', attrs={"data-test": "search-result-item"})
        for car in carsList:
            # Offer attribute
            name = car.find(class_="offer-title").find('a').get('title')
            print(name)
            subtitle = ''
            try:
                subtitle = car.find('h3', class_="offer-item__subtitle ds-title-complement").text
            except:
                subtitle = 'No info about subtitle'
            print(subtitle)
            imageUrl = ''
            try:
                imageUrl = car.find('img').get('data-src')
            except:
                imageUrl = 'No image found'
            print(imageUrl)

            year = ''
            try:
                year = car.find('li', attrs={"data-code": "year"}).find('span').get_text()
            except:
                year = 'No info about year'
            print(year)

            mileage = ''
            try:
                mileage = car.find('li', attrs={"data-code": "mileage"}).find('span').get_text()
            except:
                mileage = 'No info about mileage'
            print(mileage)

            engine_capacity = ''
            try:
                engine_capacity = car.find('li', attrs={"data-code": "engine_capacity"}).find('span').get_text()
            except:
                engine_capacity = 'No info about engine capacity'
            print(engine_capacity)

            fuel_type = ''
            try:
                fuel_type = car.find('li', attrs={"data-code": "fuel_type"}).find('span').get_text()
            except:
                fuel_type = 'No info about type of fuel'
            print(fuel_type)

            price = ''
            try:
                price = car.find(class_="offer-price__number").find('span').get_text()
            except:
                price = 'No info about price'
            print(price)

            currency = ''
            try:
                currency = car.find(class_="offer-price__number").find(class_="offer-price__currency").get_text()
            except:
                currency = ''
            print(currency)

            link = ''
            try:
                link = car.get('data-href')
            except:
                link = 'No info about link'
            print(link)

            # Miasto
            # print(car.find(class_="ds-location-city").get_text())
            # Wojewodztwo
            # print(car.find(class_="ds-location-region").get_text())
            self.addToJson(name, subtitle, imageUrl, year, mileage, engine_capacity, fuel_type, price + ' ' + currency,
                           link)

        if self.maxPages > self.pageCounter and page.find(class_="next abs") is not None:
            next_page = page.find(class_="next abs").find('a')['href']
            new_page = requests.get(next_page)
            new_page = bs(new_page.content, 'html.parser')
            self.getCarsListFromPage(new_page.body)


if __name__ == "__main__":
    startPage = "https://www.otomoto.pl/osobowe/"
    brandList = getListAvaibleBrandsAndModels(startPage, 'make')
    brand = getInput(brandList, 'brands', 'brand')
    if brand != "":
        startPage = str_append(startPage, brand)
        print(startPage)
        modelsList = brandList = getListAvaibleBrandsAndModels(startPage, 'model')
        model = getInput(modelsList, 'models', 'model')
        if model != "":
            startPage = str_append(startPage, model)
    startPage = inputPriceRange(startPage)
    startPage = inputMileageRange(startPage)
    startPage = getFuelType(startPage)
    maxPages = getMaxPagesInput()

    mainPage = requests.get(startPage)
    page = bs(mainPage.content, 'html.parser')
    scrapper = Scrapper(maxPages)
    carList = page.find('article')
    scrapper.getCarsListFromPage(page)
    scrapper.saveJson()
