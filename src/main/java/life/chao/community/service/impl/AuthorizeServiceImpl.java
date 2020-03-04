package life.chao.community.service.impl;

import life.chao.community.common.dao.UserMapper;
import life.chao.community.common.model.User;
import life.chao.community.service.AuthorizeService;
import life.chao.community.util.GithubRequestUtil;
import life.chao.community.dto.AccessTokenDto;
import life.chao.community.model.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/4 16:31
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Autowired
    private GithubRequestUtil githubRequestUtil;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String callback(AccessTokenDto dto, HttpServletRequest request) {
        //1、获取得到token值
        String accessToken = null;
        try {
            accessToken = githubRequestUtil.getAccessToken(dto);
            if (!StringUtils.isEmpty(accessToken)){
                //2、获取得到user信息
                GithubUser userVo = githubRequestUtil.getUser(accessToken);
                //3、对cookie和session进行处理
                if (!StringUtils.isEmpty(userVo)){
                    User user = new User();
                    user.setToken(UUID.randomUUID().toString());
                    user.setName(userVo.getName());
                    user.setAccountId(Long.valueOf(userVo.getId()));
                    user.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    user.setModifyTime(new Timestamp(System.currentTimeMillis()));
                    userMapper.insert(user);
                    request.getSession().setAttribute("user",userVo);
                    return "redirect:/";
                }else {
                    return "redirect:/";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
