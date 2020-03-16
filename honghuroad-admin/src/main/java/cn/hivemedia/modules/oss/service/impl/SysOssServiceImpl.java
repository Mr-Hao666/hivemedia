package cn.honghuroad.modules.oss.service.impl;

import cn.honghuroad.modules.oss.entity.SysOssEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;
import cn.honghuroad.modules.oss.dao.SysOssDao;
import cn.honghuroad.modules.oss.service.SysOssService;
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
