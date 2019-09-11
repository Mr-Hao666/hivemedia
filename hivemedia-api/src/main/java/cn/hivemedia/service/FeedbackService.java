package cn.hivemedia.service;

import cn.hivemedia.entity.FeedbackEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.Map;

/**
 * 意见反馈表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
public interface FeedbackService extends IService<FeedbackEntity> {

    Page queryPage(Map<String, Object> params);

    /**
     * 提交反馈信息
     *
     * @param userId
     * @param content
     * @return
     */
    Integer submitFeedback(Long userId, String content);
}

