package cn.hivemedia.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.user.entity.FeedbackEntity;
import java.util.Map;

/**
 * 意见反馈表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface FeedbackService extends IService<FeedbackEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

