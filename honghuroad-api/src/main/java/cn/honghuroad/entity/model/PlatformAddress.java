package cn.honghuroad.entity.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenzq
 * Date: 2019/1/23 下午9:25
 **/
@Data
@ApiModel("平台收货地址")
public class PlatformAddress implements Serializable {
    @ApiModelProperty("地址")
    private String address ;
    @ApiModelProperty("收货人")
    private String name;
    @ApiModelProperty("收货手机号")
    private String phone;
}
