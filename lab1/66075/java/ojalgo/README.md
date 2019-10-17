numer albumu 66075


Zadanie 2. Przedsiębiorstwo produkuje dwa wyroby. Do ich produkcji zużywa się m.in. dwa limitowane surowce. Zużycie tych surowców na jednostkę każdego z wyrobów, dopuszczalne limity zużycia oraz zyski jednostkowe ze sprzedaży podano w tabeli poniżej.

Wyroby Zużycie surowca na jednostkę Zysk jednostkowy [zł] I II

W1 8 7 2

W2 16 4 4

Limit zużycia surowca 96000 56000

1. Ile należy wyprodukować wyrobu W1, a ile W2, aby nie przekraczając limitów zmaksymalizować zysk ze sprzedaży wyrobów?

2. Jak zmieni się rozwiązanie, jeśli proces produkcyjny pozwala na wyprodukowanie maksymalnie 5000 szt. wyrobu W1, oraz maksymalnie 4000 szt. wyrobu W2?

Model :
1) 8W1 + 16W2 < 96000
    7W1 + 4W2 < 56000
    2W1 + 4W3 -> MAX

2)  8W1 + 16W2 < 5000
        7W1 + 4W2 < 4000
        2W1 + 4W3 -> MAX

   Rozwiazanie
   2)
   OPTIMAL 1250.0 @ { 1, 312 }
   ############################################
   0 <= A: 1 (2)
   0 <= B: 312 (4)
   0 <= Ograniczenie 2: 1255.0 <= 4000
   0 <= Zuzycie na jednostke I: 625.0 <= 625
      A >= 0
     B >= 0
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

        final Expression ograniczenie_1 = model.addExpression("Zuzycie na jednostke I").lower(0).upper(5000); // 2 wersja
        ograniczenie_1.set(a, 8).set(b, 16);
       // final Expression ograniczenie_1 = model.addExpression("Zuzycie na jednostke I").lower(0).upper(96000); // 1 wersja
     //   ograniczenie_1.set(a, 8).set(b, 16);
        //Ograniczenie względem drugiej cechy półproduktów
        //Ustalenie wartości drugiej cechy dla każdego półproduktu

        final Expression ograniczenie_2 = model.addExpression("Ograniczenie 2").lower(0).upper(4000);  // 2 wersja
        ograniczenie_2.set(a,7).set(b, 4);
      //final Expression ograniczenie_2 = model.addExpression("Ograniczenie 2").lower(0).upper(56000); // 1 wersja
        //ograniczenie_2.set(a,7).set(b, 4);
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





   1)0 <= A: 0 (2)
     0 <= B: 6000 (4)
     0 <= Ograniczenie 2: 24000.0 <= 56000
     0 <= Zuzycie na jednostke I: 12000.0 <= 12000
     A >= 0
     B >= 0
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

