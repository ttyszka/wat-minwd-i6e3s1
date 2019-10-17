# Zadanie 5
## Treść zadania 

Dziecko w pewnym wieku potrzebuje określonych ilości witamin. Ilość witamin dostarczanych przez pewne produkty przedstawiono poniżej.

Produkty Ilość witamin w jednostce
|                |A            |B            | C             | E      |Koszt jednostkowy [zł]
|----------------|-------------|-------------|---------------|--------|----------------------
|P1              |6            |1            |9              |6       |1,2
|P2              |3            |3            |1              |6       |1,8
|Minimalna ilosc |120          |60           |36             |180     |

## Model
Zmienne:
x - ilosc witamin w produkcie P1 
y - ilosc witamin w produkcie P2

Ograniczenia:
Funkcja celu: 1.2x + 1.8y => min
6x + 3y >= 120
x + 3y >= 60
9x + y >= 36
6x + 6y >= 180

oraz x i y >= 0

## Treść zadania optymalizacyjnego

1. Ile należy wykorzystać produktów P1 i P2, aby spełniając wymagania koszt spełnienia zapotrzebowania był jak najniższy?

2. Jak zmieni się rozwiązanie, jeśli ze względu na szkodliwe działanie nie można podawać więcej niż 240 jednostek witaminy A?

Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

All your files and folders are presented as a tree in the file explorer. You can switch from one to another by clicking a file in the tree.

## Rozwiązanie zadania
Liczba zmiennych = 2
Liczba ograniczeń = 4

Rozwiązanie punkt 1:
Wartość funkcji celu: = 44,99999999999999
Nalezy wyprodukowac = 15 P1 produktow
Nalezy wyprodukowac = 15 P2 produktow

Rozwiazanie punkt 2:
Nalezy wyprodukowac = 15 P1 produktow
Nalezy wyprodukowac = 15 P2 produktow
Nie zmieni sie rozwiazanie, jezeli nie mozna podawac wiecej niz 240 jednostek witaminy A
