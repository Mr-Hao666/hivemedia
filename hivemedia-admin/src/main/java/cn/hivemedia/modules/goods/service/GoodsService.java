package cn.hivemedia.modules.goods.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.goods.entity.GoodsEntity;
import cn.hivemedia.modules.goods.vo.GoodsVo;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    GoodsVo queryGoodsVo(Long id);
    List<GoodsVo> queryGoodsVoList(Map<String, Object> params);
    int queryTotal(Map<String, Object> params);

    void insertGoods(GoodsEntity goods);

    void updateGoods(GoodsEntity goods);
}

