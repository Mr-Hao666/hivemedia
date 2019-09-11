package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.RecGoodsEntity;

import java.util.Map;

/**
 * 
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface RecGoodsService extends IService<RecGoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

