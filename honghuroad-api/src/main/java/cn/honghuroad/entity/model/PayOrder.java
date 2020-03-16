package cn.honghuroad.entity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenzq
 * Date: 2019/1/8 下午4:20
 **/
@Data
public class PayOrder implements Serializable {

    private Integer id;

    private Integer payType;

    private Integer orderType;

    private String outTradeNo;

    private String totalAmount;

    private String body;

    private String subjecy;

}
