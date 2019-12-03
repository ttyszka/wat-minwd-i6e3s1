# xkom_scrapper

Prosty scrapper serwisu x-kom.pl dla celów edukacyjnych (tzn. nastawiony na prostote i czytelność)

Wejście: Pełen URL do kategorii na x-kom.pl
Wyjście: JSONy

               {
                name: "", /nazwa przedmiotu
                parameters: [], /cechy opisujące przedmiot
                price: "", /cena
                img: "" /załączona grafika kodowana w base64
               }
 
Wymagania:
Node.js

Instalacja

1.Pobierz repozytorium

2. W folderze repozytorium uruchom z wiersza poleceń/terminala:
  npm install
  
Uruchomienie:

1. Aby uruchomić przykładowy scenariusz uruchom:
  npm test
  Otrzymasz plik output.json

2. Można wprowadzić parametry ręcznie:
  node scrapper.js pelen-url-do-wybranej-kategorii-z-xkom plik-wyjsciowy
Np.
  node scrapper.js https://www.x-kom.pl/g-2/c/1668-tablety-10.html output.json
