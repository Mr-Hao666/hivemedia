package cn.hivemedia.service.impl;

import cn.hivemedia.dao.SizeDao;
import cn.hivemedia.entity.SizeEntity;
import cn.hivemedia.service.SizeService;
import cn.hivemedia.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



@Service("sizeService")
public class SizeServiceImpl extends ServiceImpl<SizeDao, SizeEntity> implements SizeService {

    @Autowired
    private SizeDao sizeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SizeEntity> page = this.selectPage(
                new Query<SizeEntity>(params).getPage(),
                new EntityWrapper<SizeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SizeEntity> getGoodsSizeInfoList(Integer goodsId) {
        return sizeDao.getGoodsSizeInfoList(goodsId);
    }

}
