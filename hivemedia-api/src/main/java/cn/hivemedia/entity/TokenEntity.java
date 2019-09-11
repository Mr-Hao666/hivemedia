package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author ZengXiong
 * @Description 用户token信息管理
 * @Date 2018/11/23 09:46
 */
@Data
@TableName("tb_token")
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Long userId;
    private String token;
    /**
     * 过期时间
     */
	private Date expireTime;
    /**
     * 更新时间
     */
	private Date updateTime;
}
