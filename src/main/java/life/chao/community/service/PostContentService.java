package life.chao.community.service;

import com.github.pagehelper.PageInfo;
import life.chao.community.dto.PostContentDto;

import java.util.LinkedHashMap;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/28 15:32
 */
public interface PostContentService {

    PageInfo<LinkedHashMap<String,Object>> getContentList(PageInfo<PostContentDto> page);
}
