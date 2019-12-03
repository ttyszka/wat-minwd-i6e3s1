import bs4
from selenium import webdriver
import simplejson as json

url = 'https://www.flashscore.pl/pilka-nozna/anglia/premier-league/spotkania/'

browser = webdriver.Chrome('C:\Drivers\chromedriver.exe')
browser.get(url)
dict = {}
Arr = []
html = browser.page_source
soup = bs4.BeautifulSoup(html,'html.parser')
for row in soup.find_all('div', {'title':  "Zobacz szczegóły meczu!"}):
    matches = {
        "gospodarz": row.find('div', {'class': "event__participant event__participant--home"}).text.encode('utf-8'),
        "gosc": row.find('div', {'class': "event__participant event__participant--away"}).text.encode('utf-8'),
        "data": row.find('div', {'class': "event__time"}).text.encode('utf-8')
    }
    Arr.append(matches)
with open('data.txt', 'w') as outfile:
    json.dump(Arr, outfile)
browser.close()
