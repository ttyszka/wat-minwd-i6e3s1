from urllib.parse import urlparse
from urllib.request import Request
from urllib.request import urlopen
from urllib.request import urlretrieve

from bs4 import BeautifulSoup as bs

hostURL = 'www.otodom.pl'
imagePath = ''
offersPerPage = 24


class Apartment:

    def __init__(self, name, location, price, image, overview=[]):
        self.name = name
        self.location = location
        self.price = price
        self.image = image
        self.overview = overview


def pageURL(mainURL, pageNumber):
    queryURL = ("%s&page=%d" % (mainURL, pageNumber))
    return queryURL


def getDetailedInfo(url):
    htmlPage = downloadPage(url)

    headerContainer = htmlPage.find('header')

    pictureContainer = htmlPage.find('picture')
    overviewContainer = htmlPage.find('section', class_='section-overview')

    return Apartment(name=headerContainer.find('h1').text,
                     location=headerContainer.find('div', class_='css-0').find('a').get_text(),
                     price=headerContainer.find('div', class_='css-1vr19r7').text,
                     image=downloadImage(pictureContainer.find('img').get('src')),
                     overview=getOverviewFromPost(overviewContainer))


def downloadPage(url):
    req = Request(url, headers={'User-Agent': "Mozilla/5.0"})
    sauce = urlopen(req).read()

    soup = bs(sauce, 'html5lib')
    return soup


def getOverviewFromPost(sectionOverview):
    overViewList = []
    for litag in sectionOverview.find_all('li'):
        overViewList.append(litag.text)

    return overViewList


def downloadImage(url):
    urlretrieve(url, imagePath + "/" + urlparse(url).path.split('/')[-2])

    return urlparse(url).path.split('/')[-2]


def getAllPageOffer(url):
    htmlPage = downloadPage(url)

    divContainer = htmlPage.find('div', class_='listing')
    offersQuantity = int(htmlPage.find('div', class_='offers-index').find('strong').text)

    allPost = []
    for articleTag in divContainer.find_all('article', class_='offer-item'):
        allPost.append(getDetailedInfo(articleTag.get('data-url')))

    return allPost, offersQuantity


def setImagePath(path):
    global imagePath
    imagePath = path
