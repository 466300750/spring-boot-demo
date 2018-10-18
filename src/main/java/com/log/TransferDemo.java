package com.log;

import com.log.log4j.Log4JRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        TransactionFactory transactionFactory = new TransactionFactory();
        for (int i = 0; i < 10; i++) {
            Transfer tx = transactionFactory.newInstance();
            Runnable task = new Log4JRunnable(tx);
            executor.submit(task);
        }
        executor.shutdown();
    }
}
