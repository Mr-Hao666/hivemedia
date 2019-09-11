package cn.hivemedia.service;

import cn.hivemedia.entity.KeywordConfigEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 关键字设置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:18
 */
public interface KeywordConfigService extends IService<KeywordConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<KeywordConfigEntity> getList(Integer size);
}

