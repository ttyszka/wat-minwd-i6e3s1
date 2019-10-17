from pulp import *
prob = LpProblem("example",LpMinimize)

P1 = LpVariable("P1", 0, None)
P2 = LpVariable("P2", 0, None)
P3 = LpVariable("P3", 0, None)
P4 = LpVariable("P4", 0, None)

prob += 9.6*P1+14.4*P2+10.8*P3+7.2*P4, "objective"

prob += 0.8*P1+2.4*P2+0.9*P3+0.4*P4 >= 1200, "c1"
prob += 0.6*P1+0.6*P2+0.3*P3+0.3*P4 >= 600, "c2"

prob.solve()

print("Status:", LpStatus[prob.status])

for v in prob.variables():
    print(v.name,"=", v.varValue)

print("objective =", value(prob.objective))
