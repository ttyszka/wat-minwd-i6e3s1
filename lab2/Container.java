import java.util.List;

public class Container {

    Container(String productName, String price, List parameters, String image) {
        this.productName = productName;
        this.price = price;
        this.parameters = parameters;
        this.image = image;
    }

    private String productName, price, image;
    List parameters;


}
