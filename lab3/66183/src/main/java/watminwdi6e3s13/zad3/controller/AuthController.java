package watminwdi6e3s13.zad3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Login controller serve every request to auth twitterj API
 * it also save twitter access token in memory
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Qualifier("myTwitter")
    private Twitter myTwitter;

    @Autowired
    public AuthController(Twitter myTwitter) {
        this.myTwitter = myTwitter;

    }

    /**
     * use when need to login to twitter api and get access token to get personal user info
     * it redirect user to twitter login page
     *
     * @param request
     * @param response
     * @throws TwitterException
     * @throws IOException
     */
    @PostMapping("/signin")
    public void signIn(HttpServletRequest request, HttpServletResponse response) throws TwitterException, IOException {


        // setup callback URL
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback");

        // get request object and save to session
        RequestToken requestToken = myTwitter.getOAuthRequestToken(callbackURL.toString());
        request.getSession().setAttribute("requestToken", requestToken);

        // redirect to twitter authentication URL
        response.sendRedirect(requestToken.getAuthenticationURL());

    }

    /**
     * method which is used when user want to sign out of application
     * access token is destroyed
     *
     * @param request
     * @param response
     * @throws TwitterException
     * @throws IOException
     */
    @GetMapping("/signout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws TwitterException, IOException {

        request.getSession().removeAttribute("accessToken");
    }

    /**
     * After being authorize twitter flow redirect to this controller method when accessToken is saved to memory
     *
     * @param request
     * @param response
     * @return
     * @throws TwitterException
     */
    @GetMapping("/callback")
    public String callbackHandler(HttpServletRequest request, HttpServletResponse response) throws TwitterException {

        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");

        AccessToken accessToken = myTwitter.getOAuthAccessToken(requestToken, verifier);
        request.getSession().removeAttribute("requestToken");
        request.getSession().setAttribute("accessToken", accessToken);

        return "redirect:/info/overview";
    }
}
