package life.chao.community.community.controller;

import life.chao.community.community.util.GithubRequestUtil;
import life.chao.community.community.vo.request.AccessTokenVo;
import life.chao.community.community.vo.response.GithubUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/28 16:28
 */

@Controller
public class AuthorizeController {
    @Autowired
    private GithubRequestUtil githubRequestUtil;

    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;

    @Value("${github.callbackUrl}")
    private String redirectUri;

    /**
     * 1、发起Get请求获取得到code和state(这一步直接放在html中做了)
     * https://github.com/login/oauth/authorize?client_id=Iv1.a306d99d869f6b7f&redirect_uri=http://localhost:8088/callback&state=community&login
     *
     * 2、发起Post请求获取得到accessToken
     * https://github.com/login/oauth/access_token
     * 请求参数为组合
     *
     *
     * */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           Model model){


        AccessTokenVo vo = new AccessTokenVo();
        vo.setClient_id(clientId);
        vo.setClient_secret(clientSecret);
        vo.setCode(code);
        vo.setRedirect_uri(redirectUri);
        vo.setState(state);

        try {
            String accessToken = githubRequestUtil.getAccessToken(vo);
            if (!StringUtils.isEmpty(accessToken)){
                GithubUserVo userVo = githubRequestUtil.getUser(accessToken);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }



}
