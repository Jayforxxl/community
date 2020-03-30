package life.chao.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.chao.community.common.dao.PostContentMapper;
import life.chao.community.dto.PostContentDto;
import life.chao.community.service.PostContentService;
import life.chao.community.util.ConvertorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/28 15:33
 */

@Service
public class PostContentServiceImpl implements PostContentService {

    @Autowired
    private ConvertorUtil convertorUtil;

    @Autowired
    private PostContentMapper postContentMapper;

    @Override
    public PageInfo<LinkedHashMap<String, Object>> getContentList(PageInfo<PostContentDto> page) {

        PostContentDto dto = convertorUtil.extractVoFromPageInfo(page,PostContentDto.class);
        int currentPage = page.getPageNum() == 0 ? 1 : page.getPageNum();
        int pageSize = page.getPageSize() == 0 ? 10 : page.getPageSize();
        PageHelper.startPage(currentPage,pageSize);

        List<LinkedHashMap<String,Object>> list =  postContentMapper.getContentList(dto);
        if (list.size() > 0){
            return new PageInfo<>(list);
        }else {
            return null;
        }
    }
}
