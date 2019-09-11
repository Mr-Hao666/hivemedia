package cn.hivemedia.modules.goods.dao;

import cn.hivemedia.modules.goods.entity.GoodsEntity;
import cn.hivemedia.modules.goods.vo.GoodsVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsDao extends BaseMapper<GoodsEntity> {

    GoodsVo queryGoodsVo(Long id);
    List<GoodsVo> queryGoodsVoList(Map<String, Object> params);
    int queryTotal(Map<String, Object> map);
}
