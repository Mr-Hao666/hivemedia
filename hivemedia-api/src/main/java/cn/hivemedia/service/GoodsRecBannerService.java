package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.GoodsRecBannerEntity;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 商品推荐banner表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface GoodsRecBannerService extends IService<GoodsRecBannerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<GoodsRecBannerEntity> selectList(Integer size);
}

