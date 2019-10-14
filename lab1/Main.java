import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Main {
    public static void main(String[] args) {
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[]{1.2,1.8},0);

        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[6];
        // p1 >= 0
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00}, 0.0);  // focus: -p1+0 <= 0
        // p2 >= 0
        inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00}, 0.0);  // focus: -p2+0 <= 0
        // 6p1 + 3p2 >= 120
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{-6.0, -3.00}, 120.0); // focus: -6p1-3p2+120 <= 0
        // p1 + 3p2 >= 60
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{-1.0, -3.00}, 60.0);// focus: -p1-3p2+60 <= 0
        // 9p1 + p2 >= 36
        inequalities[4] = new LinearMultivariateRealFunction(new double[]{-9.0,-1.0},36);// focus: -9p1-p2+36 <= 0
        // 6p1 + 6p2 >= 180
        inequalities[5] = new LinearMultivariateRealFunction(new double[]{-6.0,-6.0},180);// focus: -6p1-6p2+180 <= 0

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
            System.out.println( "P1: " + Math.round(solution[i]) + "\tP2: " + Math.round(solution[i+1]) );
        }

        System.out.println("Z* =" + (1.2d*Math.round(solution[0])+1.8d*Math.round(solution[1])));
//////////////////////////////////////////////////////////////////////////////////////////////////////
        LinearMultivariateRealFunction objectiveFunction2 = new LinearMultivariateRealFunction(new double[]{1.2,1.8},0);

        ConvexMultivariateRealFunction[] inequalities2 = new ConvexMultivariateRealFunction[7];
        // p1 >= 0
        inequalities2[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00}, 0.0);  // focus: -p1+0 <= 0
        // p2 >= 0
        inequalities2[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00}, 0.0);  // focus: -p2+0 <= 0
        // 6p1 + 3p2 >= 120
        inequalities2[2] = new LinearMultivariateRealFunction(new double[]{-6.0, -3.00}, 120.0); // focus: -6p1-3p2+120 <= 0
        // 6p1 + 3p2 <= 240
        inequalities2[6] = new LinearMultivariateRealFunction(new double[]{6.0,3.0},-240); // focus: 6p1+3p2-240 <= 0
        // p1 + 3p2 >= 60
        inequalities2[3] = new LinearMultivariateRealFunction(new double[]{-1.0, -3.00}, 60.0);// focus: -p1-3p2+60 <= 0
        // 9p1 + p2 >= 36
        inequalities2[4] = new LinearMultivariateRealFunction(new double[]{-9.0,-1.0},36);// focus: -9p1-p2+36 <= 0
        // 6p1 + 6p2 >= 180
        inequalities2[5] = new LinearMultivariateRealFunction(new double[]{-6.0,-6.0},180);// focus: -6p1-6p2+180 <= 0

        //optimization problem
        OptimizationRequest optimizationRequest2 = new OptimizationRequest();
        optimizationRequest2.setF0(objectiveFunction2);
        optimizationRequest2.setFi(inequalities2);
        optimizationRequest2.setToleranceFeas(1.e-9);
        optimizationRequest2.setTolerance(1.e-9);

        JOptimizer optimizer2 = new JOptimizer();
        optimizer2.setOptimizationRequest(optimizationRequest2);
        try {
            optimizer2.optimize();
        } catch (JOptimizerException e) {
            e.printStackTrace();
        }

        double[] solution2 = optimizer2.getOptimizationResponse().getSolution();
        System.out.println("NIE WIECEJ NIZ 240 JEDNOSTEK WITAMINY A");
        System.out.println("Length: " + solution2.length);
        for (int i=0; i<solution2.length/2; i++){
            System.out.println( "P1: " + Math.round(solution2[i]) + "\tP2: " + Math.round(solution2[i+1]) );
        }

        System.out.println("Z* =" + (1.2d*Math.round(solution2[0])+1.8d*Math.round(solution2[1])));
    }
}
