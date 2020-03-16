package cn.honghuroad.entity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenzq
 * Date: 2019/3/1 上午12:30
 **/
@Data
public class MessageDto implements Serializable {
    /**
     * 消息类型,1：订单通知；2：Biu好货；
     */
    private Integer msgType;

    /**
     * Biu好货通知消息体
     */
    private BiuGoodsMsgDto biuGoodsMsgDto;

    /**
     * 订单通知消息体
     */
    private OrderMsgDto orderMsgDto;
}
