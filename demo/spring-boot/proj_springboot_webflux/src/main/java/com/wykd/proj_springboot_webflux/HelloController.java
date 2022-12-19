package com.wykd.proj_springboot_webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HelloController {


    @GetMapping("mono")
    public Mono<String> mono() {
        return Mono.just("hello webflux");
    }


    @GetMapping("mono2")
    public Mono<Object> mono2() {

        return Mono.create(monoSink -> {
            log.info("创建Mono");
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
            log.info("订阅==========>" + subscription);
        }).doOnNext(o -> {      //当订阅者收到数据时，该方法会调用
            log.info("消费==========>" + o);
        });
    }

    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello", "webflux", "spring", "boot");
    }


    /**
     * 单数据集合
     * @return
     */
    @GetMapping("mono3")
    public Mono<String> mono3() {

        // 执行顺序  11111  ->  33333  ->  22222
        System.out.println("11111"+Thread.currentThread().getName());
        System.out.println("11111");

        Mono mono =  Mono.fromSupplier(()->{
            System.out.println("22222"+Thread.currentThread().getName());
            System.out.println("22222");
            return "hello world fromSupplier";
        });

        System.out.println("33333" + Thread.currentThread().getName());
        System.out.println("33333");
        return mono;
    }













}
