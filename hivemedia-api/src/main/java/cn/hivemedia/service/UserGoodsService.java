package cn.hivemedia.service;

import cn.hivemedia.entity.UserGoodsEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:15
 */
public interface UserGoodsService extends IService<UserGoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

