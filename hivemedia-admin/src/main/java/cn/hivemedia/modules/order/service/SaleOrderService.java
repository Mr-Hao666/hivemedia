package cn.hivemedia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.order.entity.SaleOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 出售订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-02-21 13:45:27
 */
public interface SaleOrderService extends IService<SaleOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean deleteIds(List<Long> ids);
}

