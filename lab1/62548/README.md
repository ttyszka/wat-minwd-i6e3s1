
# Laboratorium nr 2. Zadanie nr 10

  
## Treść

W pewnym zakładzie produkcyjnym wytwarza się cztery wyroby (1, 2, 3 i 4). Ich produkcja wymaga nakładów pewnych środków produkcji. Część z tych środków zakład może nabywać w nieograniczonych ilościach. Jednak niektóre z nich mogą być wykorzystane tylko w ściśle określonych granicach. Do tej grupy należą środki A i B. Limity tych środków podano w ostatniej kolumnie tabeli.

Jednostkowe nakłady środków na produkcje wyrobów przedstawiono w tabeli. Znając zysk ze sprzedaży jednostki każdego z wyrobów (ostatni wiersz) wyznaczyć optymalne z punktu widzenia zysków rozmiary produkcji.

 
| Nakłady środków produkcji na jednostkę wyrobu | Wyrób 1 |Wyrób 2|Wyrób 3|Wyrób 4|Zasoby środków promocji|
|--|--|-- | --|--| --|
| A | 15 |10 | 20|19 | 26 000|
| B | 9| 3| 5|10 | 100 000|
| Zyski jednostkowe | 6 |3 |5 | 2| |

Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

 
## Model oraz treść zadania optymalizacyjnego:

 Zmienne:
x1 - zbiór produktów wyrobu 1 (x1 należy do zbioru liczb naturalnych)

x2 - zbiór produktów wyrobu 2 (x2 należy do zbioru liczb naturalnych)

x3 - zbiór produktów wyrobu 3 (x3 należy do zbioru liczb naturalnych)

x4 - zbiór produktów wyrobu 4 (x4 należy do zbioru liczb naturalnych)

  
 Zadanie optymalizacyjne: Znając zysk ze sprzedaży jednostki każdego z wyrobów (ostatni wiersz) wyznaczyć optymalne z punktu widzenia zysków rozmiary produkcji.
 
Cel:
max 6*x1+3*x2+5*x3+2*x4

Ograniczenia:
15*x1+10*x2+20*x3+19*x4 <= 26000

9*x1+3*x2+5*x3+10*x4 <= 100000

## Rozwiązanie

Zmienne wynoszą: x1 = 1733, x2 = 0, x3 = 0, x4 = 0.

Cel wynosi: 10398

## Instalacja 
W folderze Project uruchomić w terminalu/cmd polecenie:

pip install -r requirements.txt

Następnie python project.py