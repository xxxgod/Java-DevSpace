package com.yiii.provider.controller;


import com.yiii.provider.entity.Yi;
import com.yiii.provider.service.MainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "服务提供方")
public class MainController {


    @Autowired
    private MainService mainService;

    @Value("${server.port}")
    private Integer port;

    @ApiOperation(value = "获取姓名")
    @GetMapping("/getName")
    public Yi getString(){
       Yi result= mainService.getString();
        log.info("当前为服务端，端口为======>"+port);
        return result;
    }

}
