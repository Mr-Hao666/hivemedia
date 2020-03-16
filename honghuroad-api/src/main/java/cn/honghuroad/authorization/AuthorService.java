package cn.honghuroad.authorization;

import cn.honghuroad.authorization.model.AuthorReq;
import cn.honghuroad.authorization.model.AuthorRsp;

/**
 * 第三方授权登陆接口
 *
 * @author lxy
 * @date 2019/1/3
 */
public interface AuthorService {

    AuthorRsp author(AuthorReq req);
}
