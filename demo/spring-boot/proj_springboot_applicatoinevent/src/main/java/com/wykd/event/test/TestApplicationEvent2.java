package com.wykd.event.test;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestApplicationEvent2 {


    public static void main(String[] args) {

        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        multicaster.setTaskExecutor(executorService);

        multicaster.addApplicationListener(event -> {
            System.out.printf("【线程：%s】 : %s \n",Thread.currentThread().getName(),event);
        });

        multicaster.multicastEvent(new PayloadApplicationEvent<>("hello","hello"));

//        executorService.shutdown();

    }

}
