package cn.hivemedia.modules.goods.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.goods.entity.SizeEntity;
import cn.hivemedia.modules.goods.vo.SizeVo;

import java.util.List;
import java.util.Map;

/**
 * 尺码信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface SizeService extends IService<SizeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SizeVo querySizeVo(Integer id);

    List querySizeVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);

    List<SizeVo> queryAll2Tree(Map<String, Object> params);
}

