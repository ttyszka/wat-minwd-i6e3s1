import model.Car;
import model.FuelType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

class OtomotoScraperService {

  private Document document;

  List<Car> scrapeFromPageUrl(String url) throws IOException {
    List<Car> result = new ArrayList<>();

    connect(url);

    do {
      List<Car> pageResult = scrapePage();
      result.addAll(pageResult);
    } while (nextPage());

    return result;
  }

  private List<Car> scrapePage() throws IOException {
    List<Car> cars = new ArrayList<>();

    Elements articles = document.getElementsByTag("article");

    for (Element carArticle : articles) {
      Long adId = getAdId(carArticle);
      String title = getTitle(carArticle);
      String subtitle = getSubtitle(carArticle);
      String year = getYear(carArticle);
      String mileage = getMileage(carArticle);
      String engineCapacity = getEngineCapacity(carArticle);
      FuelType fuelType = getFuelType(carArticle);
      String price = getPrice(carArticle);
      String city = getCity(carArticle);
      String region = getRegion(carArticle);
      String imgUrl = getImageUrl(carArticle);
      String imgContent = getImageContent(imgUrl);
      String link = getLink(carArticle);

      Car car = new Car(adId, title, subtitle, year, mileage, engineCapacity, fuelType, price, city, region, imgUrl,
          imgContent, link);
      System.out.println("Car advertisement ID: " + car.getId() + " scraped successfully.");
      cars.add(car);
    }
    return cars;
  }

  private void connect(String url) {
    try {
      document = Jsoup.connect(URLDecoder.decode(url, "UTF-8")).userAgent(
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36")
          .get();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    System.out.println("Connected to url: " + url);
  }

  private Long getAdId(Element article) {
    return Long.parseLong(article.attr("data-ad-id"));
  }

  private String getTitle(Element article) {
    Element titleElement = article.getElementsByClass("offer-title__link").first();
    return titleElement.attr("title");
  }

  private String getSubtitle(Element article) {
    Element subtitleElement = article.getElementsByClass("offer-item__subtitle").first();
    return subtitleElement != null ? subtitleElement.text() : null;
  }

  private String getYear(Element article) {
    Element yearElement = article.getElementsByAttributeValue("data-code", "year").first();
    return yearElement != null ? yearElement.text() : null;
  }

  private String getMileage(Element article) {
    Element mileageElement = article.getElementsByAttributeValue("data-code", "mileage").first();
    return mileageElement != null ? mileageElement.text() : null;
  }

  private String getEngineCapacity(Element article) {
    Element engineCapacityElement = article.getElementsByAttributeValue("data-code", "engine_capacity").first();
    return engineCapacityElement != null ? engineCapacityElement.text() : null;
  }

  private FuelType getFuelType(Element article) {
    Element fuelTypeElement = article.getElementsByAttributeValue("data-code", "fuel_type").first();
    String value = fuelTypeElement != null ? fuelTypeElement.text() : null;
    if ("benzyna".equalsIgnoreCase(value)) {
      return FuelType.PB;
    } else if ("diesel".equalsIgnoreCase(value)) {
      return FuelType.ON;
    } else if ("lpg".equalsIgnoreCase(value)) {
      return FuelType.PB_LPG;
    } else if ("cng".equalsIgnoreCase(value)) {
      return FuelType.PB_CNG;
    } else if ("elektryczny".equalsIgnoreCase(value)) {
      return FuelType.ELECTRIC;
    } else if ("etanol".equalsIgnoreCase(value)) {
      return FuelType.ETANOL;
    } else if ("hybryda".equalsIgnoreCase(value)) {
      return FuelType.HYBRID;
    } else if ("wodór".equalsIgnoreCase(value)) {
      return FuelType.HYBRID;
    } else {
      return FuelType.OTHER;
    }
  }

  private String getCity(Element article) {
    Element cityElement = article.getElementsByClass("ds-location-city").first();
    return cityElement != null ? cityElement.text() : null;
  }

  private String getRegion(Element article) {
    Element regionElement = article.getElementsByClass("ds-location-region").first();
    String value = regionElement.text();
    return value.substring(value.indexOf("(") + 1, value.indexOf(")"));
  }

  private String getPrice(Element article) {
    Element priceElement = article.getElementsByClass("offer-price__number").first();
    return priceElement.text();
  }

  private String getImageUrl(Element article) {
    Element itemPhotoDiv = article.getElementsByClass("offer-item__photo-link").first();
    Element imgElement = itemPhotoDiv.getElementsByTag("img").first();

    return imgElement != null ? imgElement.absUrl("data-src") : null;
  }

  private String getImageContent(String imageUrl) throws IOException {
    return imageUrl != null ? getBase64EncodedImage(imageUrl) : null;
  }

  private String getLink(Element article) {
    Element linkElement = article.getElementsByClass("offer-title__link").first();
    return linkElement.attr("href");
  }

  private Boolean nextPage() {
    Optional<String> urlOptional = Optional.ofNullable(document.getElementsByClass("om-pager rel")).map(Elements::first)
        .map(el -> el.getElementsByAttributeValue("rel", "next")).map(Elements::first).map(link -> link.attr("href"));
    if (urlOptional.isPresent()) {
      connect(urlOptional.get());
      return true;
    } else {
      return false;
    }
  }

  private String getBase64EncodedImage(String imageURL) throws IOException {
    java.net.URL url = new java.net.URL(imageURL);
    InputStream is = url.openStream();
    byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
    return Base64.getEncoder().encodeToString(bytes);
  }

}