### Treść


Zadanie 4. Firma tworzy mieszankę do spożycia z dwóch produktów. Ma ona na celu dostarczyć pewnych składników odżywczych w ilości nie mniejszej niż określona na etykiecie. 

|Produkty        |I            |II    |III      |Kosz jednostkowy |
|----------------|-------------|------|---------|-----------------|
|P1              |3            |8     |12       |6                |
|P2              |9            |4     |3        |9                |
|Minimalna ilosc |27           |32    |36       |                 |



1.	Ile należy wykorzystać produktów P1 i P2, aby spełniając wymagania koszt wytworzenia mieszanki był jak najniższy?
2.	Jak zmieni się rozwiązanie, jeśli ze względu na konsystencję produktu P1 musi być więcej niż P2?
Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

### Model

1.
ograniczenia:
3*p1 + 9*p2 >= 27
8*p1 + 4*p2 >= 32
12*p1 + 3*p2 >= 36
p1 > 0
p2 > 0

funkcja celu
Z =  6*p1 + 9*p2  ->min

2.
ograniczenia:
3*p1 + 9*p2 >= 27
8*p1 + 4*p2 >= 32
12*p1 + 3*p2 >= 36
p1 > 0
p2 > 0
p1 > p2

funkcja celu
Z =  6*p1 + 9*p2  ->min

### Rozwiązanie

1.
p1 = 3
p2 = 2
cel = 36

2.
p1 = 9
p2 = 0
cel = 54