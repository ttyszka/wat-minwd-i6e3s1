import base64
import re
from urllib.request import Request, urlopen

import requests
from bs4 import BeautifulSoup as soup
import json
from base64 import b64encode


# class copied from:
# https://stackoverflow.com/questions/37225035/serialize-in-json-a-base64-encoded-data?fbclid=IwAR1Ogz_nTBv0GB0Gy_iVM0H2Q9V4Ej89DvNmqPUXd2lnd1NicTLkol3RbMg
class Base64Encoder(json.JSONEncoder):
    # pylint: disable=method-hidden
    def default(self, o):
        if isinstance(o, bytes):
            return b64encode(o).decode()
        return json.JSONEncoder.default(self, o)


# metodka do pobierania zdjęcia z adresu URL i konwertowania go na base 64
def get_as_base64(url):
    return base64.b64encode(requests.get(url).content)


# url
url = 'https://www.otodom.pl/sprzedaz/mieszkanie/'
# request, bo inaczej nie da się strzelić do otodom.pl
req = Request(url, headers={'User-Agent': 'Mozilla/5.0'})

webpage = urlopen(req).read()
page_soup = soup(webpage, "html.parser")

# wyciąganie cen z htmla po tagu li
prices = page_soup.findAll("li", "offer-item-price")
# wyciąganie danych z htmla po tagu span
data = page_soup.findAll('span', {'data-src': re.compile(';')})

# parsowanie htmla na tytuł, cenę, zdjęcie i parsowanie danych, wywalanie zbędnego shitu  replace'ami
pricesList = []
for price in prices:
    stringPrice = str(price).replace('<li class="offer-item-price">', '').replace('Zapytaj o cenę', '') \
        .replace('</li>', '').replace(' ', '').replace('\n', '')
    pricesList.append(stringPrice)

titlesList = []
for title in data:
    stringTitle = str(title)
    match = str(re.findall('Mieszkanie.+? - z', stringTitle)).replace(' - z', '').replace('[', '') \
        .replace('\'', '').replace(']', '')
    titlesList.append(match)

imagesList = []
for img in data:
    imgLink = str(img)
    match = str(re.findall('https://.+?q=80', imgLink)).replace('[', '').replace('\'', '').replace(']', '')
    imagesList.append(match)

# get as base64 parsuje do base64, pętelka ta poniżej tworzy obiekt jsonowy
cenys_titless_parsedImgs = [{"Prize": c, "Title": t, "ImageUrl": get_as_base64(i)} for c, t, i in
                            zip(pricesList, titlesList, imagesList)]

# zapisuje do pliku jako JSON, dodatkowo używam cls=Base64Encoder bo bez tego url sie wywala bo w jsonie nie chce sie
# zapisać ten base, nie wiem dokladnie dlaczego, jest link wyżej - mozesz doczytać
with open('data.json', 'w', encoding='utf-8') as f:
    json.dump(cenys_titless_parsedImgs, f, ensure_ascii=False, indent=4, cls=Base64Encoder)
