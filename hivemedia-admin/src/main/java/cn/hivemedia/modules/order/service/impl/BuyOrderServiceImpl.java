package cn.hivemedia.modules.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.enums.BuyOrderStatus;
import cn.hivemedia.common.enums.SaleOrderStatus;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.order.dao.BuyOrderDao;
import cn.hivemedia.modules.order.dao.SaleOrderDao;
import cn.hivemedia.modules.order.entity.BuyOrderEntity;
import cn.hivemedia.modules.order.entity.SaleOrderEntity;
import cn.hivemedia.modules.order.service.BuyOrderService;
import cn.hivemedia.modules.user.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("buyOrderService")
public class BuyOrderServiceImpl extends ServiceImpl<BuyOrderDao, BuyOrderEntity> implements BuyOrderService {

    @Autowired
    private SaleOrderDao saleOrderDao;

    @Autowired
    private UserMessageService userMessageService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BuyOrderEntity> page = this.selectPage(
                new Query<BuyOrderEntity>(params).getPage(),
                new EntityWrapper<BuyOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean shipped(Long[] ids) {
        for (Long id : ids) {

            BuyOrderEntity buyOrderEntity = this.selectById(id);
            buyOrderEntity.setOrderStatus(BuyOrderStatus.SHIPPED.getCode());
            SaleOrderEntity saleOrderEntity = saleOrderDao.selectById(buyOrderEntity.getSaleOrderId());
            saleOrderEntity.setOrderStatus(SaleOrderStatus.SHIPPED.getCode());
            saleOrderDao.updateById(saleOrderEntity);
            //发送消息
            userMessageService.orderNotify(id,BuyOrderStatus.SHIPPED.getCode());
            this.baseMapper.updateById(buyOrderEntity);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteIds(Long[] ids) {
        for (Long id : ids) {
            BuyOrderEntity buyOrderEntity = this.selectById(id);
            buyOrderEntity.setCurState(2);
            this.baseMapper.updateById(buyOrderEntity);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unpass(Long[] ids) {
        for (Long id : ids) {
            BuyOrderEntity buyOrderEntity = this.selectById(id);
            buyOrderEntity.setOrderStatus(BuyOrderStatus.UNPASS.getCode());
            SaleOrderEntity saleOrderEntity = saleOrderDao.selectById(buyOrderEntity.getSaleOrderId());
            saleOrderEntity.setOrderStatus(SaleOrderStatus.RETURNS.getCode());
            saleOrderDao.updateById(saleOrderEntity);
            //发送消息
            userMessageService.orderNotify(id,BuyOrderStatus.UNPASS.getCode());
            this.baseMapper.updateById(buyOrderEntity);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean inInspection(Long[] ids) {
        for (Long id : ids) {
            BuyOrderEntity buyOrderEntity = this.selectById(id);
            SaleOrderEntity saleOrderEntity = saleOrderDao.selectById(buyOrderEntity.getSaleOrderId());
            saleOrderEntity.setOrderStatus(SaleOrderStatus.ALREADY_SIGNED.getCode());
            buyOrderEntity.setOrderStatus(BuyOrderStatus.IN_INSPECTION.getCode());
            saleOrderDao.updateById(saleOrderEntity);
            //发送消息
            userMessageService.orderNotify(id,BuyOrderStatus.IN_INSPECTION.getCode());
            this.baseMapper.updateById(buyOrderEntity);
        }
        return true;
    }

}
