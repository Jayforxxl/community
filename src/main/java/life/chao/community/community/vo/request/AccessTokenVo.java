package life.chao.community.community.vo.request;


import lombok.Data;

/**
 * Description:GitHub将用户重定向回您的站点
 * (PS:注意这里的属性名严格按照Github开发文档妈妈,
 * 要求是以_分割而不是驼峰,否则返回结结构是一个html,
 * 得不到最后的token结果)
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/28 17:33
 */

@Data
public class AccessTokenVo {

    //GitHub应用程序的客户端ID
    private String client_id;

    //GitHub应用程序的客户端密码
    private String client_secret;

    //步骤1的响应结果中的code
    private String code;

    //授权后将用户发送到应用程序中的URL(非必填)
    private String redirect_uri;

    //步骤1中提供的无法猜测的随机字符串(非必填)
    private String state;

}
