# Twitter API Sample

Jest to prosta aplikacja opierająca swoje działanie o API twittera.

## Główne funkcje
- logowanie z użyciem konta Twitter
- podgląd szczegółów konta
- publikowanie tweetów
- dodawanie tweetów z osi czasu do ulubionych
- wyświetlanie tweetów (podgląd osi czasu użytkownika)
- chmura słów zbudowana ze słów z ostatnich 10, 50 lub 100 tweetów (do wyboru przez użytkownika)

## Instalacja
Z poziomu lokalizacji projektu należy wykonać komendy:

```bash
pip install -r requirements.txt
python manage.py migrate
```

## Uruchomienie
Z poziomu lokalizacji projektu należy wykonać komendę:
```bash
python manage.py runserver
```
Aplikacja powinna być dostępna z poziomu przeglądarki pod adresem:
```bash
http://127.0.0.1:8000/
```

## Autor
Patryk kwaśniak, nr 66133