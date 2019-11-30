package watminwdi6e3s13.zad3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.HashtagEntity;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import watminwdi6e3s13.zad3.pojo.FirstLastTweets;
import watminwdi6e3s13.zad3.pojo.TwitterUser;
import watminwdi6e3s13.zad3.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/info")
public class InfoController {


    private UserInfoService userInfoService;

    @Autowired
    public InfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/overview")
    public String generalUserInfo(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().getAttribute("accessToken");
        return "user_info";
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TwitterUser getGeneralUserInfo(HttpServletRequest request, HttpServletResponse response) throws TwitterException {
        AccessToken accessToken = (AccessToken) request.getSession().getAttribute("accessToken");
        return userInfoService.getUserInfo(accessToken);
    }

    @GetMapping(value = "/hashtag_cloud", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<HashtagEntity[]> getTagsFromTweets(HttpServletRequest request, @RequestParam(name = "tweets_number", defaultValue = "20") Integer tweetsNumber) throws TwitterException {
        AccessToken accessToken = (AccessToken) request.getSession().getAttribute("accessToken");
        return userInfoService.getTagsFromTweets(tweetsNumber, accessToken);
    }

    @GetMapping(value = "/tweet", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FirstLastTweets getTagsFromTweets(HttpServletRequest request) throws TwitterException {
        AccessToken accessToken = (AccessToken) request.getSession().getAttribute("accessToken");
        return userInfoService.getFirstAndLastTweets(accessToken);
    }

}
