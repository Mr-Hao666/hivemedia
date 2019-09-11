package cn.hivemedia.service.impl;

import cn.hivemedia.dao.DataDictionaryDao;
import cn.hivemedia.entity.DataDictionaryEntity;
import cn.hivemedia.service.DataDictionaryService;
import cn.hivemedia.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



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
