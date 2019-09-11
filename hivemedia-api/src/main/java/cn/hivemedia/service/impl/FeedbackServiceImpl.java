package cn.hivemedia.service.impl;

import cn.hivemedia.dao.FeedbackDao;
import cn.hivemedia.entity.FeedbackEntity;
import cn.hivemedia.utils.Query;
import cn.hivemedia.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("feedbackService")
public class FeedbackServiceImpl extends ServiceImpl<FeedbackDao, FeedbackEntity> implements FeedbackService {

    @Override
    public Page queryPage(Map<String, Object> params) {
        Page<FeedbackEntity> page = this.selectPage(
                new Query<FeedbackEntity>(params).getPage(),
                new EntityWrapper<FeedbackEntity>()
        );

        return page;
    }

    @Override
    public Integer submitFeedback(Long userId, String content) {
        FeedbackEntity entity=new FeedbackEntity();
        entity.setUserId(userId);
        entity.setContent(content);
        return baseMapper.submitFeedback(entity);
    }


}
