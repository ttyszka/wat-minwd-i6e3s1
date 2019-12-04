import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Scrapping {

    public Scrapping(WebInfo webInfo) {
        this.webInfo = webInfo;
    }

    WebInfo webInfo;

    void searching(String url) {

        Document page = null;
        try {
            page = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxPage = 1;
        Elements pageLimit = page.getElementsByClass(webInfo.getPageLimit());
        String max = pageLimit.attr("max");
        if (!Objects.equals(max, "")) {
            maxPage = Integer.parseInt(max);
        }
        System.out.println(maxPage);
        url = url + "&page=0";

        for (int i = 1; i < maxPage + 1; i++) {

            if (i == 1)
                url = url.replace("0", String.valueOf(i));
            else
                url = url.replace("page=" + String.valueOf(i - 1), "page=" + String.valueOf(i));

            try {
                page = Jsoup.connect(url).get();
                show(page);
                System.out.println(url);
                Thread.sleep(1500);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void show(Document page) throws IOException {


        Elements productName = page.getElementsByClass(webInfo.getProductNameClass());


        Elements price = page.getElementsByClass(webInfo.getPriceClass());


        Elements temp = page.getElementsByClass("sc-4el5v8-13 goPzRd");

        Elements photoSrc = page.getElementsByClass(webInfo.getImageClass());

        List<String> l = productName.eachText();
        List<String> k = price.eachText();
        HashMap<Integer, List> parameters = new HashMap<>();

        for (int m = 0; m < temp.size(); m++) {
            parameters.put(m, (exists(temp, m, webInfo.getParametersClass())));
        }

        for (int j = 0; j < l.size(); j++) {

            String image = Base64Pic((photoSrc.get(j)).select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src"));
            Container c = new Container(l.get(j), k.get(j), parameters.get(j), image);
            createJson(c);
        }
    }

    private void createJson(Container container) {
        Gson gson = new GsonBuilder().create();
        String message = gson.toJsonTree(container).toString();
        System.out.println(message);
    }


    private List exists(Elements temp, int m, String goalClass) {
        List check = temp.get(m).getElementsByClass(goalClass).eachText();
        if (!check.isEmpty()) {
            return check;
        } else {
            check.add("Brak opisu");
            return check;
        }
    }

    String Base64Pic(String srcUrl) {

        try {
            URL url = new URL(srcUrl);
            InputStream is = url.openStream();
            byte[] bytes = IOUtils.toByteArray(is);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            return "Brak zdjecia";
        }

    }

}
