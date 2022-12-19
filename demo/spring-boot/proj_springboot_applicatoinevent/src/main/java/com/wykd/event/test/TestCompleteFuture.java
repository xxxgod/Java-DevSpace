package com.wykd.event.test;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class TestCompleteFuture {


    public static void main(String[] args) {


        /**
         * CompletableFuture   将上一次执行的结果，用于下一次的业务逻辑的入参
         */

        System.out.println(Thread.currentThread().getName());

        CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return "hello";
        }).thenApplyAsync(result->{
            System.out.println(Thread.currentThread().getName());
            return result+ " world";
        }).thenAccept(System.out::println)
                .whenComplete((v,error)->{
                    System.out.println("执行结束");
                })
                .join();


        System.out.println("kfjelfjlekjf");



//        gebi((a,b) -> {
//
//
//        });


    }

    public static void gebi(Laowanginterface laowang){



    }


    private static void println(String x) {
        System.out.println("=======");
    }



    @FunctionalInterface
    public interface Laowanginterface<T ,E > {

        void qugebi();

    }

}
