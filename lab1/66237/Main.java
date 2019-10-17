import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Main {
    public static void main(String[] args) {
        LinearMultivariateRealFunction objectiveFunction = new LinearMultivariateRealFunction(new double[]{-10,-24,-12},0);

        ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[5];
     
        inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00, 0.00}, 0.0);  

        inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00, 0.00}, 0.0);  
      
        inequalities[2] = new LinearMultivariateRealFunction(new double[]{0.00, 0.00, -1.00}, 0.0); 
      
        inequalities[3] = new LinearMultivariateRealFunction(new double[]{5.0, 3.00, 0.00}, -3600.0);
     
        inequalities[4] = new LinearMultivariateRealFunction(new double[]{1.0,2.0,4.0},-4800);

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
