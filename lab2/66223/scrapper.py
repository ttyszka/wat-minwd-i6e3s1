import requests
from bs4 import BeautifulSoup
import pandas as pd


#https://www.otomoto.pl/osobowe/

def getPrize(link):
    kasa=[]
    source = requests.get(link)
    soup = BeautifulSoup(source.text, 'html.parser')
    money = soup.find_all('span', attrs={'class':'offer-price__number ds-price-number'})
    for x in money:
        monny = x.find('span').text
        kasa.append(monny)
    return kasa

#offer-item__title
def getName(link):
    nazwa=[]
    source = requests.get(link)
    soup = BeautifulSoup(source.text, 'html.parser')
    title = soup.find_all('div', attrs={'class':'offer-item__title'})
    for x in title:
        tytle=x.find('a')['title']
        nazwa.append(tytle)
    return nazwa

def getUrl(link):
    strona=[]
    source = requests.get(link)
    soup = BeautifulSoup(source.text, 'html.parser')
    url = soup.find_all('div', attrs={'class':'offer-item__title'})
    for x in url:
        urle=x.find('a')['href']
        strona.append(urle)
    return strona

    #ds-params-block

def getRKPSP(link):
    RKPSP=[]
    source = requests.get(link)
    soup = BeautifulSoup(source.text, 'html.parser')
    glowny = soup.find_all('ul', attrs={'class':'ds-params-block'})
    for x in glowny:
        rok=x.contents[1].text[1:-2]
        km=x.contents[3].text[2:-4]
        
        RKPSP.append((rok,km))

    return RKPSP

def getCity(link):
    city=[]
    source = requests.get(link)
    soup = BeautifulSoup(source.text, 'html.parser')
    miasto = soup.find_all('span', attrs={'class':'ds-location-city'})
    for x in miasto:
        miastko=x.text
        city.append(miastko)

    return city

def getLocation(link):
    location=[]
    source = requests.get(link)
    soup = BeautifulSoup(source.text, 'html.parser')
    lokacjia = soup.find_all('span', attrs={'class':'ds-location-region'})
    for x in lokacjia:
        lokacja=x.text[1:-1]
        location.append(lokacja)

    return location

#kasa, url,nazwa, rok, km, miejscowosc, region

if __name__ == "__main__" :

    link = "https://www.otomoto.pl/osobowe/"  
    strona=[]
    kasa=[]
    nazwa=[] 
    rkpsp=[]
    city=[]
    location=[]
    strona = getUrl(link)
    kasa = getPrize(link)
    nazwa= getName(link) 
    rkpsp=getRKPSP(link)
    city=getCity(link)
    location=getLocation(link)
    #print(strona)
    #print(kasa)
    #print(nazwa)
    #print(rkpsp)
    #print(city)
    #print(location)

    df = pd.DataFrame(
        {
            'Nazwa': nazwa,
            'Kasa': kasa,
            'rkpsp': rkpsp,
            'city': city,
            'location': location,
            'strona': strona
        }
    )
    
   df.to_csv('cars.csv',index=False)
