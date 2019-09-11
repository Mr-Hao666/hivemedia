package cn.hivemedia.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.user.entity.TokenEntity;
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

