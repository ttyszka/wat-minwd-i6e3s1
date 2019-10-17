# Import PuLP modeler functions
from pulp import *

# A new problem
prob = LpProblem("example", LpMinimize)

# Variables
p1 = LpVariable("p1", 0, None, LpInteger)
p2 = LpVariable("p2", 0, None, LpInteger)


# Objective (cel)
prob += 6*p1 + 9*p2, "objective"

# Constraints (Ograniczenia)
prob += 3*p1 + 9*p2 >= 27, "c1"
prob += 8*p1 + 4*p2 >= 32, "c2"
prob += 12*p1 + 3*p2 >= 36, "c3"

# Solve the problem using the default solver
prob.solve()

# Print the status of the problem
print("Status:", LpStatus[prob.status])

# Print the value of the variables
for v in prob.variables():
	print(v.name, " = ", v.varValue)

# Print the value of the objective
print("objective = ", value(prob.objective))