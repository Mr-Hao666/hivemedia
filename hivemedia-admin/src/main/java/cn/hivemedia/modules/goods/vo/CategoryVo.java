package cn.hivemedia.modules.goods.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linjingze
 * @date 2019/1/13 8:14 AM
 */
@Data
public class CategoryVo implements Serializable {

    private Integer id;
    private Integer pid;
    /**
     * 图标ID
     */
    private Integer picId;
    /**
     * 排序
     */
    private Integer sortNo;

    private String name;
    private String imgUrl;
    private String pName;
}
