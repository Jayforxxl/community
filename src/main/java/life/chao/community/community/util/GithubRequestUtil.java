package life.chao.community.community.util;

import com.alibaba.fastjson.JSON;
import life.chao.community.community.vo.request.AccessTokenVo;
import life.chao.community.community.vo.response.GithubUserVo;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/28 16:55
 */

@Component
public class GithubRequestUtil {
    @Value("${github.jsonUrl}")
    private String jsonUrl;

    @Value("${github.userUrl}")
    private String userUrl;

    public String getAccessToken(AccessTokenVo vo) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(vo));
        Request request = new Request.Builder()
                                        .url(jsonUrl)
                                        .post(body)
                                        .build();
        try (Response response = client.newCall(request).execute()) {
            String tmpTokenString = response.body().string();
            if (!StringUtils.isEmpty(tmpTokenString)){
                String tokenValue = StringUtil.getTokenValue(tmpTokenString);
                return tokenValue;
            }
        }
        return null;
    }

    public GithubUserVo getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        String url = userUrl + accessToken;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String tmp = response.body().string();
            return JSON.parseObject(tmp,GithubUserVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }









}
