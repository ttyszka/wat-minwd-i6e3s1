import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class OjalgoSample {

    public static void main(final String[] args) {

        final Variable b = Variable.make("B").lower(0).weight(10);
        final Variable a = Variable.make("A").lower(0).weight(50);

        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        model.addVariable(a);
        model.addVariable(b);

        final Expression ograniczenie_1 = model.addExpression().lower(0).upper(480);
        ograniczenie_1.set(a, 12).set(b, 4);

        final Expression ograniczenie_2 = model.addExpression().lower(0).upper(640);
        ograniczenie_2.set(a, 8).set(b, 8);

        final Expression ograniczenie_3= model.addExpression().lower(0);
        ograniczenie_3.set(a, -1).set(b, 1);

        a.integer(true);
        b.integer(true);

        Optimisation.Result wynik = model.maximise();

        System.out.println(wynik.toString());
        System.out.println(model.toString());

    }
}