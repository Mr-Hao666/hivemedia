package cn.hivemedia.service.impl;

import cn.hivemedia.dao.KeywordConfigDao;
import cn.hivemedia.entity.KeywordConfigEntity;
import cn.hivemedia.utils.Query;
import cn.hivemedia.service.KeywordConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



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

    @Override
    public List<KeywordConfigEntity> getList(Integer size) {
        return this.baseMapper.selectList(new EntityWrapper<KeywordConfigEntity>()
                .eq("del_flag",0)
                .orderBy("sort_no", true).last(" limit 0," + size));
    }

}
