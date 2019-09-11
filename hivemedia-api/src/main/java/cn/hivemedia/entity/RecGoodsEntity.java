package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@TableName("tb_rec_goods")
public class RecGoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 排序
     */
    private Integer sortNo;

}
