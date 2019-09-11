package cn.hivemedia.modules.goods.dao;

import cn.hivemedia.modules.goods.entity.GoodsSizeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.hivemedia.modules.goods.vo.GoodsSizeVo;

import java.util.List;
import java.util.Map;

/**
 * 商品尺码关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsSizeDao extends BaseMapper<GoodsSizeEntity> {

    List<GoodsSizeVo> queryGoodsSizeVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);

    GoodsSizeVo queryGoodsSizeVo(Long id);
}
