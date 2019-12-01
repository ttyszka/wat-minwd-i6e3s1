
# Laboratorium nr 4. WebAPI - ZTM + Google Maps

  
## Treść

Strona internetowa pokazująca aktualne położenie autobusów i tramwaji Zarządu Transportu Miejskiego w Warszawie.

Wykorzystano dane dostępne dla każdego w ramach projektu "Otwarte dane".

 
## Działanie
	
Po uruchomieniu projektu uruchomi się strona na której należy wpisać numer lini którą chce się obserwować.

Jeśli nic się nie wpiszę program pokaże pustą mapę scentrowaną na cenurum Warszawy.

Jeśli poda się numer lini który nie istnieje wyświetli się napis "Do not exist".

Jeśli chce się zobaczyć gdzie znajdują się wszystkie autobusy należy wpisać "buses".

Jeśli chce się zobaczyć gdzie znajdują się wszystkie tramwaje należy wpisać "trams".

## Wymagania

- python 2.7
- flask
- requests
- json
- flask_googlemaps

## Instalacja 
Aby zainstalować zależności wymagane przy aplikacji należy w folderze Project uruchomić w terminalu/cmd polecenie:

> pip install -r requirements.txt

Aby uruchomić aplikację należy wpisać w terminalu/cmd polecenie:

>  python app.py