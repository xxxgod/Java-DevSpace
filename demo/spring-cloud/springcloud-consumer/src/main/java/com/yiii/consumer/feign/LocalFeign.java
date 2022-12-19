package com.yiii.consumer.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "provider")
public interface LocalFeign {

    @GetMapping("/getName")
    public String getName();



}
