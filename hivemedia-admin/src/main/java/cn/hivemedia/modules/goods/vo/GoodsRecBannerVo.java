package cn.hivemedia.modules.goods.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author linjingze
 * @date 2019/1/13 3:59 PM
 */
@Data
public class GoodsRecBannerVo {
    private Integer id;
    /**
     * tab  1:球鞋 2:潮服 3:背包 4:其他
     */
    private String tabId;
    /**
     * 图片id
     */

    private Long picId;
    private String picUrl;

    /**
     * 描述
     */
    private String desc;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 排序
     */
    private Integer sortNo;
    // 类型1商品id 2url
    private Integer type;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
