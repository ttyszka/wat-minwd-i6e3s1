import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Start {

    public static void main(String[] args){

        double x = 0.3;
        double y = 0.2;

        // funkcja celu
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[] {-x, -y}, 0);

        //rozwiazanie od razu w formie dualnej ze wzgledu na 2 rodzaje ciec
        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[4];

        // 7x>2100
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{7.0, 0.0}, -2100.0);
        // 2y>1200
        inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, 2.0}, -1200.0);
        // x > 0
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.0}, 0);
        // y > 0
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{0.0, -1.0}, 0);

        //konfiguracja problemu opymalizacyjnego
        OptimizationRequest or = new OptimizationRequest();
        or.setF0(objectiveFunction);
        or.setFi(inequalities);
        or.setToleranceFeas(JOptimizer.DEFAULT_FEASIBILITY_TOLERANCE / 10);
        or.setTolerance(JOptimizer.DEFAULT_TOLERANCE / 10);

        //optymalizacja
        JOptimizer opt = new JOptimizer();
        opt.setOptimizationRequest(or);
        try {
            opt.optimize();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //rozwiazanie
        double[] sol = opt.getOptimizationResponse().getSolution();

        //wyswietlenie rozwiazania w konsoli
        System.out.println("Length: " + sol.length);
        for (int i = 0; i < sol.length; i++) {
            System.out.println("answer " + (i+1)+ ": " + (sol[i]));
        }
        double wastes = x*sol[0]+y*sol[1];
        System.out.println(wastes);
        System.out.println("\nPo zaokragleniu\n");

        for (int i = 0; i < sol.length; i++) {
            System.out.println("answer " + (i+1)+ ": " + Math.round(sol[i]));
        }

        System.out.println(Math.round(wastes));
    }
}
