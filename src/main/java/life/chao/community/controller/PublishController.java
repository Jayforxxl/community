package life.chao.community.controller;

import life.chao.community.common.dao.UserMapper;
import life.chao.community.common.model.PostContent;
import life.chao.community.common.model.User;
import life.chao.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

/**
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/2/25 11:11
 */

@Controller
public class PublishController {

    @Autowired
    private PublishService publishService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(@Valid PostContent postContent, Model model, HttpServletRequest request){

        model.addAttribute("title",postContent.getTitle());
        model.addAttribute("tag",postContent.getTag());
        model.addAttribute("description",postContent.getDescription());

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (!StringUtils.isEmpty(cookies)){
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (!StringUtils.isEmpty(user)){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if (StringUtils.isEmpty(user)){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        if (StringUtils.isEmpty(postContent.getTitle())){model.addAttribute("error","标题不能为空");return "publish";}
        if (StringUtils.isEmpty(postContent.getTag())){model.addAttribute("error","标签不能为空");return "publish";}

        postContent.setCreateUserid(user.getId());
        postContent.setCreateTime(new Timestamp(System.currentTimeMillis()));
        postContent.setCommentCount(0);
        postContent.setLikeCount(0);
        postContent.setViewCount(0);
        publishService.insert(postContent);

        return "redirect:/";
    }

}
