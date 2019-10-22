using System;
using Google.OrTools.LinearSolver;


namespace minwdOr_Tools.ConsoleApplication
{
    public class BasicExample
    {
        /// <summary>
        /// This function is solving example of the basic linear optimalization task.
        /// We are using Or-Tools Library to find the answer.
        /// Math Model: 
        /// Maximize -> 2w1 + 4w2
        /// While: 
        /// 8w1 + 16w2 <= 96000
        /// 7w1 + 4w2 <= 56000
        /// w1, w2 >= 0
        /// AND THEN IN SECOND CASE
        /// w1 <= 5000, w2 <= 4000
        /// While all factors are greater or equal zero.
        /// </summary>
        /// <param name="args"></param>
        public static void Main(string[] args)
        {
            // First to simplfy we create two solvers for the a and b case from the laboratory task.
            var solver = Solver.CreateSolver("BasicExample", "GLOP_LINEAR_PROGRAMMING");
            var secondSolver = Solver.CreateSolver("BasicExample2", "GLOP_LINEAR_PROGRAMMING");

            //Then we precise two variables w1 and w2 both  >= 0
            var w1 = solver.MakeNumVar(0.0, double.PositiveInfinity, "w1");
            var w2 = solver.MakeNumVar(0.0, double.PositiveInfinity, "w2");

            //then we create two linear constraints <= 96000
            var firstLinearConstraint = solver.MakeConstraint(0.0, 96000, "firstLinearConstraint");
            //and another <=56000
            var secondLinearConstraint = solver.MakeConstraint(0.0, 56000, "secondLinearConstraint");

            //then we set factors -> 8w1 + 16w2
            firstLinearConstraint.SetCoefficient(w1, 8);
            firstLinearConstraint.SetCoefficient(w2, 16);

            // 7w1 + 4w2
            secondLinearConstraint.SetCoefficient(w1, 7);
            secondLinearConstraint.SetCoefficient(w2, 4);

            //need to create the objective variable to set maximization factors and goal
            var objective = solver.Objective();

            // we precise that we want to maximize 2w1 + 4w2 function
            objective.SetCoefficient(w1, 2);
            objective.SetCoefficient(w2, 4);
            objective.SetMaximization();

            //then we ask the first solver to solve the problem
            solver.Solve();

            Console.WriteLine("*****ROZWIAZANIE Podpunkt a)*****");
            Console.WriteLine("w1 = " + w1.SolutionValue());
            Console.WriteLine("w2 = " + w2.SolutionValue());
            Console.WriteLine("Zmaksymalizowana wartosc funkcji celu: " + solver.Objective().Value());


            //***********************************
            //Then we create the same thing for b) task. In addition we add that w1 has to be in 0 <= w1 <= 5000 and 0 < w2 <= 4000
            //The solution is the same for second term

            var w1_withAddditionalConstraint = secondSolver.MakeNumVar(0, 5000, "w1_withAddditionalConstraint");
            var w2_withAddditionalConstraint = secondSolver.MakeNumVar(0, 4000, "w2_withAddditionalConstraint");

            var linearConstraint = secondSolver.MakeConstraint(0, 96000, "linearConstraint");
            var anotherLinearConstraint = secondSolver.MakeConstraint(0, 56000, "anotherLinearConstraint");

            linearConstraint.SetCoefficient(w1_withAddditionalConstraint, 8);
            linearConstraint.SetCoefficient(w2_withAddditionalConstraint, 16);

            anotherLinearConstraint.SetCoefficient(w1_withAddditionalConstraint, 7);
            anotherLinearConstraint.SetCoefficient(w2_withAddditionalConstraint, 4);

            var secondObjective = secondSolver.Objective();

            secondObjective.SetCoefficient(w1_withAddditionalConstraint, 2);
            secondObjective.SetCoefficient(w2_withAddditionalConstraint, 4);
            secondObjective.SetMaximization();

            secondSolver.Solve();

            Console.WriteLine();
            Console.WriteLine("*****ROZWIAZANIE Podpunkt b)*****");
            Console.WriteLine("w1 = " + w1_withAddditionalConstraint.SolutionValue());
            Console.WriteLine("w2 = " + w2_withAddditionalConstraint.SolutionValue());
            Console.WriteLine("Zmaksymalizowana wartosc funkcji celu: " + secondSolver.Objective().Value());

            Console.ReadKey();
        }
    }
}
