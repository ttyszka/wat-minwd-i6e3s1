import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
@Builder
public class Offer {
    private String name;
    private String location;
    private String price;
    private String picture;
    private ArrayList<String> overview;
    private String link;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nazwa: ").append(name).append("\tLokalizacja: ").append(location)
                .append("\tCena: ").append(price)
                .append("\nZdjecie: ").append(picture)
                .append("\nLink: ").append(link);

        for(String s : overview){
            sb.append("\n").append(s);
        }
        return sb.toString();
    }
}
