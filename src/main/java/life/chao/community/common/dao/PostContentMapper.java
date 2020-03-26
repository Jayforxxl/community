package life.chao.community.common.dao;

import java.util.List;
import life.chao.community.common.model.PostContent;
import life.chao.community.common.model.PostContentExample;
import org.apache.ibatis.annotations.Param;

public interface PostContentMapper {
    int countByExample(PostContentExample example);

    int deleteByExample(PostContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PostContent record);

    int insertSelective(PostContent record);

    List<PostContent> selectByExampleWithBLOBs(PostContentExample example);

    List<PostContent> selectByExample(PostContentExample example);

    PostContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PostContent record, @Param("example") PostContentExample example);

    int updateByExampleWithBLOBs(@Param("record") PostContent record, @Param("example") PostContentExample example);

    int updateByExample(@Param("record") PostContent record, @Param("example") PostContentExample example);

    int updateByPrimaryKeySelective(PostContent record);

    int updateByPrimaryKeyWithBLOBs(PostContent record);

    int updateByPrimaryKey(PostContent record);
}