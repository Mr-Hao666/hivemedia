package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.GoodsDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface GoodsDetailService extends IService<GoodsDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

