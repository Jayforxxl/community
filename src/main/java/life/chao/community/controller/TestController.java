package life.chao.community.controller;

import life.chao.community.common.model.User;
import life.chao.community.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/3 17:19
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    public void test(){
        User user = new User();
        user.setAccountId(121133L);
        user.setName("XXXX");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testService.test(user);
    }

}
