package cn.hivemedia.modules.goods.service;

import cn.hivemedia.modules.goods.entity.GoodsRecBannerEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
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
public interface GoodsRecBannerService extends IService<GoodsRecBannerEntity> {

    PageUtils queryPage(Map<String, Object> params);
    R recommend(Long goodsId);

    List queryGoodsRecBannerVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);
    GoodsRecBannerVo queryGoodsRecBannerVo(Integer id);
}

