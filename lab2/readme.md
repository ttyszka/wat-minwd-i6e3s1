Zadanie polegało na napisaniu scrapera do strony flashscore.pl

Scraper łączy się z podaną stroną i pobiera informację o lidze angielskiej, a dokładniej dzisiejsze mecze, wyniki poprzednich kolejek,
terminarz najbliższych meczy oraz aktualną tabelę ligową.

W celu uruchomienia projektu należy:
dodać do folderu src folder resources. W folderze resources musi znajdować się plik chromedriver.exe.

Scraper został napisany z wykorzystaniem Selenium. Wyniki są wyświetlane w konsoli oraz zapisywane w plikach w strukturze JSON.

Problemy stworzyła zmieniająca się struktura strony, tj. zmienna liczba tabel. 