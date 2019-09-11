package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品推荐banner表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
@Data
@ApiModel("商品推荐banner")
@TableName("tb_goods_rec_banner")
public class GoodsRecBannerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("banner图ID")
	private Integer id;
	/**
	 * tab  1:球鞋 2:潮服 3:背包 4:其他
	 */
	@ApiModelProperty("tab  1:球鞋 2:潮服 3:背包 4:其他")
	private Integer tabId;
	/**
	 * 图片id
	 */
	@ApiModelProperty("图片ID")
	private Long picId;
	@TableField(exist = false)
	@ApiModelProperty("图片地址")
	private String picUrl;
	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String desc;
	/**
	 * 跳转链接
	 */
	@ApiModelProperty("跳转链接")
	private String url;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sortNo;
	/**
	 * 创建人id
	 */
	@ApiModelProperty("创建人id")
	private Integer createBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	/**
	 * 更新人id
	 */
	@ApiModelProperty("更新人id")
	private Integer updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
	/**
	 * 数据状态 0:正常 1:删除
	 */
	@ApiModelProperty("数据状态 0:正常 1:删除")
	private Integer delFlag;

	/**
	 * 图片地址
	 */
	@ApiModelProperty("图片地址")
	private String imgUrl;

}
