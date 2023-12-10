package com.wykd.event.test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCompleteService {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService completionService = new ExecutorCompletionService(executorService);





    }

}
