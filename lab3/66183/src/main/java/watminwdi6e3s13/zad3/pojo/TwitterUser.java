package watminwdi6e3s13.zad3.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Accessors(chain = true)
@Component
@Getter
@Setter
@NoArgsConstructor
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TwitterUser implements Serializable {

    private String screenName;
    private String description;
    private String email;
    private String profileImageURL;
    private String profileURL;
    private Integer followersNumber;

    @Override
    public String toString() {
        return "TwitterUser{" +
                "screenName='" + screenName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", profileURL='" + profileImageURL + '\'' +
                ", followersNumber=" + followersNumber +
                '}';
    }
}
