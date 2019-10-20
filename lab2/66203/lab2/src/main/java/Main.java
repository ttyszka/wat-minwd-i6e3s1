import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    static String miasto;
    static String dzielnica;
    static OfferType offerType;
    static PropertyType propertyType;
    static int pageId = 1;
    static Connection connectionPage;
    static Document singleOfferDocument;
    static String url;
    static ArrayList<Offer> offers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> links;
        loadData();
        url = createURL(offerType, propertyType, miasto, dzielnica, pageId);
        do {
            int i = 0;
            connectionPage = Jsoup.connect(url);
            links = getLinks(connectionPage.get().getElementsByTag("article"));
            for (String l : links) {
                i++;
                singleOfferDocument = Jsoup.connect(l).get();
                offers.add(getSingleOffer(singleOfferDocument, l));
                if (i % 4 == 0) System.out.println((float) i / links.size() * 100f + "%");
            }
            System.out.println("Page done!");
        } while (nextPage());
        JsonCreator jsonCreator = JsonCreator.builder().filename(miasto + "_" + dzielnica + "_" + offerType + "_" + propertyType).build();
        jsonCreator.putArray(offers);
        jsonCreator.saveJSON();
        System.out.println("All done!");
        System.out.println("\nPokazac wszystko? [Y/n]");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().toLowerCase().equals("y")) {
            for (int i = 0; i < 5; i++)
                System.out.println(offers.get(i));
        }

    }

    static ArrayList<String> getLinks(Elements elements) {
        ArrayList<String> ret = new ArrayList<>();
        for (Element e : elements) {
            if (e.attr("data-featured-name").equals("listing_no_promo"))
                ret.add(e.getElementsByTag("a").attr("href"));
        }
        System.out.println("Returned " + ret.size() + " links");
        return ret;
    }

    static String createURL(final OfferType offerType, final PropertyType propertyType, final String miasto, final String dzielnica, final int pageId) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.otodom.pl/")
                .append(offerType).append("/")
                .append(propertyType).append("/")
                .append(miasto.toLowerCase()).append("/");
        if (dzielnica != null) sb.append(dzielnica.toLowerCase()).append("/?");
        sb.append("search%5Border%5D=created_at_first%3Adesc&")
                .append("page=").append(pageId);
        return sb.toString();
    }

    static boolean nextPage() throws IOException {
        Elements elements;
        elements = connectionPage.get().head().getElementsByAttributeValue("rel", "next");
        if (elements.isEmpty()) return false;
        String page = elements.get(0).attr("href");
        if (page == null) {
            return false;
        }
        url = page;
        return true;
    }

    private static void loadData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto");
        miasto = scanner.nextLine();
        System.out.println("Podaj dzielnice (jesli jest)");
        dzielnica = scanner.nextLine();
        System.out.println("Podaj typ oferty [Sprzedaz/Wynajem]");
        offerType = OfferType.getOfferType(scanner.nextLine());
        System.out.println("Podaj typ nieruchomosci [dom/mieszkanie]");
        propertyType = PropertyType.getPropertyType(scanner.nextLine());
    }

    static Offer getSingleOffer(Document document, String link) throws IOException {
        ArrayList<String> overview = new ArrayList<>();
        String name = document.getElementsByTag("header").get(0).getElementsByTag("h1").text();
        String location = document.getElementsByTag("header").get(0).getElementsByTag("div").get(3).text();
        String price = document.getElementsByTag("header").get(0).getElementsByTag("div").get(4).text();
        String pictureSrc = document.getElementsByTag("picture").get(0).getElementsByTag("img").attr("src");
        for (Element e : document.getElementsByClass("section-overview").get(0).getElementsByTag("li")) {
            overview.add(e.text());
        }
        return Offer.builder()
                .name(name)
                .location(location)
                .price(price)
                .picture(getBase64EncodedImage(pictureSrc))
                .link(link)
                .overview(overview)
                .build();
    }

    static String getBase64EncodedImage(String imageURL) throws IOException {
        URL url = new URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = IOUtils.toByteArray(is);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
