package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.DataDictionaryEntity;

import java.util.Map;

/**
 * 数据字典表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
public interface DataDictionaryService extends IService<DataDictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

