using System;
using Google.OrTools.LinearSolver;


namespace Minwd.BasicConsoleApplication
{
    public class BasicExample
    {
        /// <summary>
        /// This function is solving example of the basic linear optimalization task.
        /// We are using Or-Tools Library to find the answer.
        /// </summary>
        /// <param name="args"></param>
        public static void Main(string[] args)
        {
            var solver = Solver.CreateSolver("Zadanie 6", "CBC_MIXED_INTEGER_PROGRAMMING");
            
            //Solver creates variables x1 and x2, and sets first two constraints 1 <= x1 <= 3 and 0 <= x2 <=6
            Variable P1 = solver.MakeNumVar(0.0, double.PositiveInfinity, "P1");
            Variable P2 = solver.MakeNumVar(0.0, double.PositiveInfinity, "P2");
            Variable P3 = solver.MakeNumVar(0.0, double.PositiveInfinity, "P3");
            Variable P4 = solver.MakeNumVar(0.0, double.PositiveInfinity, "P4");

            // Then we create first linear constraint

            Constraint c1 = solver.MakeConstraint(120.0, double.PositiveInfinity, "c1");
            c1.SetCoefficient(P1, 6);
            c1.SetCoefficient(P2, 3);
            c1.SetCoefficient(P3, 4);
            c1.SetCoefficient(P4, 4);

            // And the second one
            Constraint c2 = solver.MakeConstraint(60.0, double.PositiveInfinity, "c2");
            c2.SetCoefficient(P1, 1);
            c2.SetCoefficient(P2, 3);
            c2.SetCoefficient(P3, 2);
            c2.SetCoefficient(P4, 4);


            // And then we precise the objective for input.
            var objective = solver.Objective();
            objective.SetCoefficient(P1, 1.2);
            objective.SetCoefficient(P2, 1.8);
            objective.SetCoefficient(P3, 2.0);
            objective.SetCoefficient(P4, 0.9);
            objective.SetMinimization();

            solver.Solve();
            Console.WriteLine("Mateusz Książek, I6E3S1");
            Console.WriteLine("Zadanie 6");
            Console.WriteLine("Ilość zmiennych = " + solver.NumVariables());
            Console.WriteLine("Ilość ograniczeń = " + solver.NumConstraints());
            Console.WriteLine("Równanie funkcji celu: 1,2P1+1,8P2+2,0P3+0,9P4 -> min");
            Console.WriteLine("Ograniczenie 1: 6P1+3P2+4P3+4P4 >= 120");
            Console.WriteLine("Ograniczenie 2: 1P1+3P2+2P3+4P4 >= 60\n");
            Console.WriteLine("*****ROZWIAZANIE*****");
            Console.WriteLine("P1 = " + P1.SolutionValue());
            Console.WriteLine("P2 = " + P2.SolutionValue());
            Console.WriteLine("P3 = " + P3.SolutionValue());
            Console.WriteLine("P4 = " + P4.SolutionValue());
            Console.WriteLine("Wartość funkcji celu: " + solver.Objective().Value());

            Console.ReadKey();
        }
    }
}
