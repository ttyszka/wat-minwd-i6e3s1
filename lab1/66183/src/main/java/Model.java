import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;

@NoArgsConstructor
@Getter
public class Model {


    private ExpressionsBasedModel model = new ExpressionsBasedModel();
    private Optimisation.Result result;
    private ModelVariable modelVariable;

    public Model(ModelVariable modelVariable) {
        this.modelVariable = modelVariable;
    }

    public static void main(String[] args) {

        // Model for subpoint 1
        Model model = new Model(new VariableExercise1())
                .addVariableToModel()
                .addConstraints()
                .solveProblem();

        //Result for subpoint 1
        System.out.println(model);
        System.out.println(model.getModel().toString());

        // Model for subpoint 2
        Model model2 = new Model(new VariableExercise2())
                .addVariableToModel()
                .addConstraints()
                .solveProblem();


        //Result for subpoint 2
        System.out.println(model2);
        System.out.println(model2.getModel().toString());
    }
    //Ograniczenie względem jednostki wyrobu A

    /**
     * Adding variable defined in ModelVariable class tree
     *
     * @return this object
     */
    public Model addVariableToModel() {

        this.model.addVariable(modelVariable.getP1());
        this.model.addVariable(modelVariable.getP2());
        return this;
    }

    /**
     * Adding constraints to model
     *
     * @return this object
     */
    public Model addConstraints() {


        this.model.addExpression("constraint1")
                .lower(27)
                .set(modelVariable.getP1(), 3)
                .set(modelVariable.getP2(), 9);


        this.model.addExpression("constraint2")
                .lower(32)
                .set(modelVariable.getP1(), 8)
                .set(modelVariable.getP2(), 4);


        this.model.addExpression("constraint3")
                .lower(36)
                .set(modelVariable.getP1(), 12)
                .set(modelVariable.getP2(), 3);


        //Ograniczenie względem jednostki wyrobu B


        return this;

    }

    /**
     * Minimise result for specific problem defined in Readme file
     *
     * @return this object
     */
    public Model solveProblem() {
        //Rozwiązanie problemu

        this.result = model.minimise();

        return this;

    }

    @Override
    public String toString() {
        return this.result.toString();
    }

}


