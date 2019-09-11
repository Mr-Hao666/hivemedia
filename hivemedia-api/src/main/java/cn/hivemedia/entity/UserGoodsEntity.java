package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:15
 */
@Data
@TableName("tb_user_goods")
public class UserGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 发售时间
	 */
	private String saleTime;
	/**
	 * 性别：0男，1女，2不限
	 */
	private Integer sex;
	/**
	 * 货号
	 */
	private String number;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 
	 */
	private String salesVolume;
	/**
	 * 
	 */
	private String classification;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 大小
	 */
	private Double size;
	/**
	 * 
	 */
	private Date createdTime;
	/**
	 * 
	 */
	private Date updatedTime;

}
