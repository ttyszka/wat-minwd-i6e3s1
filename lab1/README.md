# LAB1
Zadanie 4. Firma tworzy mieszankę do spożycia z dwóch produktów. Ma ona na celu dostarczyć pewnych składników odżywczych w ilości nie mniejszej niż określona na etykiecie.
Produkty	Ilość składników odżywczych w jednostce	Koszt jednostkowy [zł]
	I	II	III
P1	3	8	12	6
P2	9	4	3	9
Minimalna ilość	27	32	36

1. Ile należy wykorzystać produktów P1 i P2, aby spełniając wymagania koszt wytworzenia mieszanki był jak najniższy?
2. Jak zmieni się rozwiązanie, jeśli ze względu na konsystencję produktu P1 musi być więcej niż P2?
Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

1.	X1 – ilość zakupionego produktu P1
X2 – ilość zakupionego produktu P2
Ograniczenia:
Składnik I:
3X1 + 9X2 >= 27
Składnik II:
8X1 + 4X2 >= 32
Składnik III:
12X1 + 3X2 >= 36
X1 >= 0
X2>= 0
Funkcja celu:
F(X1,X2) = 6X1 + 9X2 -> min
2.	Dodatkowe ograniczenie do zadania 2
X1 > X2

