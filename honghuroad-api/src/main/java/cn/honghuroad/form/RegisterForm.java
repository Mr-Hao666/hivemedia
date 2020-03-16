package cn.honghuroad.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ZengXiong
 * @Description 注册表单
 * @Date 2018/11/23 09:50
 */
@Data
@ApiModel(value = "注册表单")
public class RegisterForm {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
