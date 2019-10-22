using Google.OrTools.LinearSolver;
using System;

/// <summary>
/// Zadanie 9
/// 
/// Funkcja celu f: max(10x + 24y + 12z)
/// gdzie:
/// x - liczba wyrobow 1
/// y - liczba wyrobow 2
/// z - liczba wyrobow 3
/// 
/// w1 = x(5s1 + s2)
/// w2 = y(3s1 + 2s2)
/// w3 = z(4s2)
/// 
/// Ograniczenia:
/// 5x + 3y <= 3600
/// x + 2y + 4z <= 4800
/// x, y, z >=0
/// </summary>

namespace OptimizationTask9
{
    class Program
    {
        static void Main(string[] args)
        {
            var solver = Solver.CreateSolver("BasicExample", "GLOP_LINEAR_PROGRAMMING");

            Variable x = solver.MakeNumVar(0.0, double.PositiveInfinity, "x");
            Variable y = solver.MakeNumVar(0.0, double.PositiveInfinity, "y");
            Variable z = solver.MakeNumVar(0.0, double.PositiveInfinity, "z");

            // 5x + 3y <= 3600
            Constraint c0 = solver.MakeConstraint(double.NegativeInfinity, 3600.0);
            c0.SetCoefficient(x, 5);
            c0.SetCoefficient(y, 3);

            // x + 2y + 4z <= 4800
            Constraint c1 = solver.MakeConstraint(double.NegativeInfinity, 4800.0);
            c1.SetCoefficient(x, 1);
            c1.SetCoefficient(y, 2);
            c1.SetCoefficient(z, 4);

            // max(10x + 24y + 12z)
            Objective objective = solver.Objective();
            objective.SetCoefficient(x, 10);
            objective.SetCoefficient(y, 24);
            objective.SetCoefficient(z, 12);
            objective.SetMaximization();

            solver.Solve();

            Console.WriteLine("Number of variables = " + solver.NumVariables());
            Console.WriteLine("Number of constraints = " + solver.NumConstraints());
            // The value of each variable in the solution.
            Console.WriteLine("Solution:");
            Console.WriteLine("x = " + x.SolutionValue());
            Console.WriteLine("y = " + y.SolutionValue());
            Console.WriteLine("z = " + z.SolutionValue());
            // The objective value of the solution.
            Console.WriteLine("Optimal objective value = " +
                            solver.Objective().Value());
        }
    }
}