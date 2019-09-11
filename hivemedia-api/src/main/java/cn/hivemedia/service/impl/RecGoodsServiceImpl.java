package cn.hivemedia.service.impl;

import cn.hivemedia.dao.RecGoodsDao;
import cn.hivemedia.entity.RecGoodsEntity;
import cn.hivemedia.service.RecGoodsService;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("recGoodsService")
public class RecGoodsServiceImpl extends ServiceImpl<RecGoodsDao, RecGoodsEntity> implements RecGoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RecGoodsEntity> page = this.selectPage(
                new Query<RecGoodsEntity>(params).getPage(),
                new EntityWrapper<RecGoodsEntity>()
        );

        return new PageUtils(page);
    }

}
