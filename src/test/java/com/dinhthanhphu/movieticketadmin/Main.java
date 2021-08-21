package com.dinhthanhphu.movieticketadmin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Long t1 = System.currentTimeMillis();
        System.out.println("Manually complete " + t1);

        CompletableFuture<String> a = computeSomething();
        CompletableFuture<String> b = computeSomething();
        CompletableFuture.allOf(a, b);

        Long t2 = System.currentTimeMillis();
        System.out.println("Get the result: " + t2);
        String result = a.get();
        System.out.println("tong: "+ (t2-t1));
        System.out.println(result);
    }

    public static CompletableFuture<String> computeSomething() {
//        try {
//            System.out.println("Computing ... " );
//            Thread.sleep(3000);
//            System.out.println("Compute completed ... " + System.currentTimeMillis());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Computing ... " );
            try {
                Thread.sleep(3000);
                System.out.println("Compute completed ... " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Code to download and return the web page's content
            return "CompletableFuture Completed";
        });
    }
}
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        multiThread thread1 = new multiThread();
//        Long t1 = System.currentTimeMillis();
//        System.out.println("t1: " + t1);
//
//        CompletableFuture<String> test1 = thread1.testThread1();
//        CompletableFuture<String> test2 = thread1.testThread1();
//
//
//        CompletableFuture.allOf(test1, test2).join();
////
//        System.out.println("ket thuc: "+ System.currentTimeMillis());
//
////        System.out.println(test1.get() + test2.get());
//
////        Thread th1 = new Thread(thread1);
////        Thread th2 = new Thread(thread1);
////
////        th1.start();
////        th2.start();
////
////        Long t2 = System.currentTimeMillis();
////        System.out.println("t2: " + t2);
////        System.out.println("tong: " + (t2 - t1));
//    }
//}