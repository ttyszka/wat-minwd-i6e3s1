import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class Ojalgo {

    public static void main(final String[] args) {


        //Utworzenie zmiennych dla każdego produktu
        //Ustalenie minimalnej i maksymalnej ilości produktów oraz zyski jednostkowe
        final Variable a = Variable.make("A").lower(0).upper(Double.POSITIVE_INFINITY).weight(6);
        final Variable b = Variable.make("B").lower(0).upper(Double.POSITIVE_INFINITY).weight(3);
        final Variable c = Variable.make("C").lower(0).upper(Double.POSITIVE_INFINITY).weight(5);
        final Variable d = Variable.make("D").lower(0).upper(Double.POSITIVE_INFINITY).weight(2);

        //Utworzenie modelu i dodanie do nich zmiennych
        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        model.addVariable(a);
        model.addVariable(b);
        model.addVariable(c);
        model.addVariable(d);

        //Ograniczenie względem jednostki wyrobu A

        final Expression ograniczenie_1 = model.addExpression("Ograniczenie 1").lower(0).upper(26000);
        ograniczenie_1.set(a, 15).set(b, 10).set(c, 20).set(d,19);

        //Ograniczenie względem jednostki wyrobu B

        final Expression ograniczenie_2 = model.addExpression("Ograniczenie 2").lower(0).upper(100000);
        ograniczenie_2.set(a, 9).set(b, 3).set(c, 5).set(d,10);

        //Ograniczenie ilości produktów tylko do liczb całkowitych
        a.integer(true);
        b.integer(true);
        c.integer(true);
        d.integer(true);

        //Rozwiązanie problemu
        Optimisation.Result wynik = model.maximise();

        //Wypisanie wyników
        System.out.println(wynik.toString());
        System.out.println(model.toString());

    }

}