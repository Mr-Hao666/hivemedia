package cn.hivemedia.modules.goods.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.goods.entity.GoodsDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsDetailService extends IService<GoodsDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List queryGoodsDetailVoList(Map<String, Object> params);
    int queryTotal(Map<String, Object> params);
}

