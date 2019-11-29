package watminwdi6e3s13.zad3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import watminwdi6e3s13.zad3.pojo.TwitterUser;

@Service
public class UserInfoService {


    @Qualifier("myTwitter")
    private Twitter myTwitter;

    @Autowired
    public UserInfoService(Twitter myTwitter) {
        this.myTwitter = myTwitter;
    }


    public TwitterUser getUserInfo(AccessToken accessToken) throws TwitterException {
        myTwitter.setOAuthAccessToken(accessToken);
        User user = myTwitter.users().showUser(myTwitter.getScreenName());
        TwitterUser twitterUser = new TwitterUser();

        twitterUser
                .setDescription(user.getDescription())
                .setEmail(user.getEmail())
                .setFollowersNumber(user.getFollowersCount())
                .setProfileURL(user.getURL())
                .setScreenName(user.getScreenName());

        return twitterUser;
    }


}
