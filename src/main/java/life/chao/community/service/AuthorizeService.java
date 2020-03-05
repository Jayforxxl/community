package life.chao.community.service;

import life.chao.community.dto.AccessTokenDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/4 16:31
 */
public interface AuthorizeService {
    String callback(AccessTokenDto dto, HttpServletRequest request, HttpServletResponse response);

}
