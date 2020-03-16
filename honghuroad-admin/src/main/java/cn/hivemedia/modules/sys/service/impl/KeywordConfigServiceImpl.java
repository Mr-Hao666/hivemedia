package cn.honghuroad.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;
import cn.honghuroad.modules.sys.dao.KeywordConfigDao;
import cn.honghuroad.modules.sys.entity.KeywordConfigEntity;
import cn.honghuroad.modules.sys.service.KeywordConfigService;
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
