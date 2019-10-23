# Or_Tools Optimization Problem Solving

The application shows a way to solve a simple linear optimization problem using the Or-Tools library.

## Installation:

Make sure to install the .Net Core 3.0 SDK for the proper functioning.
You can find it [here](https://dotnet.microsoft.com/download/dotnet-core/3.0) .

To start the application from the command line, make sure you are inside the project with the solution. 

```
cd [YourDirectory]../wat-minwd-i6e3s1/lab1/66179
```

Then just simply run the command from the powershell/cmd: 
```
dotnet run --project minwdOr_Tools.ConsoleApplication
```

*There should be no worries to try compile it on Linux machine*

## Treść zadania: 

Zadanie 2. Przedsiębiorstwo produkuje dwa wyroby. 
Do ich produkcji zużywa się m.in. dwa limitowane surowce. 
Zużycie tych surowców na jednostkę każdego z wyrobów, 
dopuszczalne limity zużycia oraz zyski jednostkowe ze sprzedaży podano w tabeli poniżej. 

Wyroby: W1, W2

Zużycie surowca na jednostkę: 
I:
Dla W1: 8
Dla W2: 16

II:
Dla W1: 7
Dla W2: 4

Zysk Jednostkowy
dla W1: 2
dla W2: 4

Limit zużycia surowca
dla W1: 96000
dla W2: 56000

1.	Ile należy wyprodukować wyrobu W1, a ile W2, aby nie przekraczając limitów zmaksymalizować zysk ze sprzedaży wyrobów?
2.	Jak zmieni się rozwiązanie, jeśli proces produkcyjny pozwala na wyprodukowanie maksymalnie 5000 szt. wyrobu W1, oraz maksymalnie 4000 szt. wyrobu W2?

Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.


## Model matematyczny:
Dla pierwszego przypadku: 

Zdefiniowanie symboli:
W1 - Wyrób pierwszy, gdzie W1 należy do naturalnych + 0
W2 - Wyrób drugi, gdzie W2 należy do naturalnych + 0


Funkcja celu: 2W1 + 4W2 -> max

Przy ograniczeniach: 
8W1 + 16W2 <= 96000
7W1 + 4W2  <= 56000
w1, w2 >= 0


Dla drugiego przypadku: 
W1 - Wyrób pierwszy, gdzie W1 należy do naturalnych + 0 gdzie graniczną wartością górną jest 5000
W2 - Wyrób drugi, gdzie W2 należy do naturalnych + 0 gdzie graniczną wartością górną jest 4000


Funkcja celu: 2W1 + 4W2 -> max

Przy ograniczeniach: 
8W1 + 16W2 <= 96000
7W1 + 4W2  <= 56000
w1, w2 >= 0
w1 <= 5000
w2 <= 4000


## Definicja zadania optymalizacyjnego:
Ile należy wyprodukować wyrobu W1, a ile W2, aby nie przekraczając limitów zmaksymalizować zysk ze sprzedaży wyrobów?

## Rozwiązanie 

```
*****ROZWIAZANIE Podpunkt a)*****
w1 = 0
w2 = 6000
Zmaksymalizowana wartosc funkcji celu: 24000

*****ROZWIAZANIE Podpunkt b)*****
w1 = 4000
w2 = 4000
Zmaksymalizowana wartosc funkcji celu: 24000
```

## DODATKOWO:

W folderze img/ 
dodatkowe pliki, obrazujące rozwiązanie w formie graficznej.

I6E3S1 Metody i Narzędzia Informatycznego Wspomagania Decyzji, Semestr 7

