package life.chao.community.service.impl;

import life.chao.community.common.dao.UserMapper;
import life.chao.community.common.model.User;
import life.chao.community.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/5 09:59
 */

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void persistentLogin(String token,HttpServletRequest request) {
        User user = userMapper.findByToken(token);
        if (!StringUtils.isEmpty(user)){
            request.getSession().setAttribute("user",user);
        }
    }
}
