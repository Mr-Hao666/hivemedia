package cn.hivemedia.modules.goods.vo;

import cn.hivemedia.modules.goods.entity.GoodsDetailEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linjingze
 * @date 2019/1/14 7:26 PM
 */
@Data
public class GoodsDetailsVo extends GoodsDetailEntity implements Serializable{
    private String sizeDesc;
    private String goodsName;
    private String artNo;
}
