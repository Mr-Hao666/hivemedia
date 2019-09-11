package cn.hivemedia.authorization;

import cn.hivemedia.authorization.model.AuthorReq;
import cn.hivemedia.authorization.model.AuthorRsp;

/**
 * 第三方授权登陆接口
 *
 * @author lxy
 * @date 2019/1/3
 */
public interface AuthorService {

    AuthorRsp author(AuthorReq req);
}
