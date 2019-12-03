import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Application {

    private static final String PAGE_URL = "https://pl.wiktionary.org/wiki/Indeks:Angielski_-_Ptaki";
    private static final String HTTPS = "https:";

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> polishNames = new ArrayList<String>();
        ArrayList<String> englishNames = new ArrayList<String>();
        ArrayList<String> images = new ArrayList<String>();
        List<Item> items = new ArrayList<Item>();

        Document doc = Jsoup.connect(PAGE_URL).get();

        Elements elements = doc.select("tbody > tr > td");
        if (elements.isEmpty()) {
            throw new RuntimeException("No definitions found.");
        }


        for (Element e : elements) {
            String name = e.text();
//            System.out.println(name);
            if (!name.equals("")) {
                names.add(name);
            }
        }


        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) {
                englishNames.add(names.get(i));
            } else {
                polishNames.add(names.get(i));
            }
        }


        Elements imgElements = doc.getElementsByClass("image");
        for (Element e : imgElements) {

            Element newUrl = e.select("img").first();

            String absoluteUrl = newUrl.attr("src");

            images.add(getBase64EncodedImage(HTTPS + absoluteUrl));
        }

        Item item;
        for (int i = 0; i < images.size(); i++) {
            item = new Item(polishNames.get(i), englishNames.get(i), images.get(i));
            items.add(item);
        }



        String arrayToJson = objectMapper.writeValueAsString(items);

        System.out.println(arrayToJson);

        objectMapper.writeValue(new File("src\\main\\resources\\file.json"), arrayToJson );

    }

    public static String getBase64EncodedImage(String imageURL) throws IOException {
        java.net.URL url = new java.net.URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = IOUtils.toByteArray(is);
        return Base64.getEncoder().encodeToString(bytes);
    }
}

class Item {
    Item(String polishName, String englishName, String image64) {
        this.polishName = polishName;
        this.englishName = englishName;
        this.image64 = image64;
    }

    private String polishName;
    private String englishName;
    private String image64;

    public String getPolishName() {
        return polishName;
    }

    public void setPolishName(String polishName) {
        this.polishName = polishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getImage64() {
        return image64;
    }

    public void setImage64(String image64) {
        this.image64 = image64;
    }
}
