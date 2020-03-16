package cn.honghuroad.modules.sys.dao;

import cn.honghuroad.modules.sys.entity.SysConfigEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key，查询value
     */
    SysConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);
}
