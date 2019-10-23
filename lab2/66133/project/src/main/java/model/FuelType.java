package model;

import lombok.Getter;

@Getter
public enum FuelType {
  ON("diesel"),
  PB("petrol"),
  PB_LPG("petrol-lpg"),
  PB_CNG("petrol-cng"),
  ELECTRIC("electric"),
  ETANOL("etanol"),
  HYBRID("hybrid"),
  HIDROGEN("hidrogen"),
  OTHER("other");

  String code;

  FuelType(String code) {
    this.code = code;
  }
}
