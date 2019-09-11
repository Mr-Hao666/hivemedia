package cn.hivemedia.service;

import cn.hivemedia.entity.MessageEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.Map;

/**
 * 消息推送表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
public interface MessageService extends IService<MessageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    


}

