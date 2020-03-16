package cn.honghuroad.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.sys.entity.KeywordConfigEntity;
import java.util.Map;

/**
 * 关键字设置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface KeywordConfigService extends IService<KeywordConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

