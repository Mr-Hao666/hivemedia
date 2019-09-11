package cn.hivemedia.modules.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 用户信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 身份证号码
     */
    private String idCardNo;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 登录密码
     */
    private String loginPwd;
    /**
     * 头像id
     */
    private Long avatarId;

    /**
     * 性别 0:未知 1:男 2:女
     */
    private Integer gender;
    /**
     * token(保留字段)
     */
    private String token;
    /**
     * 是否实名认证 0:未认证 1:认证成功 2:认证失败
     */
    private Integer realNameAuthorized;
    /**
     * 最近登录时间
     */
	private Date latestLoginTime;
    /**
     * 最近登录ip
     */
    private String latestLoginIp;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 状态 1:正常 2:禁用 3:删除
     */
    private Integer curState;

    /** -- 头像url -- **/
    private String avatarUrl;

    /**
     * 支付宝账号
     */
    private String alipayAccount;
    /**
     * 支付宝账号用户姓名
     */
    private String alipayRealName;

}
