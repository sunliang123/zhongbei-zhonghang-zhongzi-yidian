package com.waben.stock.risk;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc
 */
public class ContainerTest {

    @Test
    public void testCopyOnWriteList() throws InterruptedException {
        final CopyOnWriteArrayList<Stock> stocks = new CopyOnWriteArrayList<>();
//        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("a", "1"));
        stocks.add(new Stock("b", "2"));
        stocks.add(new Stock("c", "3"));
        stocks.add(new Stock("d", "4"));
        stocks.add(new Stock("e", "5"));

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.nanoTime();
                System.out.println("-------------");
                for (int i = 0; i < 10; i++) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    stocks.add(new Stock("a" + i, "1" + "--" + i));
                    System.out.println("长度" + stocks.size());
                }
                System.out.println("------------" + String.valueOf(System.nanoTime() - start));
            }
        });
        Thread.sleep(2500);
        t1.start();
        for (int index = 0; index < stocks.size(); index++) {
//            Thread.sleep(1500);
            System.out.println(stocks.get(index).getName());
        }
    }


    @Test
    public void testListContainer() throws InterruptedException {
        final Container container = new Container();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread push = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("添加数据:" + i);
                    container.add(new Stock("stock" + i, String.valueOf(i)));
//                    if (i % 5 == 0) {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
                countDownLatch.countDown();
            }
        });
        push.start();
        Thread pull = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Map<String,Stock> stringStockMap = container.getBuyInContainer();
                    System.out.println("---begin---"+stringStockMap.size());
                    int index = 0;
                    for (Map.Entry<String, Stock> entry : stringStockMap.entrySet()) {
                        if (stringStockMap.size() == 1) {
                            countDownLatch.countDown();
                        }
                        index++;
                        System.out.println(stringStockMap.size()+"==="+entry.getKey() + "---" + entry.getValue().getName());
                        if (index % 2 == 0) {
                            container.remove(entry.getKey());
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("---end-----"+stringStockMap.size());
                }
            }
        });
        pull.start();
        countDownLatch.await();
        System.out.println(container.getBuyInContainer().size());
    }

    class Container {
        Map<String, Stock> buyInContainer = new ConcurrentHashMap<>();

        public void add(Stock stock) {
            buyInContainer.put(stock.getCode(), stock);
        }

        public void remove(String code) {
            buyInContainer.remove(code);
        }

        public Map<String, Stock> getBuyInContainer() {
            return buyInContainer;
        }
    }


    class Stock {
        private String name;
        private String code;

        public Stock(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
