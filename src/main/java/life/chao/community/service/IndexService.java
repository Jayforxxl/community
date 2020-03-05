package life.chao.community.service;

import life.chao.community.common.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/5 09:58
 */
public interface IndexService {

    void persistentLogin(String token,HttpServletRequest request);


}
