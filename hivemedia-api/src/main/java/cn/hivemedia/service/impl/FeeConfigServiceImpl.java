package cn.hivemedia.service.impl;

import cn.hivemedia.dao.FeeConfigDao;
import cn.hivemedia.entity.FeeConfigEntity;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.service.FeeConfigService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("feeConfigService")
public class FeeConfigServiceImpl extends ServiceImpl<FeeConfigDao, FeeConfigEntity> implements FeeConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FeeConfigEntity> page = this.selectPage(
                new Query<FeeConfigEntity>(params).getPage(),
                new EntityWrapper<FeeConfigEntity>()
        );

        return new PageUtils(page);
    }

}
