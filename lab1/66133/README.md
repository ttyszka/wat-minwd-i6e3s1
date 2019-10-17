# Zadanie 7, nr indeksu: 66133

## Treść

Racjonalna hodowla bydła wymaga dostarczenia każdej sztuce dwóch składników odżywczych w ilościach nie mniejszych niż określone. Składniki te zawarte są w czterech paszach. W jakich ilościach należy zakupić poszczególne pasze, aby dostarczyć niezbędne składniki przy zachowaniu jak najniższych kosztów?


| Pasze  | Zawartość w 1 kg paszy  |   | Koszt 1kg [zł]  |
|---|---|---|---|
|   | A  | B  |   |
| P1  | 0.8  | 0.6  | 9.6  |
| P2  | 2.4  | 0.6  | 14.4  |
| P3  | 0.9  | 0.3  | 10.8  |
| P4  | 0.4  | 0.3  | 7.2  |
| Minimalna ilość  | 1200  | 600  |   |
|   |   |   |   |


## Model

Zmienne:
x1 >= 0,
x2 >= 0,
x3 >= 0,
x4 >= 0

Cel:
z = 9.6x1 + 14.4x2 + 10.8x3 + 7.2x4 -> min

Warunki ograniczające:
0.8x1 + 2.4x2 + 0.9x3 + 0.4x4 >= 1200
0.6x1 + 0.6x2 + 0.3x3 + 0.3x4 >= 600

## Rozwiązanie

x1 = 750,
x2 = 250,
x3 = 0,
x4 = 0

Aby dostarczyć niezbędne składniki, przy zachowaniu jak najniższych kosztów należy zakupić 750 kg paszy P1 oraz 250 kg paszy P2. Koszty wyniosą w tym przypadku 10800 zł.
