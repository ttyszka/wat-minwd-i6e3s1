# Or_Tools Optimization library basic example

The application shows a way to solve a simple linear optimization problem using the Or-Tools library.

## Installation:

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

## Linear problem which it's solving: 

Zadanie 6. Dziecko w pewnym wieku potrzebuje określonych ilości witamin. Ilość witamin dostarczanych przez pewne produkty przedstawiono poniżej. Należy wyznaczyć takie ilości poszczególnych produktów, aby zapewnić pożądaną ilość zachowując jak najniższy koszt.

Produkty Ilość witamin w jednostce Koszt jednostkowy [zł] A B

P1 6 1 1,2

P2 3 3 1,8

P3 4 2 2,0

P4 4 4 0,9

Minimalna ilość 120 60

Zbuduj model matematyczny i rozwiąż zadanie metodą geometryczną.

