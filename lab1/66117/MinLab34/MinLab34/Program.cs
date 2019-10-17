using System;
using Google.OrTools.LinearSolver;

namespace MinLab34
{
    class Program
    {
        static void Main(string[] args)
        {
            Solver solver = Solver.CreateSolver("SimpleLpProgram", "GLOP_LINEAR_PROGRAMMING");

            //Variables x, y > 0
            Variable x = solver.MakeNumVar(0.0, double.PositiveInfinity, "x");
            Variable y = solver.MakeNumVar(0.0, double.PositiveInfinity, "y");

            Console.WriteLine("Liczba zmiennych = " + solver.NumVariables());

            // Constraints: 
            // 6x + 3y >= 120
            // x + 3y >= 60
            // 9x + y >= 36
            // 6x + 6y >= 180
            Constraint ct1a = solver.MakeConstraint(120, double.PositiveInfinity, "ct1a");
            ct1a.SetCoefficient(x, 6);
            ct1a.SetCoefficient(y, 3);

            Constraint ct2a = solver.MakeConstraint(60, double.PositiveInfinity, "ct2a");
            ct2a.SetCoefficient(x, 1);
            ct2a.SetCoefficient(y, 3);

            Constraint ct3a = solver.MakeConstraint(36, double.PositiveInfinity, "ct3a");
            ct3a.SetCoefficient(x, 9);
            ct3a.SetCoefficient(y, 1);

            Constraint ct4a = solver.MakeConstraint(180, double.PositiveInfinity, "ct4a");
            ct4a.SetCoefficient(x, 6);
            ct4a.SetCoefficient(y, 6);

            Console.WriteLine("Liczba ograniczeń = " + solver.NumConstraints());

            // Objective function: 1.2x + 1.8y => min
            Objective objectiveA = solver.Objective();
            objectiveA.SetCoefficient(x, 1.2);
            objectiveA.SetCoefficient(y, 1.8);
            objectiveA.SetMinimization();

            solver.Solve();

            Console.WriteLine("-----------------------");
            Console.WriteLine("Rozwiązanie punkt 1:");
            Console.WriteLine("Wartość funkcji celu: = " + solver.Objective().Value());
            Console.WriteLine("Nalezy wyprodukowac = " + (int)Math.Ceiling(x.SolutionValue()) + " P1 produktow");
            Console.WriteLine("Nalezy wyprodukowac = " + (int)Math.Ceiling(y.SolutionValue()) + " P2 produktow");
            Console.WriteLine("-----------------------");

            Console.WriteLine("Rozwiazanie punkt 2:");
            // Constraints: 
            // 6x + 3y < 240
            // x + 3y >= 60
            // 9x + y >= 36
            // 6x + 6y >= 180
            Constraint ct1b = solver.MakeConstraint(double.NegativeInfinity, 240, "ct1b");
            ct1b.SetCoefficient(x, 6);
            ct1b.SetCoefficient(y, 3);

            Constraint ct2b = solver.MakeConstraint(60, double.PositiveInfinity, "ct2b");
            ct2b.SetCoefficient(x, 1);
            ct2b.SetCoefficient(y, 3);

            Constraint ct3b = solver.MakeConstraint(36, double.PositiveInfinity, "ct3b");
            ct3b.SetCoefficient(x, 9);
            ct3b.SetCoefficient(y, 1);

            Constraint ct4b = solver.MakeConstraint(180, double.PositiveInfinity, "ct4b");
            ct4b.SetCoefficient(x, 6);
            ct4b.SetCoefficient(y, 6);

            // Objective function: 1.2x + 1.8y => min
            Objective objectiveB = solver.Objective();
            objectiveB.SetCoefficient(x, 1.2);
            objectiveB.SetCoefficient(y, 1.8);
            objectiveB.SetMinimization();

            solver.Solve();
            Console.WriteLine("Nalezy wyprodukowac = " + (int)Math.Ceiling(x.SolutionValue()) + " P1 produktow");
            Console.WriteLine("Nalezy wyprodukowac = " + (int)Math.Ceiling(y.SolutionValue()) + " P2 produktow");

            Console.WriteLine("Nie zmieni sie rozwiazanie, jezeli nie mozna podawac wiecej niz 240 jednostek witaminy A");
            Console.WriteLine("-----------------------");
        }
    }
}
