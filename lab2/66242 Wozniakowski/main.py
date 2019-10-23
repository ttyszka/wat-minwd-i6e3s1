import os
import urllib
from urllib.parse import urlparse, ParseResult
from urllib.request import urlretrieve

import requests
import base64
from bs4 import BeautifulSoup
import json
from base64 import b64encode

queryURL = ''
baseURL = 'www.morele.net/wyszukiwarka/0/0/,,,,,,,,,,,,/1/'


# tytuly = body.find_all('h2', {'class': 'cat-product-name'}).get_text()
# ceny = body.find_all('div', {'class': 'price-new'}).get_text()
# parametry = body.find_all('div', {'class': 'cat-product-feature'}).get_text()
# zdjecia = body.find_all('a', {'class': 'cat_product_image'}).get_text()


class Base64Encoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, bytes):
            return b64encode(o).decode()
            return json.JSONEncoder.default(self, 0)


def parseHtmlToText(findElement, classElement):
    textArray  = []
    for el in body.find_all(findElement, classElement):
        textArray.append(el.get_text())
    return textArray


def getImageSrc():
    imageArray = []
    for el in body.find_all('img', {'class': 'product-image'}):
        imageArray.append(el.get('src'))
    return imageArray


def buildObjectArray(tytuly, ceny, parametry, zdjecia):

    objectArray = []
    arrayLenght = len(tytuly)
    arrayIterator = 0
    while arrayIterator < arrayLenght:
        elementToSave = ElementToSave()
        elementToSave.tytul = tytuly[arrayIterator]
        elementToSave.cena =  ceny[arrayIterator]
        parameters = []
        for el in range(0, 4):
            parameters.append(parametry[(arrayIterator*4)+el])
        elementToSave.parametr = parameters
        elementToSave.zdjecie = zdjecia[arrayIterator]
        objectArray.append(elementToSave)
        arrayIterator = arrayIterator + 1


    return objectArray

def saveToFile(moreleObjectArray):
        with open('data.json', 'w', encoding='utf-8') as outfile:
            for moreleObject in moreleObjectArray:
                downloadImage(moreleObject.zdjecie)
                json.dump(moreleObject.__dict__, outfile, indent=1, separators=(',', ': '))


class ElementToSave:

    def __init__(self, tytul = '', cena = '', parametr = '', zdjecie = ''):
        self.tytul =tytul
        self.cena = cena
        self.parametr = parametr
        self.zdjecie = zdjecie

def createDIR():
    if not os.path.exists(imageDIR):
        os.mkdir(imageDIR)


def downloadImage(url):

    opener = urllib.request.build_opener()
    opener.addheaders = [('User-Agent',
                          'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1941.0 Safari/537.36')]
    urllib.request.install_opener(opener)

    urllib.request.urlretrieve(url, imageDIR + "/" + urlparse(url).path.split('/')[-1])
    return urlparse(url).path.split('/')[-2]


def createURL(category):
    global queryURL
    parsedResult = ParseResult(scheme='https',
                               netloc=baseURL,
                               path='',
                               params='',
                               query=("q=%s" % category),
                               fragment='')

    queryURL = parsedResult.geturl()


def get_as_base64(url):
    return base64.b64encode(requests.get(url).content)


category = input("Enter category to search for\n")
createURL(category)
response = requests.get(queryURL)
imageDIR = os.getcwd() + '/img'
soup = BeautifulSoup(response.text, "html5lib")
body = soup.body

tytuly = parseHtmlToText('h2', {'class': 'cat-product-name'})
ceny = parseHtmlToText('div', {'class': 'price-new'})
parametry = parseHtmlToText('div', {'class': 'cat-product-feature'})
zdjecia = getImageSrc()

moreleObjectArray = buildObjectArray(tytuly, ceny, parametry, zdjecia)


createDIR()
saveToFile(moreleObjectArray)