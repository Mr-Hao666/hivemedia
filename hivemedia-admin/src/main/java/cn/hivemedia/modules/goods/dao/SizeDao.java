package cn.hivemedia.modules.goods.dao;

import cn.hivemedia.modules.goods.entity.SizeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.hivemedia.modules.goods.vo.SizeVo;

import java.util.List;
import java.util.Map;

/**
 * 尺码信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface SizeDao extends BaseMapper<SizeEntity> {

    int queryTotal(Map<String, Object> params);

    List<SizeVo> querySizeVoList(Map<String, Object> params);

    SizeVo querySizeVo(Integer id);
}
