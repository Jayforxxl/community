package life.chao.community.controller;

import life.chao.community.entity.R;
import life.chao.community.service.AuthorizeService;
import life.chao.community.dto.AccessTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/28 16:28
 */

@Controller
@Slf4j
public class AuthorizeController {

    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;

    @Value("${github.callbackUrl}")
    private String redirectUri;

    @Autowired
    private AuthorizeService authorizeService;

    /**
     * 1、发起Get请求获取得到code和state(这一步直接放在html中做了)
     * https://github.com/login/oauth/authorize?client_id=Iv1.a306d99d869f6b7f&redirect_uri=http://localhost:8088/callback&state=community&login
     *
     * 2、发起Post请求获取得到accessToken
     * https://github.com/login/oauth/access_token
     * 请求参数为组合
     *
     * PS:此处不能用R类包装,必须返回返回地址
     * */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state, HttpServletRequest request){
        if (StringUtils.isEmpty(code)){
            log.error("获取不到Git账户的code值");
        }
        if (StringUtils.isEmpty(state)){
            log.error("获取不到Git账户的state值");
        }
        AccessTokenDto dto = new AccessTokenDto();
        dto.setClient_id(clientId);
        dto.setClient_secret(clientSecret);
        dto.setCode(code);
        dto.setRedirect_uri(redirectUri);
        dto.setState(state);

        return authorizeService.callback(dto,request);
    }



}
