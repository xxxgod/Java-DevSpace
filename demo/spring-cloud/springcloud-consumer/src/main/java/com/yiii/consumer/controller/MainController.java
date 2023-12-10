package com.yiii.consumer.controller;

import com.netflix.discovery.shared.Application;
import com.yiii.consumer.feign.LocalFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Api(tags="服务消费方")
@Slf4j
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LocalFeign localFeign;

    @ApiOperation(value = "获取姓名-RestTemplate调用")
    @GetMapping("/getName")
    public String getString() {
        List<ServiceInstance> eureka_client_provider = discoveryClient.getInstances("provider");
        log.info("获取到服务提供方==>"+eureka_client_provider);

        ServiceInstance serviceInstance = eureka_client_provider.get(0);
        log.info("服务提供方主机信息==>"+"\n"+serviceInstance);
        log.info("IP为==>"+serviceInstance.getHost());
        log.info("端口为==>"+serviceInstance.getPort());

        //String url ="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/getName";
        String url ="http://provider/getName";
        String jsonpObject= restTemplate.getForObject(url, String.class);

        return  jsonpObject;
    }

    @ApiOperation(value = "获取姓名-Feign调用")
    @GetMapping("/getNameByFeign")
    public String getName() {
        String result= localFeign.getName();
        return  result;
    }

}
