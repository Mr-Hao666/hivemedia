package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.WxPostDataInfoEntity;
import cn.hivemedia.entity.model.PayOrder;

import java.util.Map;

/**
 * 
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-09 20:27:03
 */
public interface WxPostDataInfoService extends IService<WxPostDataInfoEntity> {

    String nofify(Map<String, String> map);

    Map<String, Object> getWxPayParams(PayOrder payOrder);
}

