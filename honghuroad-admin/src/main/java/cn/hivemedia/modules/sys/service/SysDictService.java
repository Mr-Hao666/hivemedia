package cn.honghuroad.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysDictEntity> queryMacrosByValue(String type);
}

