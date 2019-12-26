# Flashscore Scraper

Aplikacja umożliwia zescrapowanie aktualnej tabeli oraz dzisiejszych, ostatnich oraz nadchodzących meczów dla najwyższej klasy rozgrywkowej angielskiej ligi piłki nożnej - Premier League. Wyniki dla każdego z powyżej opisanych zagadnień zwracane są w postaci pliku .json. 

## Użyte technologie
.NET Core 3.0, Selenium

## Uruchomienie

Aplikacja do poprawnego działania wymaga Chromedrivera dla Selenium (plik .exe znajduje się w repozytorium). Po uruchomieniu zostaje uruchomiony klient przeglądarki Google Chrome bez interfejsu graficznego. W przeciągu kilkunastu sekund aplikacja wygeneruje 4 pliki .json z rezultatami działania. Po ukończeniu aplikacja powinna zwrócić exit code 0. 

![Example-1](https://i.imgur.com/eTDskIi.png)

Ścieżka do wyników:

```
[YourDirectory]../lab2/59894/FlashscoreScraper/FlashscoreScraper/bin/Debug/netcoreapp3.0/Result
```

## Struktura plików JSON
### TodayMatches.json, LatestMatches.json, UpcomingMatches.json

![Example-2](https://i.imgur.com/goavcyJ.png)

Zwracana jest lista meczów, gdzie dla każdego z nich wyszczególnione zostały data, drużyny biorące udział oraz wynik.

### Table.json
![Example-2](https://i.imgur.com/8YvfEw5.png)

Tabela ligowa zwracana jest jako lista rekordów, gdzie każdy z nich jest określony poprzez pozycję, nazwę zespołu, liczbę rozegranych meczów, liczbę wygranych, remisów, porażek, bilansu bramkowego oraz zdobytych punktów.

### Dodatkowe uwagi:
Autor: Mateusz Książek I6E3S1, 59894

Przedmiot: Metody i narzędzia informatycznego wspomagania decyzji

Prowadzący: mgr inż. Kamil Banach
