package cn.hivemedia.modules.goods.vo;

import cn.hivemedia.modules.goods.entity.GoodsCategoryEntity;
import lombok.Data;

/**
 * @author linjingze
 * @date 2019/1/13 3:43 PM
 */
@Data
public class GoodsCateVo extends GoodsCategoryEntity {
    private String cateName;
    private String goodsName;
    private Integer pid;
}
