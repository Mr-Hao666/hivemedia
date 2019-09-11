package cn.hivemedia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 订单退货表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_order_returns")
public class OrderReturnsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 退货编号
     */
    private String returnsNo;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 物流单号
     */
    private String expressNo;
    /**
     * 收货人姓名
     */
    private String consigneeRealname;
    /**
     * 联系电话
     */
    private String consigneeTelphone;
    /**
     * 收货地址
     */
    private String consigneeAddress;
    /**
     * 邮政编码
     */
    private String consigneeZip;
    /**
     * 物流方式 1:ems...
     */
    private Integer logisticsType;
    /**
     * 退货运费
     */
    private BigDecimal logisticsFee;
    /**
     * 物流状态
     */
    private Integer orderlogisticsStatus;
    /**
     * 物流描述
     */
    private String orderlogisticsDesc;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;
}
