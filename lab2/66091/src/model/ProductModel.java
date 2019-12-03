package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

    private String name;
    private String price;
    private String parameters;
    private String image;

    public ProductModel(String name, String price, String parameters, String image) {
        this.name = name;
        this.price = price;
        this.parameters = parameters;
        this.image = image;
    }
}
