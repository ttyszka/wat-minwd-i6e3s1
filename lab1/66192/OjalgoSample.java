import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class OjalgoSample {

    public static void main(final String[] args) {

        final Variable a = Variable.make("A").lower(7).upper(10).weight(0.7);
        final Variable b = Variable.make("B").lower(5).upper(15).weight(0.5);
        final Variable c = Variable.make("C").lower(4).upper(12).weight(0.3);

        //Utworzenie modelu i dodanie do nich zmiennych

        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        model.addVariable(a);
        model.addVariable(b);
        model.addVariable(c);

        //Ograniczenie względem pierwszej cechy półproduktów oraz ustalenie wartości pierwszej cechy dla każdego półproduktu

        final Expression ograniczenie_1 = model.addExpression("Ograniczenie 1").lower(100).upper(1000);
        ograniczenie_1.set(a, 80).set(b, 30).set(c, 15);

       // Ograniczenie względem drugiej cechy półproduktów oraz ustalenie wartości drugiej cechy dla każdego półproduktu

        final Expression ograniczenie_2 = model.addExpression("Ograniczenie 2").lower(50).upper(750);
        ograniczenie_2.set(a, 10).set(b, 20).set(c, 15);

       // Ograniczenie ilości połproduktów tylko do liczb całkowitych

        a.integer(true);
        b.integer(true);
        c.integer(true);

       // Rozwiązanie problemu

        Optimisation.Result wynik = model.maximise();

       // Wypisanie wyników

        System.out.println(wynik.toString());
        System.out.println(model.toString());

    }

}
