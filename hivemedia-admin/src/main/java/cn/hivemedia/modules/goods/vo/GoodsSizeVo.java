package cn.hivemedia.modules.goods.vo;

import cn.hivemedia.modules.goods.entity.GoodsSizeEntity;
import lombok.Data;

/**
 * @author linjingze
 * @date 2019/1/13 7:38 PM
 */
@Data
public class GoodsSizeVo extends GoodsSizeEntity {
    private String sizeDesc;
    private String goodsName;
    private String artNo;

}
