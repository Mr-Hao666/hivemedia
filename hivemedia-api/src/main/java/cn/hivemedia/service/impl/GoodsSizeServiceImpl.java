package cn.hivemedia.service.impl;

import cn.hivemedia.dao.GoodsSizeDao;
import cn.hivemedia.entity.GoodsSizeEntity;
import cn.hivemedia.service.GoodsSizeService;
import cn.hivemedia.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



@Service("goodsSizeService")
public class GoodsSizeServiceImpl extends ServiceImpl<GoodsSizeDao, GoodsSizeEntity> implements GoodsSizeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsSizeEntity> page = this.selectPage(
                new Query<GoodsSizeEntity>(params).getPage(),
                new EntityWrapper<GoodsSizeEntity>()
        );

        return new PageUtils(page);
    }

}
