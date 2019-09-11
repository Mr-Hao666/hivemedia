package cn.hivemedia.dao;

import cn.hivemedia.entity.WxPostDataInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-09 20:27:03
 */
public interface WxPostDataInfoDao extends BaseMapper<WxPostDataInfoEntity> {

    WxPostDataInfoEntity selectByOutTradeNo(String outTradeNo);
}
