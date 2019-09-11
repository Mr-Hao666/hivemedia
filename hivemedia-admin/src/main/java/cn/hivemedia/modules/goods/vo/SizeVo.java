package cn.hivemedia.modules.goods.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linjingze
 * @date 2019/1/14 7:47 PM
 */
@Data
public class SizeVo implements Serializable {

    private Integer id;
    /**
     * 尺码描述
     */
    private String desc;
    private String goodsTab;
    private Integer pid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
