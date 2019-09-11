package cn.hivemedia.modules.oss.service.impl;

import cn.hivemedia.modules.oss.entity.SysOssEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.oss.dao.SysOssDao;
import cn.hivemedia.modules.oss.service.SysOssService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysOssEntity> page = this.selectPage(
                new Query<SysOssEntity>(params).getPage()
        );

        return new PageUtils(page);
    }
}
