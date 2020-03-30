package life.chao.community.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import life.chao.community.common.model.User;
import life.chao.community.dto.PostContentDto;
import life.chao.community.service.IndexService;
import life.chao.community.service.PostContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/25 11:11
 */

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private PostContentService postContentService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (!StringUtils.isEmpty(cookies)){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("token")){
                    indexService.persistentLogin(cookie.getValue(),request);
                }
            }
        }

        //进入主页的时候分页加载获取得到分布列表
        PageInfo page = new PageInfo();
        PostContentDto dto = new PostContentDto();//预留搜索条件
        page.setList(Lists.newArrayList());
        page.getList().add(dto);
        PageInfo<List<LinkedHashMap<String,Object>>> contentList = postContentService.getContentList(page);
        model.addAttribute("contentList",contentList);

        return "index";
    }
}
