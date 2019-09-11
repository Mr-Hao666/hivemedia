package cn.hivemedia.controller;

import cn.hivemedia.entity.UserMessageEntity;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.UserMessageService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import cn.hivemedia.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨浩
 * @create 2018-12-30 15:00
 **/
@RestController
@RequestMapping("/api/message")
@Api(tags = "消息通知")
public class ApiMessageController {

    @Autowired
    private UserMessageService userMessageService;

    @ApiOperation("查询通知列表")
    @GetMapping("/queryMessageList")
    public BaseResult<List<UserMessageEntity>> queryMessageList(
            @ApiParam("userId") @RequestParam("userId") Integer userId,
            @ApiParam("消息类型,1：订单通知；2：Biu好货") @RequestParam("type") Integer type,
            @ApiParam("当前页码") @RequestParam("pageNo") Integer pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("type", type);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return BaseResult.success(userMessageService.queryPage(params));
    }

    @ApiOperation("查询未读消息数量")
    @GetMapping("/queryUnreadMessageNum")
    public BaseResult queryUnreadMessageNum(@ApiParam("userId") @RequestParam("userId") Integer userId) {

        Map<String, Object> map = new HashMap<>(1);
        //表单校验
        List<UserMessageEntity> messageEntities = userMessageService.selectList(new EntityWrapper<UserMessageEntity>().eq("userId", userId));
        map.put("sum", messageEntities.size());

        //用户登录
        return BaseResult.success(map);
    }

    @ApiOperation("设置消息已读")
    @GetMapping("/updateMessageRead")
    public R updateMessageRead(@ApiParam("消息Id") @RequestParam("msgId") Integer msgId) {
        //表单校验

        UserMessageEntity userMessageEntity = new UserMessageEntity();
        userMessageEntity.setStatus("2");
        userMessageService.update(userMessageEntity, new EntityWrapper<UserMessageEntity>().eq("messageId", msgId));

        //用户登录
        return R.ok();
    }
}
