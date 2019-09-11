package cn.hivemedia.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.sys.dao.KeywordConfigDao;
import cn.hivemedia.modules.sys.entity.KeywordConfigEntity;
import cn.hivemedia.modules.sys.service.KeywordConfigService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("keywordConfigService")
public class KeywordConfigServiceImpl extends ServiceImpl<KeywordConfigDao, KeywordConfigEntity> implements KeywordConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KeywordConfigEntity> page = this.selectPage(
                new Query<KeywordConfigEntity>(params).getPage(),
                new EntityWrapper<KeywordConfigEntity>()
        );

        return new PageUtils(page);
    }
}
