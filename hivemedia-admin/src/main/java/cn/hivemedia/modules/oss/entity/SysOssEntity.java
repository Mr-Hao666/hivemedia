package cn.hivemedia.modules.oss.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件上传
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017-03-25 12:13:26
 */
@Data
@TableName("sys_oss")
public class SysOssEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * URL地址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createDate;
}
