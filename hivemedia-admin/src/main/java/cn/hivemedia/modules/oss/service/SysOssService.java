package cn.hivemedia.modules.oss.service;

import cn.hivemedia.modules.oss.entity.SysOssEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.Map;

/**
 * 文件上传
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService extends IService<SysOssEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
