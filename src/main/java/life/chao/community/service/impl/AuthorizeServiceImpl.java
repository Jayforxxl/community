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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String callback(AccessTokenDto dto, HttpServletRequest request, HttpServletResponse response) {
        //1、获取得到token值
        String accessToken = null;
        try {
            accessToken = githubRequestUtil.getAccessToken(dto);
            if (!StringUtils.isEmpty(accessToken)){
                //2、获取得到user信息
                GithubUser user = githubRequestUtil.getUser(accessToken);
                //3、对cookie和session进行处理
                if (!StringUtils.isEmpty(user)){
                    //4.查询数据库中是否存在改用户
                    User tmpUser = userMapper.isPresent(Long.valueOf(user.getId()));
                    if (!StringUtils.isEmpty(tmpUser)){
                        User tmp = new User();
                        tmp.setId(tmpUser.getId());
                        tmp.setModifyTime(new Timestamp(System.currentTimeMillis()));
                        userMapper.updateByPrimaryKeySelective(tmp);
                        response.addCookie(new Cookie("token",tmpUser.getToken()));
                    }else {
                        User tmp = new User();
                        tmp.setToken(UUID.randomUUID().toString());
                        tmp.setName(user.getName());
                        tmp.setAccountId(Long.valueOf(user.getId()));
                        tmp.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        userMapper.insert(tmp);
                        //request.getSession().setAttribute("user",userVo);
                        //使用数据库代替session写入的实现
                        response.addCookie(new Cookie("token",tmp.getToken()));
                    }
                }
                return "redirect:/";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
