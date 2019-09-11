package cn.hivemedia.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.GoodsEntity;

import java.util.Map;

/**
 * 商品表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:47
 */
public interface GoodsService extends IService<GoodsEntity> {

    Page queryPage(Map<String, Object> params);

    Page queryPageByIds(Map<String, Object> params,Integer pageNo,Integer pageSize);

    Page queryIndexGoodsListByPage(Map<String, Object> params);
}

