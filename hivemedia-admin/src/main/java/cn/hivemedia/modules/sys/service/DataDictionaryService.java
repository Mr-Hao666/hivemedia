package cn.hivemedia.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.sys.entity.DataDictionaryEntity;
import java.util.Map;

/**
 * 数据字典表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface DataDictionaryService extends IService<DataDictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

