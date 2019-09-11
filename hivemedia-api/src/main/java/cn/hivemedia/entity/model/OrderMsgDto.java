package cn.hivemedia.entity.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenzq
 * Date: 2019/2/25 下午10:06
 **/
@Data
public class OrderMsgDto implements Serializable {
    /**
     * 订单ID
     */
    private Long orderId;

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
     * 发送消息时间
     */
    private Date sendTime;

    /**
     * 订单状态，3:已发往Biu平台；11：平台已签收，查验中；4:买家待收货(平台验货通过)；5:平台验货未通过；
     */
    private Integer orderStatus;

}
