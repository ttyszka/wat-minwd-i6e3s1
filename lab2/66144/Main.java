import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;


import java.util.*;


public class Main {


    static WebInfo webInfo = new WebInfo("https://www.x-kom.pl"
            , "sc-8wk80e-1 iBGcxa sc-73olit-0 dFuogE"
            , "sc-4el5v8-12 hSNTSR sc-1epzo6z-0 tpNZZ", "apyg3s-0 apyg3s-3 loxMbz"
            , "sc-1vco2i8-2 eAvRvA"
            , "sc-854dbh-0 sc-4el5v8-11 caxOWz sc-854dbh-2 iqbYGQ");


    public static void main(String[] arg) {
        int choice;

        System.out.println("Hello World! \n Wybierz 1 jeśli chcesz wyszukać produkt \n" +
                " Wybierz 2 jesli chcesz wyszukać kategorie \n");

        Scrapping scrapping = new Scrapping(webInfo);

        Scanner in = new Scanner(System.in);
        choice = in.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("Podaj nazwe produktu");
                String productName = in.next();
                String url = webInfo.getUrl();
                url = url + "/szukaj?q=" + productName;
                scrapping.searching(url);
                break;
            }
            case 2: {
                System.out.println("Podaj link do kategorii");
                String catLink = in.next();
                catLink = catLink.substring(0, catLink.length() - 5);
                scrapping.searching(catLink);
                break;
            }
        }
    }
}
