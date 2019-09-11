package cn.hivemedia.modules.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chenzq
 * Date: 2019/2/26 下午8:27
 **/
@Data
public class BiuGoodsMsgDto implements Serializable {
    /**
     * 订单ID
     */
    private Long saleOrderId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 尺码ID
     */
    private Long sizeId;

    /**
     * 尺码名称
     */
    private String sizeDesc;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品主图
     */
    private String picShowUrl;

    /**
     * 售价
     */
    private BigDecimal saleAmountTotal;

    /**
     * 当前用户订单售价
     */
    private BigDecimal amountTotal;

    /**
     * 头像
     */
    private String senderAvatar;

    /**
     * Biu好货消息类型 1：出售提醒；2：变现提醒；3：更高求购价提醒；
     */
    private Integer biuGoodsMsgType;


}
