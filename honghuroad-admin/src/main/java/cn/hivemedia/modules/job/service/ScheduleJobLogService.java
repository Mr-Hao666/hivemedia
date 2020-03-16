package cn.honghuroad.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.job.entity.ScheduleJobLogEntity;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author yhao
 * @since 1.2.0 2016-11-28
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
