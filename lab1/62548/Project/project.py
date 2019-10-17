# Import PuLP modeler functions
from pulp import *

# A new problem
prob = LpProblem("example", LpMaximize)

# Variables
x1 = LpVariable("x1", 0, None,LpInteger)
x2 = LpVariable("x2", 0, None,LpInteger)
x3 = LpVariable("x3", 0, None,LpInteger)
x4 = LpVariable("x4", 0, None,LpInteger)

# Objective
prob += 6*x1+3*x2+5*x3+2*x4, "objective"

# Constraints
prob += 15*x1+10*x2+20*x3+19*x4 <= 26000, "c1"
prob += 9*x1+3*x2+5*x3+10*x4 <= 100000, "c2"

# Solve the problem using the default solver
prob.solve()

# Print the status of the problem
print("Status:", LpStatus[prob.status])

# Print the value of the variables
for v in prob.variables():
	print(v.name, " = ", v.varValue)

# Print the value of the objective
print("objective = ", value(prob.objective))