package com.wykd.event.test;

import com.alibaba.fastjson.JSON;
import com.wykd.event.vo.User;
import com.wykd.event.vo.UserRegisterEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.support.GenericApplicationContext;


public class TestApplicationEvent {


    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();

        //监听
        context.addApplicationListener(event -> {
            System.out.println("==================" + Thread.currentThread().getName());
            System.out.println("==================" + event); });

        context.refresh();


        System.out.println(JSON.toJSONString(context.getBeanDefinitionNames()));

        System.out.println("#######################" + Thread.currentThread().getName());
        System.out.println("#######################");
        context.publishEvent(new PayloadApplicationEvent<>("hello i am a applicatoin event","123456"));

        context.publishEvent(new UserRegisterEvent("hello i am a UserRegisterEvent",new User()));


        context.close();
    }

}
