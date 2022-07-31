package com.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    boolean b = semaphore.tryAcquire(1, 100, TimeUnit.MILLISECONDS);
                    if (b) {
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
