package life.chao.community.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

/**
 * Description:分页查询时所需过滤条件类
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/3/28 16:27
 */
@Component
public class ConvertorUtil {

    ObjectMapper mapper = new ObjectMapper();

    public <U, V> V extractVoFromPageInfo(PageInfo<V> voInfo, Class<V> voClz) {
        if (voInfo.getList() == null || voInfo.getList().size() <= 0)
            return null;
        Object voObj = voInfo.getList().get(0);
        if (voObj == null)
            return null;
        V vo = mapper.convertValue(voObj, voClz);
        return vo;
    }

}
