package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 商品尺码关联表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
@Data
@TableName("tb_goods_size")
public class GoodsSizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 尺码id
	 */
	private Long sizeId;
	/**
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 最高求购价格
	 */
	private BigDecimal expectMaxPrice;
	/**
	 * 最低售价
	 */
	private BigDecimal expectMinPrice;

}
