package com.company;


import com.joptimizer.exception.JOptimizerException;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;

public class Main {

    public static void main(String[] args) {
        // Initialize objective function
        LinearMultivariateRealFunction objectiveFunction =
                new LinearMultivariateRealFunction(new double[]{-50.0, -10.0}, 0); //maximize 50x+10y

        /*
        //Ad 1)
        // Initialize inequalities constraints
        ConvexMultivariateRealFunction[] constraints = new ConvexMultivariateRealFunction[4];
        // x >= 0
        constraints[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00}, 0.0);  // -x + 0 <= 0
        // y >= 0
        constraints[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00}, 0.0);  // -y + 0 <= 0
        // 12x + 4y <= 480
        constraints[2] = new LinearMultivariateRealFunction(new double[]{12.0, 4.00}, -480.0); // 12x + 4y -480 <= 0
        // 8x + 8y <= 640
        constraints[3] = new LinearMultivariateRealFunction(new double[]{8.0, 8.00}, -640.0);// 8x + 8y - 640 <= 0
        */


        //Ad 2)
        // Initialize inequalities constraints
        ConvexMultivariateRealFunction[] constraints = new ConvexMultivariateRealFunction[5];
        // x >= 0
        constraints[0] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.00}, 0.0);  // -x + 0 <= 0
        // y >= 0
        constraints[1] = new LinearMultivariateRealFunction(new double[]{0.0, -1.00}, 0.0);  // -y + 0 <= 0
        // X <= Y
        constraints[2] = new LinearMultivariateRealFunction(new double[]{1.0, -1.00}, 0);// X - Y <= 0
        // 12x + 4y <= 480
        constraints[3] = new LinearMultivariateRealFunction(new double[]{12.0, 4.00}, -480.0); // 12x + 4y -480 <= 0
        // 8x + 8y <= 640
        constraints[4] = new LinearMultivariateRealFunction(new double[]{8.0, 8.00}, -640.0);// 8x + 8y - 640 <= 0


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
        try {
            optimizer.optimize();
        } catch (JOptimizerException e) {
            e.printStackTrace();
        }

        double[] solution = optimizer.getOptimizationResponse().getSolution();

        // Print result
        System.out.println("Length: " + solution.length);
        for (int i = 0; i < solution.length / 2; i++) {
            System.out.println("X" + (i + 1) + ": " + Math.round(solution[i]) + "\tY" + (i + 1) + ": " + Math.round(solution[i + 1]));
            double z = Math.round(50*solution[i] + 10*solution[i+1]);
            System.out.println("Z" + ": " + z);
        }
    }
}
