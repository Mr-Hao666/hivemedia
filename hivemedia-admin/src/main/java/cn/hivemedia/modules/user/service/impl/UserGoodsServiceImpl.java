package cn.hivemedia.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.user.dao.UserGoodsDao;
import cn.hivemedia.modules.user.entity.UserGoodsEntity;
import cn.hivemedia.modules.user.service.UserGoodsService;
import java.util.Map;
import org.springframework.stereotype.Service;

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
