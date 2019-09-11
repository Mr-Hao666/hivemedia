package cn.hivemedia.service.impl;

import cn.hivemedia.dao.MessageDao;
import cn.hivemedia.dao.OrderGoodsDetailDao;
import cn.hivemedia.entity.MessageEntity;
import cn.hivemedia.service.BuyOrderService;
import cn.hivemedia.service.GoodsService;
import cn.hivemedia.utils.Query;
import cn.hivemedia.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;



@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Autowired
    private BuyOrderService buyOrderService;
    @Autowired
    private OrderGoodsDetailDao orderGoodsDetailDao;
    @Autowired
    private GoodsService goodsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MessageEntity> page = this.selectPage(
                new Query<MessageEntity>(params).getPage(),
                new EntityWrapper<MessageEntity>()
        );

        return new PageUtils(page);
    }

    


}
