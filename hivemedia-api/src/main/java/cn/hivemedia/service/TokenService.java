package cn.hivemedia.service;

import cn.hivemedia.entity.TokenEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author ZengXiong
 * @Description 用户token信息
 * @Date 2018/11/23 10:40
 */
public interface TokenService extends IService<TokenEntity> {

    TokenEntity queryByToken(String token);

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return 返回token信息
     */
    TokenEntity createToken(long userId);

    /**
     * 设置token过期
     *
     * @param userId 用户ID
     */
    void expireToken(long userId);
}
