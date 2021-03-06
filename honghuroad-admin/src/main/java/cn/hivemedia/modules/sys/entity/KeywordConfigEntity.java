package cn.honghuroad.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 关键字设置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_keyword_config")
public class KeywordConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 排序（由小到大）
     */
    private Long sortNo;
    /**
     * 创建人id
     */
    private Long createBy;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新人id
     */
    private Long updateBy;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 数据状态 0:有效 1:无效
     */
    private Integer delFlag;
}
