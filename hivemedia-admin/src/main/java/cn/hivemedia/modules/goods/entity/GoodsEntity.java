package cn.hivemedia.modules.goods.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hivemedia.modules.goods.vo.SizeVo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_goods")
public class GoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId
    private Long id;
    /**
     * 店铺id(保留)
     */
    private Integer shopId;
    /**
     * tab
     */
    private Integer goodsTab;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品描述
     */
    private String desc;
    /**
     * 商品详情(保留字段,图文)
     */
    private String detail;
    /**
     * 商品货号(商品编号),唯一
     */
    private String artNo;
    /**
     * 商品关键字
     */
    private String keyword;
    /**
     * 商品展示图(主图)
     */
    private Long picShow;
    /**
     * 商品详情展示图1
     */
    private Long picDetail1;
    /**
     * 商品详情展示图2
     */
    private Long picDetail2;
    /**
     * 商品详情展示图3
     */
    private Long picDetail3;
    /**
     * 商品详情展示图4
     */
    private Long picDetail4;
    /**
     * 商品详情展示图5
     */
    private Long picDetail5;

    /**
     * 商品详情底图
     */
    private Long baseMap;

    /**
     * 发售日期(暂时只到天,后期改)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    /**
     * 所属品牌名称(便于搜索)
     */
    private String brand;
    /**
     * 适用性别：0:不限，1:男，2:女
     */
    private Integer genderFor;
    /**
     * 销量
     */
    private Integer salesVolume;
    /**
     * 最新成交价(单位:元人民币)
     */
    private BigDecimal latestDealPrice;
    /**
     * 单位计量: 1:件 2...
     */
    private Integer unit;
    /**
     * 重量
     */
    private String weight;
    /**
     * 库存
     */
    private Integer inventory;
    /**
     * 是否上架销售 1:是 0:否
     */
    private Integer isOnSale;
    /**
     * 单次购买上限数
     */
    private Integer limitTimes;
    /**
     * 排序
     */
    private Integer sortNo;
    /**
     * 标签：1:无标签；2:热卖；3:包邮；4:折扣
     */
    private Integer label;
    /**
     * 创建人id
     */
    private Integer createBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人id
     */
    private Integer updateBy;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 数据状态 0:正常 1:删除
     */
    private Integer delFlag;
    /**
     * 审核人id
     */
    private Integer auditBy;
    /**
     * 审核状态 1:待审核 2:审核通过 3:审核不通过
     */
    private Integer auditState;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 是否包邮 0 不包 1包
     */
    private Integer isFreePost;

    @TableField(exist = false)
    private List<SizeVo> goodsSizeList;
}
