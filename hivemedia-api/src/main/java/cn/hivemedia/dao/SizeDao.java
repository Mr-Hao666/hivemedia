package cn.hivemedia.dao;

import cn.hivemedia.entity.SizeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 尺码信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface SizeDao extends BaseMapper<SizeEntity> {

    List<SizeEntity> getGoodsSizeInfoList(Integer goodsId);
	
}
