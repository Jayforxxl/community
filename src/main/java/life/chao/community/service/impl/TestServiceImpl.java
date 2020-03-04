package life.chao.community.service.impl;

import life.chao.community.common.dao.UserMapper;
import life.chao.community.common.model.User;
import life.chao.community.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/3 17:05
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;

    public void test(User user){
        userMapper.insert(user);
    }
}
