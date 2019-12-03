import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Car;
import model.FuelType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OtomotoScraperApp {

  public static void main(String[] args) throws Exception {
    UserPreferences preferences = getUserPreferences();

    String startUrl = getUrl(preferences.getBrand(), preferences.getModel(), preferences.getFuelType(), preferences.getYearFrom(), preferences.getYearTo(), preferences.getPriceFrom(), preferences.getPriceTo());

    OtomotoScraperService otomotoScraperService = new OtomotoScraperService();
    List<Car> cars = otomotoScraperService.scrapeFromPageUrl(startUrl);
    saveToFile(preferences.getBrand(), preferences.getModel(), cars);
  }

  private static void saveToFile(String brand, String model, List<Car> cars) throws IOException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm");

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(new File(String.join("_", brand, model, format.format(new Date()) + ".json")), cars);
  }

  private static String getUrl(String filterBrand, String filterModel, FuelType fuelType, Integer filterYearFrom,
      Integer filterYearTo, Integer filterPriceFrom, Integer filterPriceTo) {
    StringBuilder sb = new StringBuilder();
    sb.append("https://www.otomoto.pl").append("/").append("osobowe").append("/").append(filterBrand).append("/")
        .append(filterModel).append("/").append("od-").append(filterYearFrom).append("/").append("?search")
        .append("[filter_enum_fuel_type][0]=").append(fuelType.getCode()).append("&search")
        .append("[filter_float_price:from]=").append(filterPriceFrom).append("&search")
        .append("[filter_float_price:to]=").append(filterPriceTo).append("&search").append("[filter_float_year:to]=")
        .append(filterYearTo);
    return sb.toString();
  }

  private static UserPreferences getUserPreferences() {
    System.out.println("Witaj w programie Scraper Otomoto.pl" +
        "\nPodaj podstawowe parametry samochodu, którym jesteś zainteresowany:");

    Scanner in = new Scanner(System.in);
    System.out.println("Podaj nazwę marki: ");
    String brand = in.next();

    System.out.println("Podaj nazwę modelu: ");
    String model = in.next();

    System.out.println("Podaj rocznik od: ");
    Integer yearFrom = in.nextInt();

    System.out.println("Podaj rocznik do: ");
    Integer yearTo = in.nextInt();

    boolean incorrectFuelType = true;
    FuelType fuelType = null;
    System.out.println("Podaj rodzaj paliwa: " + Arrays.toString(FuelType.values()));
    do {
      String fuelTypeString = in.next();

      try {
        fuelType = FuelType.valueOf(fuelTypeString);
        incorrectFuelType = false;
      } catch (Exception e) {
        System.out.println("Wybierz rodzaj paliwa z dostępnych na liście!");
      }

    } while (incorrectFuelType);

    System.out.println("Podaj cenę od: ");
    Integer priceFrom = in.nextInt();

    System.out.println("Podaj cenę do: ");
    Integer priceTo = in.nextInt();

    return new UserPreferences(brand, model, yearFrom, yearTo, fuelType, priceFrom, priceTo);
  }

  @Getter
  @AllArgsConstructor
  static class UserPreferences {
    String brand;
    String model;
    Integer yearFrom;
    Integer yearTo;
    FuelType fuelType;
    Integer priceFrom;
    Integer priceTo;
  }
}
