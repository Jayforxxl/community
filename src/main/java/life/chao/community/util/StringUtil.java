package life.chao.community.util;

import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/2 11:20
 */
@Component
public class StringUtil {
    public static String getTokenValue(String tmpString){
        String[] tmpStringList = tmpString.split("&");
        String tokenString = tmpStringList[0];
        String tokenValue = tokenString.split("=")[1];
        return tokenValue;
    }
}
