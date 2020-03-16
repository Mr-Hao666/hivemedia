package cn.honghuroad.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.user.entity.TokenEntity;
import java.util.Map;

/**
 * 用户Token
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface TokenService extends IService<TokenEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

