package cn.honghuroad.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 系统配置信息
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2016年12月4日 下午6:43:36
 */
@Data
@TableName("sys_config")
public class SysConfigEntity {
    @TableId
    private Long id;
    @NotBlank(message = "参数名不能为空")
    private String paramKey;
    @NotBlank(message = "参数值不能为空")
    private String paramValue;
    private String remark;
}
