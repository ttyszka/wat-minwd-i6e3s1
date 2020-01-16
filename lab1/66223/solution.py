from pulp import *

#x1,x2,x3,x4 - produkt1,produkt2,produkt3,produkt4
x1 = LpVariable("x1", 0, None,LpInteger)
x2 = LpVariable("x2", 0, None,LpInteger)
x3 = LpVariable("x3", 0, None,LpInteger)
x4 = LpVariable("x4", 0, None,LpInteger)

#funkcja celu
prob = LpProblem("problem", LpMinimize)
prob += 1.2*x1+1.8*x2+2.0*x3+0.9*x4
#Ograniczenia
prob += 6*x1+3*x2+4*x3+4*x4 >=120
prob += 1*x1+3*x2+2*x3+4*x4 >=60

prob.solve()


print("Status:", LpStatus[prob.status])

#liczba poszczególnych produktów
for v in prob.variables():
	print(v.name, " = ", v.varValue)

#wartość funkcji celu
print("Wartość funkcji celu = ", value(prob.objective))