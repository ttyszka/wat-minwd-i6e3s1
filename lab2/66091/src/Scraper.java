import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.ProductModel;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

@AllArgsConstructor
@NoArgsConstructor
public class Scraper {

    private int pagesNumber;
    private int productIterator = 1;
    private final String BASE_URL = "https://www.morele.net";
    private String searchPath = "/wyszukiwarka/0/0/,,,,,,,,0,,,,/";
    private int PAGE = 1;
    private String query = "/?q=";
    private String URL_TO_PARSE;
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    private LinkedList<String> namesArray = new LinkedList<>();
    private LinkedList<String> pricesArray = new LinkedList<>();
    private TreeMap<Integer,String> specificsMap = new TreeMap<>();
    private LinkedList<String> imagesArray = new LinkedList<>();
    private LinkedList<ProductModel> productModels = new LinkedList<>();

    public void init(String userRequest) throws IOException {

        if (userRequest.startsWith("http")) {
            scrapePagesNumber(userRequest + ",,,,,,,,0,,,,/" + PAGE + "/");
            start(1, userRequest);
        }else {
            scrapePagesNumber(BASE_URL + searchPath + PAGE + query + userRequest);
            start(2, userRequest);}
    }
    public void start(int option, String userRequest) throws IOException {
        if(option == 1){
            URL_TO_PARSE = userRequest + ",,,,,,,,0,,,,/" + PAGE + "/";
        } else {
            URL_TO_PARSE = BASE_URL + searchPath + PAGE + query + userRequest;
        }
        System.out.println(URL_TO_PARSE);
        Document doc = Jsoup.connect(URL_TO_PARSE).get();
        Elements productsNames = doc.select(".cat-product-name a");
        Elements productsPrices = doc.select(".cat-product-right > .cat-product-price > .price-new");
        Elements productsSpecifics = doc.select(".cat-product-features > .cat-product-feature");
        Elements productsImages = doc.select(".cat-product-left > .cat-product-image > .product-image");

        for (Element productName : productsNames) {
            namesArray.add(productName.attr("title"));
        }

        for (Element productPrice : productsPrices) {
            pricesArray.add(productPrice.html());
        }

        StringBuilder specs = new StringBuilder();
        for (Element productSpecifics : productsSpecifics) {
            if (productSpecifics.nextElementSibling().html().replaceAll("</?b>", "")
                    .equals("Pokaż więcej")) {
                specificsMap.put(productIterator, specs.toString());
                specs = new StringBuilder();
                productIterator++;
            } else {
                specs.append(productSpecifics.nextElementSibling().html().replaceAll("</?b>", "")).append(" ");
            }
        }

        for (Element productImage : productsImages) {

            imagesArray.add(getBase64EncodedImage(productImage.attr("src")));
        }

        if(pagesNumber>PAGE++) {
            start(option, userRequest);
        } else {
            summary();
            createJson(productModels);
        }
    }

    private void summary () {
        for (int i=0; i<namesArray.size()-1; i++) {
            productModels.add(new ProductModel(namesArray.get(i), pricesArray.get(i), specificsMap.get(i + 1), imagesArray.get(i)));
        }
    }

    private void createJson(List<ProductModel> productModels) {
        Gson gson = new GsonBuilder().create();
        for (ProductModel model : productModels) {
            System.out.println(gson.toJsonTree(model));
        }
    }

    private void scrapePagesNumber(String urlToParse) throws IOException {
        Document doc = Jsoup.connect(urlToParse).get();
        char[] pages = doc.select(".pagination-wrapper a").html().replaceAll("<.*|\n","").toCharArray();
        int nr = pages.length;
        pagesNumber = Character.getNumericValue(pages[nr-1]);
    }

    private String getBase64EncodedImage(String imageURL) throws IOException {
        URLConnection openConnection = new URL(imageURL).openConnection();
        openConnection.addRequestProperty("User-Agent", USER_AGENT);
        InputStream is = openConnection.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }
}