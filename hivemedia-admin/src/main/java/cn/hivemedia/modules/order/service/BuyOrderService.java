package cn.hivemedia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.order.entity.BuyOrderEntity;

import java.util.Map;

/**
 * 购买订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-02-21 13:45:27
 */
public interface BuyOrderService extends IService<BuyOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean shipped(Long[] ids);

    boolean deleteIds(Long[] ids);

    boolean unpass(Long[] ids);

    boolean inInspection(Long[] ids);
}

