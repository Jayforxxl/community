package life.chao.community.controller;

import life.chao.community.common.model.User;
import life.chao.community.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/25 11:11
 */

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (!StringUtils.isEmpty(cookies)){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("token")){
                    indexService.persistentLogin(cookie.getValue(),request);
                }
            }
        }
        return "index";
    }
}
