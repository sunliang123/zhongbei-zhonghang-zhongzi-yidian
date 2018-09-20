package com.waben.stock.applayer.strategist.crawler.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Boyce 2016年8月17日 下午4:36:28
 */
public class Executors {


    public static ExecutorService newFixedThreadPool(int workerNum, String poolName) {
        return java.util.concurrent.Executors.newFixedThreadPool(workerNum, new DreamThreadFactory(poolName));
    }

    public static ExecutorService newFixedThreadPool1111(int workerNum, int maxNum, String poolName) {
    	// TODO 处理冲突
    	/*
        return new ThreadPoolExecutor(workerNum, maxNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new DreamThreadFactory(poolName));
        */
    	return null;
    }



    public static ExecutorService newFixedThreadPool(int workerNum, int maxNum, String poolName,  BlockingQueue workQueue) {

        return new ThreadPoolExecutor(workerNum, maxNum,
                0L, TimeUnit.MILLISECONDS,
                workQueue,
                new DreamThreadFactory(poolName));
    }


    public static ScheduledExecutorService newScheduledThreadPool(int workerNum, String poolName) {
        return java.util.concurrent.Executors.newScheduledThreadPool(workerNum, new DreamThreadFactory(poolName));
    }

    public static ExecutorService newCachedThreadPool(String poolName) {
        return java.util.concurrent.Executors.newCachedThreadPool(new DreamThreadFactory(poolName));
    }

    public static ExecutorService newSingleThreadExecutor(String poolName) {
        return java.util.concurrent.Executors.newSingleThreadExecutor(new DreamThreadFactory(poolName));
    }


}
