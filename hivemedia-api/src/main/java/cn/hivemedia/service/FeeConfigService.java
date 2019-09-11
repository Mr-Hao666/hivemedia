package cn.hivemedia.service;

import cn.hivemedia.entity.FeeConfigEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.Map;

/**
 * 费用配置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface FeeConfigService extends IService<FeeConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

