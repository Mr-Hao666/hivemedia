package cn.honghuroad.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 数据字典表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_data_dictionary")
public class DataDictionaryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据字典表主键
     */
    @TableId
    private Integer id;
    /**
     * 数据类型(保留)
     */
    private Integer dataType;
    /**
     * 名称
     */
    private String name;
    /**
     * 父id
     */
    private Integer pid;
    /**
     * key
     */
    private String reskey;
    /**
     * 排序
     */
    private Integer level;
    /**
     * 0:不隐藏 1:隐藏
     */
    private Integer ishide;
    /**
     * 描述
     */
    private String desc;
}
