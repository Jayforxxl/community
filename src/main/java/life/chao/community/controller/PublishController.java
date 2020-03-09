package life.chao.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/25 11:11
 */

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish(HttpServletRequest request){

        return "publish";
    }
}
