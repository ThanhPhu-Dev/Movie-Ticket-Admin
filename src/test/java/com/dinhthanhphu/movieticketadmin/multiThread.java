package com.dinhthanhphu.movieticketadmin;

import com.dinhthanhphu.movieticketadmin.config.AsynchConfiguration;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class multiThread implements Runnable{



    @Async("asyncExecutor")
    CompletableFuture<String> testThread1() throws InterruptedException {

        Thread.sleep(2000L);
        System.out.println("funtion: "+System.currentTimeMillis());
        return CompletableFuture.completedFuture("a");
    }

    String testThread2() throws InterruptedException {
        Thread.sleep(2000L);
        System.out.println("funtion: "+System.currentTimeMillis());
        return "a";
    }

    @SneakyThrows
    @Override
    public void run() {
        testThread2();
    }
}
