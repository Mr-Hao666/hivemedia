package cn.honghuroad.modules.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;
import cn.honghuroad.modules.user.dao.UserDao;
import cn.honghuroad.modules.user.entity.UserEntity;
import cn.honghuroad.modules.user.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override public PageUtils queryPage(Map<String, Object> params) {
        Page<UserEntity> page = new Query<UserEntity>(params).getPage();
        List<UserEntity> userList = this.baseMapper.selectUserListPage(params);
        page.setTotal(this.baseMapper.countAll(params));
        page.setRecords(userList);
        return new PageUtils(page);
    }
}
