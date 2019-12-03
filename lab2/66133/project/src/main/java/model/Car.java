package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {

  private Long id;
  private String title;
  private String subtitle;
  private String year;
  private String mileage;
  private String engineCapacity;
  private FuelType fuelType;
  private String price;
  private String city;
  private String region;
  private String imageUrl;
  private String imageContent;
  private String link;

}
