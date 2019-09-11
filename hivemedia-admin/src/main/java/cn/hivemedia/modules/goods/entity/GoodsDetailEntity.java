package cn.hivemedia.modules.goods.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_goods_detail")
public class GoodsDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 尺码id
     */
    private Integer sizeId;
    /**
     * 押金
     */
    private BigDecimal cashPledge;
    /**
     * 单间商品状态：1，待售；2，已定；3，已售；
     */
    private Integer curState;
}
