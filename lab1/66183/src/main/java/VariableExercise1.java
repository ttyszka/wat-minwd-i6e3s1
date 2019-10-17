import org.ojalgo.optimisation.Variable;

public class VariableExercise1 extends ModelVariable {


    // //Initializing variable for first subpoint

    public VariableExercise1() {
        super();
        this.p1 = Variable.make("p1").lower(0).upper(Double.POSITIVE_INFINITY).integer(true).weight(6);
        this.p2 = Variable.make("p2").lower(0).upper(Double.POSITIVE_INFINITY).integer(true).weight(9);
    }
}
