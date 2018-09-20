package com.waben.stock.applayer.tactics.crawler.service.impl;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.waben.stock.applayer.tactics.crawler.model.AnnualReportModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyAnnouncementModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyProfileModel;
import com.waben.stock.applayer.tactics.crawler.model.StockNewsModel;
import com.waben.stock.applayer.tactics.crawler.util.JsoupUtil;

/**
 * Created by 12 on 2017/7/7.
 *
 * 使用Join Fork
 */
public class DeleteErrorInfoTask extends RecursiveTask<Integer> {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteErrorInfoTask.class);
    MongoTemplate mongoTemplate;

    public static final int threshold = 2000;
    private int start;
    private int end;
    private Class clazz;


    public DeleteErrorInfoTask(int start, int end, Class clazz, MongoTemplate mongoTemplate) {

        LOG.info("create task {},{}",start,end);

        this.start = start;
        this.end = end;
        this.clazz = clazz;
        this.mongoTemplate = mongoTemplate;
    }



    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            sum += doTask();

            LOG.info("end doTask  del  data count ={}",sum);

        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            DeleteErrorInfoTask leftTask = new DeleteErrorInfoTask(start, middle, clazz, mongoTemplate);
            DeleteErrorInfoTask rightTask = new DeleteErrorInfoTask(middle + 1, end, clazz, mongoTemplate);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }

        return sum;
    }

    private int doTask() {
        LOG.info("start doTask  {},{}",start,end);

        AtomicInteger atomicInteger = new AtomicInteger();
        
        List list = mongoTemplate.find(new Query().skip(start).limit(end-start), clazz);
        if(list != null && list.size() > 0) {
        	for(Object stk : list) {
        		String msg = null;
                if (stk instanceof StockNewsModel) msg = ((StockNewsModel) stk).getTitle();
                else if (stk instanceof AnnualReportModel) msg = ((AnnualReportModel) stk).getTitle();
                else if (stk instanceof CompanyProfileModel) msg = ((CompanyProfileModel) stk).getCompanyName();
                else if (stk instanceof CompanyAnnouncementModel) msg = ((CompanyAnnouncementModel) stk).getTitle();
                if (msg != null) {
                    if (!JsoupUtil.isCheckUnicode(msg)) {
                        mongoTemplate.remove(stk);
                        atomicInteger.incrementAndGet();

                        LOG.info("delete error info msg= {}", msg);
                    }
                }
        	}
        }
        
        
        /*
        mongoTemplate
                .find(new Query().skip(start).limit(end-start), clazz)
                .forEach((stk) -> {
                    String msg = null;
                    if (stk instanceof StockNewsModel) msg = ((StockNewsModel) stk).getTitle();
                    else if (stk instanceof AnnualReportModel) msg = ((AnnualReportModel) stk).getTitle();
                    else if (stk instanceof CompanyProfileModel) msg = ((CompanyProfileModel) stk).getCompanyName();
                    else if (stk instanceof CompanyAnnouncementModel) msg = ((CompanyAnnouncementModel) stk).getTitle();
                    if (msg != null) {
                        if (!JsoupUtil.isCheckUnicode(msg)) {
                            mongoTemplate.remove(stk);
                            atomicInteger.incrementAndGet();

                            LOG.info("delete error info msg= {}", msg);
                        }
                    }
                });
		*/


        return atomicInteger.get();
    }


}
