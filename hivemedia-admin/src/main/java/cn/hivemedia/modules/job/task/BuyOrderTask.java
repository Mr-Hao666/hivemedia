package cn.hivemedia.modules.job.task;

import cn.hivemedia.modules.order.dao.BuyOrderDao;
import cn.hivemedia.modules.order.entity.BuyOrderEntity;
import cn.hivemedia.common.enums.BuyOrderStatus;
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
@Component("buyOrderTask")
public class BuyOrderTask {
    @Autowired
    private BuyOrderDao buyOrderDao;

    /**
     * 取消长时间未支付订单
     *
     * @param time 时长（分）
     */
    public void cancel(String time) {

        log.info("=====>>>>>我是buyOrderTask带参数的cancel方法，正在被执行，参数为：" + time);
        int index = 0;
        int size = 100;
        int orderStatus = BuyOrderStatus.UNPAID_EPOSIT.getCode();
        List<BuyOrderEntity> buyOrderEntityList = buyOrderDao.selectOutOfTime(time, orderStatus, index, size);

        while (!CollectionUtils.isEmpty(buyOrderEntityList)) {
            for (BuyOrderEntity orderEntity : buyOrderEntityList) {
                orderEntity.setCancelReason(4);
                orderEntity.setOrderStatus(BuyOrderStatus.CANCEL_TRANSACTION.getCode());
                buyOrderDao.updateById(orderEntity);
            }
            buyOrderEntityList = buyOrderDao.selectOutOfTime(time, orderStatus, index + size, size);
        }
    }/**
     * 取消长时间未支付订单
     *
     * @param time 时长（分）
     */
    public void cancelUnpay(String time) {

        log.info("=====>>>>>我是buyOrderTask带参数的cancelUnpay方法，正在被执行，参数为：" + time);
        int index = 0;
        int size = 100;
        int orderStatus = BuyOrderStatus.PENDING_PAYMENT.getCode();
        List<BuyOrderEntity> buyOrderEntityList = buyOrderDao.selectOutOfUpdateTime(time, orderStatus, index, size);

        while (!CollectionUtils.isEmpty(buyOrderEntityList)) {
            for (BuyOrderEntity orderEntity : buyOrderEntityList) {
                orderEntity.setCancelReason(4);
                orderEntity.setOrderStatus(BuyOrderStatus.CANCEL_TRANSACTION.getCode());
                buyOrderDao.updateById(orderEntity);
            }
            buyOrderEntityList = buyOrderDao.selectOutOfUpdateTime(time, orderStatus, index + size, size);
        }
    }

}
