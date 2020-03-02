package life.chao.community.community.vo.response;

import lombok.Data;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/2 10:16
 */
@Data
public class GithubUserVo {

    private String name;

    private Long id;

    //用户描述
    private String bio;

}
