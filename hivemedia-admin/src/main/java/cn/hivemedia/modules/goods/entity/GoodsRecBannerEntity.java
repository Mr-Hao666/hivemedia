package cn.hivemedia.modules.goods.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 商品推荐banner表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_goods_rec_banner")
public class GoodsRecBannerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * tab  1:球鞋 2:潮服 3:背包 4:其他
     */
    private Integer tabId;
    /**
     * 图片id
     */
    private Long picId;
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
    /**
     * 创建人id
     */
    private Integer createBy;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新人id
     */
    private Integer updateBy;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 数据状态 0:正常 1:删除
     */
    private Integer delFlag;
    // 类型1商品id 2url
    private Integer type;


}
