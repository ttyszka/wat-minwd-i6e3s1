import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class JoptimizerTask {

  public static void main(String[] args) throws Exception {

    // Initialize objective function
    LinearMultivariateRealFunction objectiveFunction =
        new LinearMultivariateRealFunction(new double[] {9.6, 14.4, 10.8, 7.2}, 0); //minimize 9.6x1 + 14.4x2 + 10.8x3 + 7.2x4

    // Initialize inequalities constraints
    ConvexMultivariateRealFunction[] constraints = new ConvexMultivariateRealFunction[6];
    // x1 >= 0
    constraints[0] = new LinearMultivariateRealFunction(new double[] {-1.0, 0.00, 0.00, 0.00}, 0.0);
    // x2 >= 0
    constraints[1] = new LinearMultivariateRealFunction(new double[] {0.0, -1.00, 0.00, 0.00}, 0.0);
    // x3 >= 0
    constraints[2] = new LinearMultivariateRealFunction(new double[] {0.0, 0.00, -1.00, 0.00}, 0.0);
    // x4 >= 0
    constraints[3] = new LinearMultivariateRealFunction(new double[] {0.0, 0.00, 0.00, -1.00}, 0.0);
    // 0.8x1 + 2.4x2 + 0.9x3 + 0.4x4 >= 1200
    constraints[4] = new LinearMultivariateRealFunction(new double[] {-0.8, -2.4, -0.9, -0.4}, 1200.0);
    // 0.6x1 + 0.6x2 + 0.3x3 + 0.3x4 >= 600
    constraints[5] = new LinearMultivariateRealFunction(new double[] {-0.6, -0.6, -0.3, -0.3}, 600.0);

    // Initialize optimization request
    OptimizationRequest request = new OptimizationRequest();
    request.setF0(objectiveFunction);
    request.setFi(constraints);

    // Set tolerance
    request.setToleranceFeas(1.E-9);
    request.setTolerance(1.E-9);

    // Initialize JOptimizer and get optimization result
    JOptimizer optimizer = new JOptimizer();
    optimizer.setOptimizationRequest(request);
    optimizer.optimize();

    double[] solution = optimizer.getOptimizationResponse().getSolution();

    // Print result
    System.out.println("RESULT - variables values: ");
    for (int i = 0; i < solution.length; i++) {
      System.out.println("X" + (i + 1) + ": " + Math.round(solution[i]));
    }

  }
}
