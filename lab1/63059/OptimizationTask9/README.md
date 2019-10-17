# Or_Tools Optimization Problem Solving

The application shows a way to solve a simple linear optimization problem using the Or-Tools library.

## Installation:

Make sure to install the .Net Core 3.0 SDK for the proper functioning.
You can find it [here](https://dotnet.microsoft.com/download/dotnet-core/3.0) .

To start the application from the command line, make sure you are inside the project with the solution. 

```
cd [YourDirectory]../wat-minwd-i6e3s1/lab1/63059
```

Then just simply run the command from the powershell/cmd: 
```
dotnet run --project OptimizationTask9
```

*There should be no worries to try compile it on Linux machine*

## Treść zadania: 

Zadanie 9. Przedsiębiorstwo produkuje trzy wyroby. Do ich produkcji zużywa się m.in. dwa limitowane surowce. Zużycie tych surowców na jednostkę każdego z wyrobów, dopuszczalne limity zużycia oraz zyski jednostkowe ze sprzedaży podano w tabeli poniżej. Należy wyznaczyć takie ilości poszczególnych wyrobów, aby zysk był maksymalny.

Wyroby | Zużycie surowca I na jednostkę | Zużycie surowca I na jednostkę | Zysk jednostkowy [zł]

W1 5 1 10

W2 3 2 24

W3 0 4 12

Limit zużycia surowca I: 3600
Limit zużycia surowca II: 4800

Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.


## Model matematyczny:
Funkcja celu f: max(10x + 24y + 12z)
gdzie:
x - liczba wyrobow 1
y - liczba wyrobow 2
z - liczba wyrobow 3

w1 = x(5s1 + s2)
w2 = y(3s1 + 2s2)
w3 = z(4s2)
 
Ograniczenia:
5x + 3y <= 3600
x + 2y + 4z <= 4800
x, y, z >=0


## Definicja zadania optymalizacyjnego:
Ile należy wyprodukować wyrobu W1, ile wyrobu W2, a ile wyrobu W3, aby nie przekraczając limitów zmaksymalizować zysk ze sprzedaży wyrobów?

## Rozwiązanie 
Należy wyprodukować wyłącznie wyrób W2 w liczbie 1200 jednostek.