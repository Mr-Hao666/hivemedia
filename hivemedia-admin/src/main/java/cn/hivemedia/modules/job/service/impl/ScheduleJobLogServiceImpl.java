package cn.hivemedia.modules.job.service.impl;

import cn.hivemedia.modules.job.service.ScheduleJobLogService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.job.dao.ScheduleJobLogDao;
import cn.hivemedia.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");

        Page<ScheduleJobLogEntity> page = this.selectPage(
                new Query<ScheduleJobLogEntity>(params).getPage(),
                new EntityWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId)
        );

        return new PageUtils(page);
    }
}
