package cn.honghuroad.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.sys.entity.FeeConfigEntity;

import java.util.Map;

/**
 * 费用配置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-02-22 23:07:24
 */
public interface FeeConfigService extends IService<FeeConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

