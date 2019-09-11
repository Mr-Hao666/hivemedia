package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 费用配置表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@TableName("tb_fee_config")
public class FeeConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 银行转账费比例
	 */
	@ApiModelProperty("银行转账费比例")
	private BigDecimal bankFeePercent;
	/**
	 * 商品查验费用
	 */
	@ApiModelProperty("商品查验费用")
	private BigDecimal goodsCheckFee;
	/**
	 * 包装费
	 */
	@ApiModelProperty("包装费")
	private BigDecimal pacFee;
	/**
	 * 手续费
	 */
	@ApiModelProperty("手续费")
	private BigDecimal proceduresFee;
	/**
	 * 保证金比例
	 */
	@ApiModelProperty("保证金比例")
	private BigDecimal depositPercent;
	/**
	 * 快递费
	 */
	@ApiModelProperty("快递费")
	private BigDecimal expressFee;

	/**
	 * 平台费用
	 */
	@ApiModelProperty("平台费用")
	private BigDecimal platformFee;

}
