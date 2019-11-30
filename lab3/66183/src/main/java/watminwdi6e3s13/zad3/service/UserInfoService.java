package watminwdi6e3s13.zad3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import watminwdi6e3s13.zad3.pojo.FirstLastTweets;
import watminwdi6e3s13.zad3.pojo.TwitterUser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .setProfileImageURL(user.getOriginalProfileImageURL())
                .setProfileURL(user.getURL())
                .setScreenName(user.getScreenName());

        return twitterUser;
    }

    public List<HashtagEntity[]> getTagsFromTweets(Integer tweetsNumber, AccessToken accessToken) throws TwitterException {
        myTwitter.setOAuthAccessToken(accessToken);
        Stream<Status> statuses = myTwitter.timelines().getUserTimeline(new Paging(1, tweetsNumber)).stream();

        return statuses.map(EntitySupport::getHashtagEntities).collect(Collectors.toList());


    }

    public FirstLastTweets getFirstAndLastTweets(AccessToken accessToken) throws TwitterException {
        myTwitter.setOAuthAccessToken(accessToken);
        ResponseList<Status> statusResponseList = myTwitter.getUserTimeline();
        return new FirstLastTweets(statusResponseList.get(statusResponseList.size() - 1), statusResponseList.get(0));

    }


}
