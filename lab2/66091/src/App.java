import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {

        Scraper scraper = new Scraper();
        System.out.println("Podaj interesujący cię przedmiot lub link do kategorii!\n");
        Scanner in = new Scanner(System.in);
        scraper.init(in.next());
    }
}
