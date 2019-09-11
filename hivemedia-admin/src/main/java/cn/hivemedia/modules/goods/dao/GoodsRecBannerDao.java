package cn.hivemedia.modules.goods.dao;

import cn.hivemedia.modules.goods.entity.GoodsRecBannerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.hivemedia.modules.goods.vo.GoodsRecBannerVo;

import java.util.List;
import java.util.Map;

/**
 * 商品推荐banner表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsRecBannerDao extends BaseMapper<GoodsRecBannerEntity> {

    int queryTotal(Map<String, Object> params);

    List<GoodsRecBannerVo> queryGoodsRecBannerVoList(Map<String, Object> params);
    GoodsRecBannerVo queryGoodsRecBannerVo(Integer id);

}
