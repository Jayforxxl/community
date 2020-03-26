package life.chao.community.service.impl;

import life.chao.community.common.dao.PostContentMapper;
import life.chao.community.common.model.PostContent;
import life.chao.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/24 16:33
 */

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PostContentMapper postContentMapper;

    @Override
    public int insert(PostContent postContent) {
        return postContentMapper.insert(postContent);
    }
}
