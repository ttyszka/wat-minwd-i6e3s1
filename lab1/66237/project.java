import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Main {
    public static void main(String[] args) {
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[]{-10,-24,-12},0);

        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[5];
        // p1 >= 0
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00, 0.00}, 0.0);  // focus: -p1+0 <= 0
        // p2 >= 0
        inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00, 0.00}, 0.0);  // focus: -p2+0 <= 0
        // 6p1 + 3p2 >= 120
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{0.00, 0.00, -1.00}, 120.0); // focus: -6p1-3p2+120 <= 0
        // p1 + 3p2 >= 60
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{5.0, 3.00, 0.00}, -3600.0);// focus: -p1-3p2+60 <= 0
        // 9p1 + p2 >= 36
        inequalities[4] = new LinearMultivariateRealFunction(new double[]{1.0,2.0,4.0},-4800);// focus: -9p1-p2+36 <= 0

        //optimization problem
        OptimizationRequest optimizationRequest = new OptimizationRequest();
        optimizationRequest.setF0(objectiveFunction);
        optimizationRequest.setFi(inequalities);
        optimizationRequest.setToleranceFeas(1.e-9);
        optimizationRequest.setTolerance(1.e-9);

        JOptimizer optimizer = new JOptimizer();
        optimizer.setOptimizationRequest(optimizationRequest);
        try {
            optimizer.optimize();
        } catch (JOptimizerException e) {
            e.printStackTrace();
        }

        double[] solution = optimizer.getOptimizationResponse().getSolution();
        System.out.println("Length: " + solution.length);
        for (int i=0; i<solution.length/2; i++){
            System.out.println( "X: " + Math.round(solution[i]) + "\tY: " + Math.round(solution[i+1])+ "\tZ: " + Math.round(solution[i+2]) );
        }

        System.out.println("A =" + (10d*Math.round(solution[0])+24d*Math.round(solution[1])+12d*Math.round(solution[2])));

    }
}
