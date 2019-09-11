package cn.hivemedia.modules.goods.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.goods.entity.GoodsSizeEntity;
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
public interface GoodsSizeService extends IService<GoodsSizeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List queryGoodsSizeVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);
    GoodsSizeVo queryGoodsSizeVo(Long id);
}

