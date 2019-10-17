Zadanie 5. Dziecko w pewnym wieku potrzebuje określonych ilości witamin. Ilość witamin dostarczanych przez pewne produkty przedstawiono poniżej.
Produkty	Ilość witamin w jednostce				Koszt jednostkowy [zł]
		A		B		C		E	
P1		6		1		9		6	1,2
P2		3		3		1		6	1,8
Minimalna ilość	120		60		36		180	

1.	Ile należy wykorzystać produktów P1 i P2, aby spełniając wymagania koszt spełnienia zapotrzebowania był jak najniższy?
2.	Jak zmieni się rozwiązanie, jeśli ze względu na szkodliwe działanie nie można podawać więcej niż 240 jednostek witaminy A?
Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

Model:

z* <- rozwiązanie optymalne
p1 <- ilość produktu nr 1
p2 <- ilość produktu nr 2


z*= min(p1*1,2+p2*1,8)



p1*6 + p2*3 >= 120
p1*1 + p2*3 >= 60
p1*9 + p2*1 >= 36
p1*6 + p2*6 >= 180
p1 >= 0
p2 >= 0
p1,p2 należą do N+ u {0}


Rozwiązanie:

P1: 15	P2: 15
Z* = 45.0

Rozwiązanie przy dodatkowym ograniczeniu (nie wiecej niz 240 jednostek witaminy A):

P1: 15	P2: 15
Z* = 45.0