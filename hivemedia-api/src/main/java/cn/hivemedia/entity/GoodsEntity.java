package cn.hivemedia.entity;

import cn.hivemedia.entity.model.OrderGoods;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:47
 */
@Data
@TableName("tb_goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	@ApiModelProperty("商品id")
	private Long id;
	/**
	 * 店铺id(保留)
	 */
	@ApiModelProperty("店铺id(保留)")
	private Integer shopId;
	/**
	 * 首页筛选tab类型，1：球鞋；2：服饰；3：其他
	 */
	@ApiModelProperty("首页筛选tab类型，1：球鞋；2：服饰；3：其他")
	private Integer goodsTab;
	/**
	 * 商品名
	 */
	@ApiModelProperty("商品名")
	private String name;
	/**
	 * 商品描述
	 */
	@ApiModelProperty("商品描述")
	private String desc;
	/**
	 * 商品详情(保留字段,图文)
	 */
	@ApiModelProperty("商品详情(保留字段,图文)")
	private String detail;
	/**
	 * 商品货号(商品编号),唯一
	 */
	@ApiModelProperty("商品货号(商品编号),唯一")
	private String artNo;
	/**
	 * 商品关键字
	 */
	@ApiModelProperty("商品关键字")
	private String keyword;
	/**
	 * 商品展示图(主图)
	 */
	@ApiModelProperty("商品展示图(主图)")
	private Long picShow;

	@ApiModelProperty("商品展示图(主图)地址")
	@TableField(exist = false)
	private String picShowUrl;
	/**
	 * 商品详情展示图1
	 */
	@ApiModelProperty("商品详情展示图1")
	private Long picDetail1;
	@TableField(exist = false)
	@ApiModelProperty("商品详情展示图地址")
	private List<String> picDetailUrlList;
	/**
	 * 商品详情展示图2
	 */
	@ApiModelProperty("商品详情展示图2")
	private Long picDetail2;

	/**
	 * 商品详情展示图3
	 */
	@ApiModelProperty("商品详情展示图3")
	private Long picDetail3;

	/**
	 * 商品详情展示图4
	 */
	@ApiModelProperty("商品详情展示图4")
	private Long picDetail4;

	/**
	 * 商品详情展示图5
	 */
	@ApiModelProperty("商品详情展示图5")
	private Long picDetail5;

	/**
	 * 底图
	 */
	@ApiModelProperty("底图")
	private Long baseMap;

	/**
	 * 发售日期(暂时只到天,后期改)
	 */
	@ApiModelProperty("发售日期(暂时只到天,后期改)")
	private Date releaseDate;

	@ApiModelProperty("发售日期(字符串类型)")
	private String releaseDateStr;
	/**
	 * 所属品牌名称(便于搜索)
	 */
	@ApiModelProperty("所属品牌名称(便于搜索)")
	private String brand;
	/**
	 * 适用性别：0:不限，1:男，2:女
	 */
	@ApiModelProperty("适用性别：0:不限，1:男，2:女")
	private Integer genderFor;
	/**
	 * 销量
	 */
	@ApiModelProperty("销量")
	private Integer salesVolume;
	/**
	 * 最新成交价(单位:元人民币)
	 */
	@ApiModelProperty("最新成交价(单位:元人民币)")
	private BigDecimal latestDealPrice;

	@ApiModelProperty("最新成交价尺码规格")
	@TableField(exist = false)
	private String sizeDesc;

	/**
	 * 单位计量: 1:件 2...
	 */
	@ApiModelProperty("单位计量: 1:件 2...")
	private Integer unit;
	/**
	 * 重量
	 */
	@ApiModelProperty("重量")
	private String weight;
	/**
	 * 库存
	 */
	@ApiModelProperty("库存")
	private Integer inventory;
	/**
	 * 是否上架销售 1:是 0:否
	 */
	@ApiModelProperty("是否上架销售 1:是 0:否")
	private Integer isOnSale;
	/**
	 * 单次购买上限数
	 */
	@ApiModelProperty("单次购买上限数")
	private Integer limitTimes;
	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sortNo;
	/**
	 * 标签：1:无标签；2:热卖；3:包邮；4:折扣
	 */
	@ApiModelProperty("标签：1:无标签；2:热卖；3:包邮；4:折扣")
	private Integer label;
	/**
	 * 创建人id
	 */
	@ApiModelProperty("创建人id")
	private Integer createBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createdTime;
	/**
	 * 更新人id
	 */
	@ApiModelProperty("更新人id")
	private Integer updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updatedTime;
	/**
	 * 数据状态 0:正常 1:删除
	 */
	@ApiModelProperty("数据状态 0:正常 1:删除")
	private Integer delFlag;
	/**
	 * 审核人id
	 */
	@ApiModelProperty("审核人id")
	private Integer auditBy;
	/**
	 * 审核状态 1:待审核 2:审核通过 3:审核不通过
	 */
	@ApiModelProperty("审核状态 1:待审核 2:审核通过 3:审核不通过")
	private Integer auditState;
	/**
	 * 审核备注
	 */
	@ApiModelProperty("审核备注")
	private String auditRemark;
	/**
	 * 发售价
	 */
	@ApiModelProperty("发售价")
	private BigDecimal releasePrice;
	/**
	 * 商品详情介绍图
	 */
	@ApiModelProperty("商品详情介绍图")
	private Integer detailDescImg;
	@TableField(exist = false)
	@ApiModelProperty("商品详情介绍图地址")
	private String detailDescImgUrl;
	/**
	 * 0:否 1:是
	 */
	@ApiModelProperty("0:否 1:是")
	private Integer isRec;

	@TableField(exist = false)
	@ApiModelProperty("最近五条成交记录")
	private List<OrderGoods> goodsOrder;

	@ApiModelProperty("最高求购价")
	private BigDecimal maxPurchasePrice;

	@ApiModelProperty("最低售价")
	private BigDecimal minSalePrice;

	@ApiModelProperty("是否免邮，1:是；0：否")
	private Integer isFreePost;



	public void setGoodsImgUrl(String detailDescImgUrl,List<String> picDetailUrlList){
		this.detailDescImgUrl = detailDescImgUrl;
		if (!CollectionUtils.isEmpty(picDetailUrlList)){
			this.picDetailUrlList = picDetailUrlList;
		}
	}

	public List<Long> getpicDetailList(){
		List<Long> pics = new ArrayList<>();
		if (picDetail1!=null){
			pics.add(picDetail1);
		}
		if (picDetail2!=null){
			pics.add(picDetail2);
		}
		if (picDetail3!=null){
			pics.add(picDetail3);
		}
		if (picDetail4!=null){
			pics.add(picDetail4);
		}
		if (picDetail5!=null){
			pics.add(picDetail5);
		}
		if (baseMap!=null){
			pics.add(baseMap);
		}
		return pics;
	}

}
