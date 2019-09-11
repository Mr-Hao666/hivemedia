package cn.hivemedia.service.impl;

import cn.hivemedia.dao.UserGoodsDao;
import cn.hivemedia.entity.UserGoodsEntity;
import cn.hivemedia.utils.Query;
import cn.hivemedia.service.UserGoodsService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



@Service("userGoodsService")
public class UserGoodsServiceImpl extends ServiceImpl<UserGoodsDao, UserGoodsEntity> implements UserGoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserGoodsEntity> page = this.selectPage(
                new Query<UserGoodsEntity>(params).getPage(),
                new EntityWrapper<UserGoodsEntity>()
        );

        return new PageUtils(page);
    }

}
