package com.spring.controller;

import com.spring.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(description = "测试redis")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @ApiOperation(value = "获取用户信息",httpMethod = "GET",response = ApiResponse.class)
    @ApiImplicitParam(name = "id",value = "用户id",required = true,paramType = "query",dataType = "String")
    public Map<String,Object> getUser( String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (StringUtils.isEmpty(id)) {
                map.put("code", 101);
                map.put("msg", "参数不能为空");
            }
            map.put("code", 100);
            map.put("msg", "查询成功");
            map.put("data", userService.findById(Integer.valueOf(id)));
        } catch (Exception e) {
            map.put("code", 101);
            map.put("msg", "状态异常");
            e.printStackTrace();
        }
        return map;
    }
}
