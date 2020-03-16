package cn.honghuroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Controller
@RequestMapping("/record")
public class RecordController{

    /**
     * 列表
     */
    @GetMapping("/index")
    public String list() {
        return "index";
    }
}
