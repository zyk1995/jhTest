package com.zyk.jhtest.web.rest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class TimerResource {


//    @Scheduled(cron = "0/6 * * * * ? ")
//    @Async
//    public void asyncTimer1() throws InterruptedException {
//        System.out.println(new Date(System.currentTimeMillis()) + "开始打印1000" + Thread.currentThread().getName());
//        print1000();
//        System.out.println(new Date(System.currentTimeMillis()) + "结束打印1000" + Thread.currentThread().getName());
//    }
//
//    @Scheduled(cron = "0/6 * * * * ? ")
//    @Async
//    public void asyncTimer2() throws InterruptedException {
//        System.out.println(new Date(System.currentTimeMillis()) + "开始打印1000" + Thread.currentThread().getName());
//        print1000();
//        System.out.println(new Date(System.currentTimeMillis()) + "结束打印1000" + Thread.currentThread().getName());
//    }
//
//    @Scheduled(cron = "0/6 * * * * ? ")
//    @Async
//    public void asyncTimer3() throws InterruptedException {
//        System.out.println(new Date(System.currentTimeMillis()) + "开始打印1000" + Thread.currentThread().getName());
//        print1000();
//        System.out.println(new Date(System.currentTimeMillis()) + "结束打印1000" + Thread.currentThread().getName());
//    }
//
//    @Scheduled(cron = "0/6 * * * * ? ")
//    @Async
//    public void asyncTimer4() throws InterruptedException {
//        System.out.println(new Date(System.currentTimeMillis()) + "开始打印1000" + Thread.currentThread().getName());
//        print1000();
//        System.out.println(new Date(System.currentTimeMillis()) + "结束打印1000" + Thread.currentThread().getName());
//    }
//
//    @Async
//    public void print1000() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + "线程等待");
//        Thread.sleep(10000);
//        System.out.println(Thread.currentThread().getName() + "线程等待结束");
//    }

}
