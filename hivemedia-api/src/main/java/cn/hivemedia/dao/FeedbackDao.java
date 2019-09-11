package cn.hivemedia.dao;

import cn.hivemedia.entity.FeedbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 意见反馈表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
public interface FeedbackDao extends BaseMapper<FeedbackEntity> {
    Integer submitFeedback(FeedbackEntity entity);
	
}
