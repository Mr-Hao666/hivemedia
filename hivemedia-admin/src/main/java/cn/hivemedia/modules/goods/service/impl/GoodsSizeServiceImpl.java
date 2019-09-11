package cn.hivemedia.modules.goods.service.impl;

import cn.hivemedia.modules.goods.service.GoodsSizeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.goods.dao.GoodsSizeDao;
import cn.hivemedia.modules.goods.entity.GoodsSizeEntity;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.GoodsSizeVo;
import org.springframework.stereotype.Service;

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

    @Override
    public List queryGoodsSizeVoList(Map<String, Object> params) {
        return baseMapper.queryGoodsSizeVoList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }
    @Override
    public GoodsSizeVo queryGoodsSizeVo(Long id) {
        return baseMapper.queryGoodsSizeVo(id);
    }


}
