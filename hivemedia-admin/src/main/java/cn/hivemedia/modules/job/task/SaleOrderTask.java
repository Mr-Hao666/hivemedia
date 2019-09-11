package cn.hivemedia.modules.job.task;

import cn.hivemedia.modules.order.dao.SaleOrderDao;
import cn.hivemedia.modules.order.entity.SaleOrderEntity;
import cn.hivemedia.common.enums.SaleOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 杨浩
 * @create 2019-02-22 10:03
 **/
@Slf4j
@Component("saleOrderTask")
public class SaleOrderTask {
    @Autowired
    private SaleOrderDao saleOrderDao;

    /**
     * 取消长时间未支付订单
     *
     * @param time 时长（分）
     */
    public void cancel(String time) {

        log.info("=====>>>>>我是saleOrderTask带参数的cancel方法，正在被执行，参数为：" + time);
        int index = 0;
        int size = 100;
        int orderStatus = SaleOrderStatus.UNPAID_EPOSIT.getCode();
        List<SaleOrderEntity> saleOrderEntityList = saleOrderDao.selectOutOfTime(time, orderStatus, index, size);

        while (!CollectionUtils.isEmpty(saleOrderEntityList)) {
            for (SaleOrderEntity orderEntity : saleOrderEntityList) {
                orderEntity.setCancelReason(4);
                orderEntity.setOrderStatus(SaleOrderStatus.CANCEL_TRANSACTION.getCode());
                saleOrderDao.updateById(orderEntity);
            }
            saleOrderEntityList = saleOrderDao.selectOutOfTime(time, orderStatus, index + size, size);
        }
    }
}
