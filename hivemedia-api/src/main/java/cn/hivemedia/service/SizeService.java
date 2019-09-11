package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.SizeEntity;

import java.util.List;
import java.util.Map;

/**
 * 尺码信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface SizeService extends IService<SizeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SizeEntity> getGoodsSizeInfoList(Integer goodsId);
}

