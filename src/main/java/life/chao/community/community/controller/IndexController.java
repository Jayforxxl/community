package life.chao.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/25 11:11
 */

@Controller
public class IndexController {

    @GetMapping("/index")
    public String hello(){
        return "index";
    }
}
