package cn.hivemedia.service.impl;

import cn.hivemedia.dao.GoodsDetailDao;
import cn.hivemedia.entity.GoodsDetailEntity;
import cn.hivemedia.service.GoodsDetailService;
import cn.hivemedia.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



@Service("goodsDetailService")
public class GoodsDetailServiceImpl extends ServiceImpl<GoodsDetailDao, GoodsDetailEntity> implements GoodsDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsDetailEntity> page = this.selectPage(
                new Query<GoodsDetailEntity>(params).getPage(),
                new EntityWrapper<GoodsDetailEntity>()
        );

        return new PageUtils(page);
    }

}
