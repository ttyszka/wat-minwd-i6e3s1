# Zadanie 6, Mateusz Książek I6E3S1

## Treść zadania: 

Zadanie 6. Dziecko w pewnym wieku potrzebuje określonych ilości witamin. Ilość witamin dostarczanych przez pewne produkty przedstawiono poniżej. Należy wyznaczyć takie ilości poszczególnych produktów, aby zapewnić pożądaną ilość zachowując jak najniższy koszt.

|Produkty| |Ilość witaminy A w jedn.| |Ilość witaminy B w jedn.| Koszt jednostkowy [zł]|

|P1| 		  | 6 | |1 | |1,2|

|P2| 		  | 3 | |3 | |1,8|

|P3| 		  | 4 | |2 | |2,0|

|P4| 		  | 4 | |4 | |0,9|

|Minimalna ilość| |120| |60|


Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

## Definicja modelu matematycznego:
Rodzaj produktu C∈P C=<1;4>

Ilość witaminy A w jednostce produktu: Kc∈P Kc=<0;+inf>

Ilość witaminy B w jednostce produktu: Lc∈P Lc=<0;+inf>

Koszt jednostkowy produktu Zc∈R+


Ilość jednostek wit. A dla wszystkich produktów nie może być mniejsza niż 120:

Σ(Kc*Pc) >= 120, C=<1;4>

Ilość jednostek wit. B dla wszysktich produktów nie może być mniejsza niż 60:

Σ(Lc*Pc) >= 60, C=<1;4>

Koszt jednostkowy powinien być jak najniższy:

Σ(Zc*Pc)  -> min, C=<1;4>



## Zadanie optymalizacyjne
Równanie funkcji celu: f(P) = 1,2P1+1,8P2+2,0P3+0,9P4 -> min

Ograniczenie 1: 6P1+3P2+4P3+4P4 >= 120

Ograniczenie 2: 1P1+3P2+2P3+4P4 >= 60

Ograniczenia zmiennych: P1,P2,P3,P4 >= 0


## Rozwiązanie
P1 = 12,

P2 = 0,

P3 = 0,

P4 = 12

Wartość funkcji celu: f(P*)=25,2

## Additional notes

Make sure to install the .Net Core 3.0 SDK for the proper functioning.
You can find it [here](https://dotnet.microsoft.com/download/dotnet-core/3.0) .

To start the application from the command line, make sure you are inside the project with the solution. 

```
cd [YourDirectory]../wat-minwd/i6e3s1/c#/Or_Tools_Library_Example
```

Then just simply run the command from the powershell/cmd: 
```
dotnet run --project Minwd.BasicConsoleApplication
```

*There should be no worries to try compile it on Linux machine*