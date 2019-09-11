package cn.hivemedia.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.sys.dao.MessageDao;
import cn.hivemedia.modules.sys.entity.MessageEntity;
import cn.hivemedia.modules.sys.service.MessageService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MessageEntity> page = this.selectPage(
                new Query<MessageEntity>(params).getPage(),
                new EntityWrapper<MessageEntity>()
        );

        return new PageUtils(page);
    }
}
