package cn.honghuroad.msg;

import cn.honghuroad.msg.model.MsgReq;

public interface ICaptchaApi {

    /**
     * 发送短信  -- 验证码
     *
     * @param req 入参
     * @return
     */
    String send(MsgReq req);

    /**
     * 校验验证码是否正确
     * @param req
     * @return
     */
    boolean checkCode(MsgReq req);


}
