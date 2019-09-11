package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户消息信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@TableName("tb_user_message")
public class UserMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 消息id
	 */
	@ApiModelProperty("消息id")
	private Long messageId;
	/**
	 * 用户id
	 */
	@ApiModelProperty("接收人ID")
	private Long userId;
	/**
	 * 消息类型,1：订单通知；2：Biu好货；
	 */
	@ApiModelProperty("类型,1：订单通知；2：Biu好货；")
	private String type;
	/**
	 * 目标id
	 */
	private Integer targetId;
	/**
	 * 目标链接
	 */
	private String targetUrl;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	@ApiModelProperty("消息内容,json格式")
	private String content;
	/**
	 * 状态：1、未读；2、已读；3、删除；
	 */
	private String status;
	/**
	 * 
	 */

	private Date createdTime;
	/**
	 * 
	 */

	private Date updatedTime;

}
