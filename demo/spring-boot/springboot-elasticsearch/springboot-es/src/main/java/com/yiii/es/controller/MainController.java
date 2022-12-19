package com.yiii.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiii.es.service.ESService;
import com.yiii.es.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "es测试案例")
@RestController
public class MainController {


    @Autowired
    private ESService service;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public int add() {
        return service.add();
    }

    @ApiOperation(value = "查询")
    @GetMapping("/get")
    public Object get(String id) throws IOException {
        return service.get(id);
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("/getAll")
    public List<User> getAll() throws IOException{
        List<User> list = new ArrayList<User>();
        for(int i=1;i<11;i++) {
            Object json = service.get(String.valueOf(i));
            list.add(JSONObject.parseObject(json.toString(), User.class));
        }
        return list;
    }

    @ApiOperation(value = "更新-有问题")
    @PostMapping("/update")
    public boolean edit(User user,String id) throws IOException{
        return service.edit(user, id);
    }


    @ApiOperation(value = "删除")
    @PostMapping("/del")
    public boolean del(String id) throws IOException{
        return service.del(id);
    }

    @ApiOperation(value = "条件查询-有问题")
    @GetMapping("/select")
    public List<User> select(String key,String value,int start, int end,int type) throws IOException {
        return service.select(key, value, start, end, type);
    }


}
