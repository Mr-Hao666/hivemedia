package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@ApiModel("用户信息")
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
	@ApiModelProperty("用户名")
	private String name;
	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	private String nickname;
	/**
	 * 身份证号码
	 */
	@ApiModelProperty("身份证号码")
	@JsonIgnore
	private String idCardNo;
	/**
	 * 电话号码
	 */
	@ApiModelProperty("电话号码")
	private String phone;
	/**
	 * 登录密码
	 */
	@ApiModelProperty("登录密码")
	private String loginPwd;
	/**
	 * 头像id
	 */
	@ApiModelProperty("头像id")
	private Long avatarId;
	/**
	 * 头像url
	 */
	@ApiModelProperty("头像url")
	@TableField(exist = false)
	private String avatarUrl;
	/**
	 * 性别 0:未知 1:男 2:女
	 */
	@ApiModelProperty("性别 0:未知 1:男 2:女")
	private Integer gender;
	/**
	 * open_id(保留字段)
	 */
	@ApiModelProperty("微信openId")
	private String wxOpenId;
	/**
	 * token(保留字段)
	 */
	@ApiModelProperty("token(保留字段)")
	@JsonIgnore
	private String wxToken;
	/**
	 * 是否实名认证 0:未认证 1:认证成功 2:认证失败
	 */
	@ApiModelProperty("是否实名认证 0:未认证 1:认证成功 2:认证失败")
	private Integer realNameAuthorized;
	/**
	 * 最近登录时间
	 */
	@ApiModelProperty("最近登录时间")
	private Date latestLoginTime;
	/**
	 * 最近登录ip
	 */
	@ApiModelProperty("最近登录ip")
	@JsonIgnore
	private String latestLoginIp;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
	/**
	 * 状态 1:正常 2:禁用 3:删除
	 */
	@ApiModelProperty("状态 1:正常 2:禁用 3:删除")
	private Integer curState;

	@ApiModelProperty("qqOpenId")
	private String qqOpenId;

	private String qqToken;

	@ApiModelProperty("weiboOpenId")
	private String weiboOpenId;

	private String weiboToken;
	/**
	 * 个性签名
	 */
	@ApiModelProperty("个性签名")
	private String personalSign;
	/**
	 * 支付宝账号
	 */
	@ApiModelProperty("支付宝账号")
	private String alipayAccount;
	/**
	 * 支付宝账号用户姓名
	 */
	@ApiModelProperty("支付宝账号用户姓名")
	@JsonIgnore
	private String alipayRealName;

}
