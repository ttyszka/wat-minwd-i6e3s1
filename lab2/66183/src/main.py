import argparse
import json
import math
import os
import time
from urllib.error import HTTPError
from urllib.parse import ParseResult

from scrapping import *

path = os.path.dirname(os.getcwd())
imagePath = path + '/img'
jsonPath = path + '/json'
mainURL = ''
offersQuantity = 0


def setup():
    parser = argparse.ArgumentParser(description='Provide input')
    parser.add_argument('-p', '--propertytype', nargs='+', required=True,
                        help='Podaj czy interesuje cie dom czy mieszkanie'
                        , type=lambda input: checkArgsCorrectness(parser, ("dom", "mieszkanie"), input))
    parser.add_argument('-rt', '--rentaltype', nargs='+', required=True,
                        help='Podaj czy interesuje cie wynajem czy sprzedaz',
                        type=lambda input: checkArgsCorrectness(parser, ("wynajem", "sprzedaz"), input))
    parser.add_argument('-c', '--city', nargs='+', required=True,
                        help='Podaj misto ktore cie interesuje')
    parser.add_argument('-d', '--district', nargs='+', required=True,
                        help='Podaj dzielnice ktore cie interesuje')

    args = parser.parse_args()
    return vars(args)


def checkArgsCorrectness(parser, choices, input):
    if input not in choices:
        parser.error("Args doesn't end with one of {}".format(choices))
    return input


def getAllOffer():
    s, offersQuantity = getAllPageOffer(mainURL)
    convertToJsonAndSave(s, 1)

    pageQuantity = math.ceil(offersQuantity / offersPerPage)

    print("Offers quantity: " + str(offersQuantity))
    print("Offers per page: " + str(offersPerPage))
    print("Pages quantity: " + str(pageQuantity) + "\n\n")
    print("Downloading page: " + str(1) + " " + mainURL)
    print("Page number " + str(1) + " saved")

    if (pageQuantity > 1):
        for i in range(2, pageQuantity + 1):
            try:
                pageurl = pageURL(mainURL, i)
                print("Downloading page: " + str(i) + " " + pageurl)
                s, offersQuantity = getAllPageOffer(pageurl)
                convertToJsonAndSave(s, i)
                print("Page number " + str(i) + " saved")
            except HTTPError as httperror:
                if httperror.code == 503:
                    if i > 2:
                        i -= 1
                    else:
                        i = 2
                    time.sleep(0.2)


def convertToJsonAndSave(appartmentArray, fileName):
    with open(jsonPath + '/page' + str(fileName),
              'w') as outfile:
        for appartment in appartmentArray:
            json.dump(appartment.__dict__, outfile, indent=1, separators=(',', ': '))


def createURL(rentalType='sprzedaz', propertyType='mieszkanie', city='warszawa', district='bemowo'):
    global mainURL
    parsedResult = ParseResult(scheme='https',
                               netloc=hostURL,
                               path=("%s/%s/%s/%s" % (
                                   rentalType.lower(), propertyType.lower(), city.lower(), district.lower())),
                               params='',
                               query=("nrAdsPerPage=%d" % offersPerPage),
                               fragment='')

    mainURL = parsedResult.geturl()


def createProjectDirs():
    if not os.path.exists(imagePath):
        os.mkdir(imagePath)
    if not os.path.exists(jsonPath):
        os.mkdir(jsonPath)


def main():
    args = setup()
    try:
        createProjectDirs()
        setImagePath(imagePath)
        createURL(args['rentaltype'][0], args['propertytype'][0], args['city'][0], args['district'][0])
        getAllOffer()
    except HTTPError as httperror:
        print("Error occur: " + str(httperror))
    except OSError as oserror:
        print("Creation of the directory failed: " + str(oserror))


if __name__ == "__main__":
    main()
