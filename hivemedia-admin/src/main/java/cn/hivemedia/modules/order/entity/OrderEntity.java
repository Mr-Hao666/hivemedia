package cn.hivemedia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单信息
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-09-16 14:51:55
 */
@Data
@TableName("t_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 单位ID
	 */
	private Long parentId;
	/**
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 小组ID
	 */
	private Long groupId;
	/**
	 * 博主ID
	 */
	private Long blogId;
	/**
	 * 平台ID
	 */
	private Long platformId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 发布日期
	 */
	private Date publishDate;
	/**
	 * 账号ID
	 */
	private Long accountId;
	/**
	 * 账号类型
	 */
	private Integer accountType;
	/**
	 * 发布方式
	 */
	private Integer publishType;
	/**
	 * 类型补充
	 */
	private String supply;
	/**
	 * 项目类型
	 */
	private Integer itemType;
	/**
	 * 项目名称
	 */
	private String itemName;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 报价
	 */
	private BigDecimal price;
	/**
	 * 扣税（按9.72%）
	 */
	private BigDecimal tax;
	/**
	 * 微任务成本
	 */
	private BigDecimal microtaskCost;
	/**
	 * 外签/外部成本
	 */
	private BigDecimal externalCost;
	/**
	 * 粉丝头条
	 */
	private BigDecimal fansCost;
	/**
	 * 计算提成（毛利润）金额
	 */
	private BigDecimal grossProfit;
	/**
	 * 其他（如承担博主差旅费）
	 */
	private BigDecimal otherCost;
	/**
	 * 提成比率
	 */
	private BigDecimal proportion;
	/**
	 * 比例提成金额
	 */
	private BigDecimal royalty;
	/**
	 * 固定提成金额
	 */
	private BigDecimal fixedRoyalty;
	/**
	 * 总提成金额
	 */
	private BigDecimal totalRoyalty;

}
