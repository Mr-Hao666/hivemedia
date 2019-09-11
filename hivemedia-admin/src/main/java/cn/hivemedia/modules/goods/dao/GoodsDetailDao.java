package cn.hivemedia.modules.goods.dao;

import cn.hivemedia.modules.goods.entity.GoodsDetailEntity;
import cn.hivemedia.modules.goods.vo.GoodsDetailsVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsDetailDao extends BaseMapper<GoodsDetailEntity> {
    List<GoodsDetailsVo> queryGoodsDetailVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);
}
