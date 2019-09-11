package cn.hivemedia.modules.goods.service.impl;

import cn.hivemedia.modules.goods.service.SizeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.goods.dao.SizeDao;
import cn.hivemedia.modules.goods.entity.SizeEntity;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.SizeVo;
import cn.hivemedia.modules.sys.entity.SysDictEntity;
import cn.hivemedia.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sizeService")
public class SizeServiceImpl extends ServiceImpl<SizeDao, SizeEntity> implements SizeService {

    @Autowired
    private SysDictService sysDictService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String desc = params.get("desc") == null ? "" : params.get("desc").toString();

        Page<SizeEntity> page = this.selectPage(
                new Query<SizeEntity>(params).getPage(),
                new EntityWrapper<SizeEntity>().like(StringUtils.isNotBlank(desc), "`desc`", desc)
        );

        return new PageUtils(page);
    }

    @Override
    public SizeVo querySizeVo(Integer id) {
        return baseMapper.querySizeVo(id);
    }

    @Override
    public List querySizeVoList(Map<String, Object> params) {
        return baseMapper.querySizeVoList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }

    @Override
    public List<SizeVo> queryAll2Tree(Map<String, Object> params) {
        List<SizeVo> sizeVoList = querySizeVoList(params);
        List<SysDictEntity> goodsTabList = sysDictService.queryMacrosByValue("goodsTab");
        List<SizeVo> pSizeList = Lists.newArrayList();
        int i = 1;
        for(SysDictEntity goodsTab: goodsTabList){
            SizeVo sizeVo = new SizeVo();
            int pid = i*-1;
            sizeVo.setId(pid);
            sizeVo.setDesc("");
            sizeVo.setGoodsTab(goodsTab.getValue());
            pSizeList.add(sizeVo);
            for(SizeVo size : sizeVoList){
                if(StringUtils.equals(size.getGoodsTab(), sizeVo.getGoodsTab())){
                    size.setPid(pid);
                }
            }
            i++;
        }
        sizeVoList.addAll(pSizeList);
        return sizeVoList;
    }
}
