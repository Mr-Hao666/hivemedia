package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.GoodsSizeEntity;

import java.util.Map;

/**
 * 商品尺码关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface GoodsSizeService extends IService<GoodsSizeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

