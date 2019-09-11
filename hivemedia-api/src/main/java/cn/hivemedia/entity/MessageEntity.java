package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息推送表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
@Data
@TableName("tb_message")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 类型,1：订单通知；2：Biu好货；
	 */
	@ApiModelProperty("类型,1：订单通知；2：Biu好货；")
	private Integer type;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 消息内容
	 */
	@ApiModelProperty("消息内容,json格式")
	private String content;
	/**
	 * 接收人id(多个以逗号隔开)
	 */
	@ApiModelProperty("接收人id(多个以逗号隔开)")
	private String toUserId;
	/**
	 * 预计发送时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 推送状态 1:等待推送；2:推送中；3:推送失败；4:推送成功
	 */
	private Integer status;
	/**
	 * 1:系统通知 2:服务通知 3:活动通知(优先级更高,优先推送) ...
	 */
	private Integer level;
	/**
	 * 是否已读 0:未读 1:已读
	 */
	@ApiModelProperty("是否已读 0:未读 1:已读")
	private Integer isRead;
	/**
	 * 创建人id
	 */
	private Integer createBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createdTime;
	/**
	 * 更新时间
	 */
	private Date updatedTime;
	/**
	 * 数据状态 0:有效 1:无效
	 */
	private Integer delFlag;

}
