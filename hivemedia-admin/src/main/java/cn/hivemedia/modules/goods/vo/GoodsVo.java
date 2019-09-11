package cn.hivemedia.modules.goods.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author linjingze
 * @date 2019/1/13 10:19 AM
 */
@Data
public class GoodsVo implements Serializable{
    /**
     * 商品id
     */
    private Long id;
    /**
     * tab
     */
    private String goodsTab;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品描述
     */
    private String desc;
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
    private String picShowUrl;
    /**
     * 商品详情展示图1
     */
    private Long picDetail1;
    private String picDetail1Url;
    /**
     * 商品详情展示图2
     */
    private Long picDetail2;
    private String picDetail2Url;
    /**
     * 商品详情展示图3
     */
    private Long picDetail3;
    private String picDetail3Url;
    /**
     * 商品详情展示图4
     */
    private Long picDetail4;
    private String picDetail4Url;
    /**
     * 商品详情展示图5
     */
    private Long picDetail5;
    private String picDetail5Url;
    /**
     * 商品详情底图
     */
    private Long baseMap;
    private String baseMapUrl;


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
     * 单位计量: 1:件 2...
     */
    private String unit;
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
    private String label;
    /**
     * 销量
     */
    private Integer salesVolume;
    /**
     * 最新成交价(单位:元人民币)
     */
    private BigDecimal latestDealPrice;
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
    private String sizeListStr;
    private List<String> goodsSizeList;
    // 包邮
    private Integer isFreePost;

}
