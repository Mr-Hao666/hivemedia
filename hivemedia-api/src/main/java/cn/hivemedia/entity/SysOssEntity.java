package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017-03-25 12:13:26
 */
@Data
@TableName("sys_oss")
@ApiModel("文件")
public class SysOssEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("ossId")
    private Long id;
    /**
     * URL地址
     */
    @ApiModelProperty("url")
    private String url;
    /**
     * 创建时间
     */

    private Date createDate;
}
