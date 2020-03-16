package cn.honghuroad.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;
import cn.honghuroad.modules.sys.dao.DataDictionaryDao;
import cn.honghuroad.modules.sys.entity.DataDictionaryEntity;
import cn.honghuroad.modules.sys.service.DataDictionaryService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl extends ServiceImpl<DataDictionaryDao, DataDictionaryEntity> implements DataDictionaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DataDictionaryEntity> page = this.selectPage(
                new Query<DataDictionaryEntity>(params).getPage(),
                new EntityWrapper<DataDictionaryEntity>()
        );

        return new PageUtils(page);
    }
}
