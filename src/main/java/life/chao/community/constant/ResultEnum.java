package life.chao.community.constant;

import lombok.Getter;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/4 16:57
 */
@Getter
public enum ResultEnum {
    SUCCESS(1,"成功"),
    FAILURE(0,"失败"),
    USER_NEED_AUTHORITIES(201,"用户未登录"),
    USER_LOGIN_FAILED(202,"用户账号或密码错误"),
    USER_LOGIN_SUCCESS(203,"用户登录成功"),
    USER_NO_ACCESS(204,"用户无权访问"),
    USER_IS_LOCKED(205,"用户已被锁定，无法进行操作"),
    USER_LOGOUT_SUCCESS(11,"用户登出成功"),

    TOKEN_IS_BLACKLIST(301,"token已失效，请重新登陆！"),
    TOKEN_IS_BAD(302,"无效的令牌，操作被禁止！"),

    LOGIN_IS_OVERDUE(101,"登录已失效"),

    URL_IS_ERROR(500,"访问的数据不存在"),
    SYSTEM_IS_ERROR(999,"系统出错！"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @deprecation:通过code返回枚举
     */
    public static ResultEnum parse(int code){
        ResultEnum[] values = values();
        for (ResultEnum value : values) {
            if(value.getCode() == code){
                return value;
            }
        }
        throw  new RuntimeException("Unknown code of ResultEnum");
    }
}
