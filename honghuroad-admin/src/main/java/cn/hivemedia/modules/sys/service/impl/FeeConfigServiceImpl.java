package cn.honghuroad.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;

import cn.honghuroad.modules.sys.dao.FeeConfigDao;
import cn.honghuroad.modules.sys.entity.FeeConfigEntity;
import cn.honghuroad.modules.sys.service.FeeConfigService;


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
