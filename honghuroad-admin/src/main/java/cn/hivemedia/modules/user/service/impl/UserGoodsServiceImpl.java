package cn.honghuroad.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;
import cn.honghuroad.modules.user.dao.UserGoodsDao;
import cn.honghuroad.modules.user.entity.UserGoodsEntity;
import cn.honghuroad.modules.user.service.UserGoodsService;
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
