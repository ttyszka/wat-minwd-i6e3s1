import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

public class OjalgoSample {

    public static void main(final String[] args) {

        //Maksymalizacja kosztu produkcji

        //Utworzenie zmiennych dla każdego półproduktu
        //Ustalenie minimalnej i maksymalnej ilości półproduktów oraz ceny każdej sztuki

        final Variable a = Variable.make("A").lower(0).weight(2);
        final Variable b = Variable.make("B").lower(0).weight(4);

        //Utworzenie modelu i dodanie do nich zmiennych
        final ExpressionsBasedModel model = new ExpressionsBasedModel();
        model.addVariable(a);
        model.addVariable(b);
        //model.addVariable(c);

        //Ograniczenie względem pierwszej cechy półproduktów
        //Ustalenie wartości pierwszej cechy dla każdego półproduktu

       // final Expression ograniczenie_1 = model.addExpression("Zuzycie na jednostke I").lower(0).upper(5000); // 2 wersja
      //  ograniczenie_1.set(a, 8).set(b, 16);
        final Expression ograniczenie_1 = model.addExpression("Zuzycie na jednostke I").lower(0).upper(96000); // 1 wersja
      ograniczenie_1.set(a, 8).set(b, 16);
        //Ograniczenie względem drugiej cechy półproduktów
        //Ustalenie wartości drugiej cechy dla każdego półproduktu

       // final Expression ograniczenie_2 = model.addExpression("Ograniczenie 2").lower(0).upper(4000);  // 2 wersja
       // ograniczenie_2.set(a,7).set(b, 4);
      final Expression ograniczenie_2 = model.addExpression("Ograniczenie 2").lower(0).upper(56000); // 1 wersja
        ograniczenie_2.set(a,7).set(b, 4);
    //    Ograniczenie ilości połproduktów tylko do liczb całkowitych
        a.integer(true);
        b.integer(true);
        //c.integer(true);

        //Rozwiązanie problemu
        Optimisation.Result wynik = model.maximise();

        //Wypisanie wyników
        System.out.println(wynik.toString());
        System.out.println(model.toString());

    }

}