package cn.hivemedia.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;

import cn.hivemedia.modules.sys.dao.FeeConfigDao;
import cn.hivemedia.modules.sys.entity.FeeConfigEntity;
import cn.hivemedia.modules.sys.service.FeeConfigService;


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
