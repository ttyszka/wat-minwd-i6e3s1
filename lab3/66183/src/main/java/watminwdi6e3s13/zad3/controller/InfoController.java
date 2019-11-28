package watminwdi6e3s13.zad3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.Twitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/info")
public class InfoController {

    @Qualifier("myTwitter")
    private Twitter myTwitter;

    @Autowired
    public InfoController(Twitter myTwitter) {
        this.myTwitter = myTwitter;
    }

    @GetMapping("/overview")
    public String generalUserInfo(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().getAttribute("accessToken");
        return "user_info";
    }

}
