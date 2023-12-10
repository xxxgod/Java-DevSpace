package com.wykd.event.test;

public class TestThreadJoin {

    public static void main(String[] args) {


        System.out.println(Thread.currentThread().getName());


        Thread thread  = new Thread(()->{
            System.out.println(Thread.currentThread().getName());
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
