package cn.honghuroad.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.user.entity.UserGoodsEntity;
import java.util.Map;

/**
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface UserGoodsService extends IService<UserGoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

