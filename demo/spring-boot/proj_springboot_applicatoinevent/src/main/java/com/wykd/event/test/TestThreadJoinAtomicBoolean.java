package com.wykd.event.test;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestThreadJoinAtomicBoolean {

    public static void main(String[] args) {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        System.out.println(Thread.currentThread().getName());


        Thread thread  = new Thread(()->{
            System.out.println(Thread.currentThread().getName());

            atomicBoolean.set(true);
        });

        thread.start();

//        try {
//            thread.join();  //子线程销毁的时候，再往下执行
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(Thread.currentThread().getName());

    }

}
