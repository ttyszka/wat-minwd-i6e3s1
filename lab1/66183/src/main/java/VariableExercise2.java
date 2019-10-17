import org.ojalgo.optimisation.Variable;

public class VariableExercise2 extends ModelVariable {

    //Initializing variable for second subpoint

    public VariableExercise2() {
        super();
        this.p1 = Variable.make("p1").lower(0).upper(Double.POSITIVE_INFINITY).integer(true).weight(6);
        this.p2 = Variable.make("p2").lower(0).upper(p1.getLowerSlack()).integer(true).weight(9);


    }
}
