package com.yiii.provider.controller;


import com.yiii.provider.entity.Yi;
import com.yiii.provider.service.MainService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "提供方")
public class MainController {




    @Autowired
    private MainService mainService;

    @RequestMapping("/getString")
    public Yi getString(){
       Yi result= mainService.getString();

        return result;
    }

}
