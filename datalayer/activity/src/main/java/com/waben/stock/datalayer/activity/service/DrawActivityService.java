package com.waben.stock.datalayer.activity.service;


import com.waben.stock.datalayer.activity.entity.*;
import com.waben.stock.datalayer.activity.repository.DrawActivityDao;
import com.waben.stock.datalayer.activity.repository.DrawActivityRadioDao;
import com.waben.stock.datalayer.activity.repository.TicketDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.DataNotFoundException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class DrawActivityService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DrawActivityDao drawActivityDao;

    @Autowired
    private DrawActivityRadioDao drawActivityRadioDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private ActivityPublisherService  activityPublisherService;

    @Autowired
    private PublisherDeduTicketService publisherDeduTicketService;

    @Autowired
    private PublisherTeleChargeService publisherTeleChargeService;

    @Autowired
    private PublisherTicketService publisherTicketService;

    @Transactional
    public TicketAmount lotteryDraw(long activity, long publisherId) {
        DrawActivity drawActivity = drawActivityDao.getDrawActivityByActivityIdAndPublisherId(activity, publisherId);
        ActivityPublisher activityPublisher = activityPublisherService.getActivityPublisherByPublisherId(publisherId);
        if(drawActivity.getRemaintime()<=0) {
            //抽奖次数不足
            throw new ServiceException(ExceptionConstant.INSUFFICIENT_NUMBER_OF_DRAW);
        }else if(drawActivity.getLuckyDrawCount()>10) {
            throw new ServiceException(ExceptionConstant.OVERSTEP_NUMBER_OF_DRAW);
        }else {
            List<DrawActivityRadio> drawActivityRadios = drawActivityRadioDao.getDrawActivityRadioByActivitysId(drawActivity.getActivityId());
            TicketAmount ticket = draw(drawActivityRadios);
            logger.info("奖品 ：{}",JacksonUtil.encode(ticket));
            setRemaintime(drawActivity.getDrawId());//修改抽奖数
            setUsedNum(ticket.getTicketAmountId());//修改使用数
            if(ticket.getTicketType()==1) {
                savePublisherDeduTicket(activityPublisher.getApId(),publisherId,ticket);
            }else if(ticket.getTicketType()==2) {
                savePublisherTeleCharge(activityPublisher.getApId(),publisherId,ticket);
            }else if(ticket.getTicketType()==3) {
                savePublisherTicket(activityPublisher.getApId(),publisherId,ticket);
            }
            return ticket;
        }
    }

    public TicketAmount drawTemp(List<DrawActivityRadio> drawActivityRadios) {
        for(int i=0; i<drawActivityRadios.size(); i++) {
            boolean flag = true;
            DrawActivityRadio drawActivityRadioI = drawActivityRadios.get(i);
            TicketAmount ticketI = ticketDao.getTicketAmount(drawActivityRadioI.getTicketId());
            int surI = ticketI.getNum() - ticketI.getUsednum();
            if(surI>0) {
                BigDecimal surplusI = new BigDecimal(surI);//剩余数量
                BigDecimal radioI = drawActivityRadioI.getRadio();//占比
                BigDecimal resultI = surplusI.multiply(radioI);
                for(int j=0; j<drawActivityRadios.size()-i;j++) {
                    DrawActivityRadio drawActivityRadioJ = drawActivityRadios.get(j);
                    TicketAmount ticketJ = ticketDao.getTicketAmount(drawActivityRadioJ.getTicketId());
                    int surJ = ticketJ.getNum() - ticketJ.getUsednum();
                    BigDecimal surplusJ = new BigDecimal(surJ);//剩余数量
                    BigDecimal radioJ = drawActivityRadioJ.getRadio();//占比
                    BigDecimal resultJ = surplusJ.multiply(radioJ);
                    if(resultI.compareTo(resultJ)<0) {
                        flag = false;
                    }
                }
                if(flag) {
                    return ticketI;
                }
            }
        }
        throw new ServiceException(ExceptionConstant.PRIZE_IS_EMPTY);
    }

    public TicketAmount draw(List<DrawActivityRadio> drawActivityRadios) {

            //计算总权重
            BigDecimal sumWeight = new BigDecimal(0);
            int sum = 0;
            int used = 0;
            for(DrawActivityRadio drawActivityRadio : drawActivityRadios){
                sumWeight = sumWeight.add(drawActivityRadio.getRadio());
                TicketAmount ticketAmount = ticketDao.getTicketAmount(drawActivityRadio.getTicketId());
                sum += ticketAmount.getNum();
                used += ticketAmount.getUsednum();
            }
            if(sum-used>0) {
                //产生随机数
                BigDecimal randomNumber;
                randomNumber = new BigDecimal(Math.random());
                logger.info("产生随机数:{}",randomNumber);
                //根据随机数在所有奖品分布的区域并确定所抽奖品
                BigDecimal before =  new BigDecimal(0);
                BigDecimal after = new BigDecimal(0);
                for(int i=0;i<drawActivityRadios.size();i++){
                    TicketAmount ticketAmount = ticketDao.getTicketAmount(drawActivityRadios.get(i).getTicketId());

                    after = after.add(drawActivityRadios.get(i).getRadio().divide(sumWeight));
                    if(i!=0){
                        before = before.add(drawActivityRadios.get(i-1).getRadio().divide(sumWeight));
                    }
                    if(randomNumber.compareTo(before)>=0 && randomNumber.compareTo(after)<=0){
                        if(ticketAmount.getNum()-ticketAmount.getUsednum()>0) {
                            return ticketAmount;
                        }else {
                            return draw(drawActivityRadios);
                        }
                    }
                }
            }
        throw new DataNotFoundException("已没有奖品");
    }

    @Transactional
    public void setRemaintime(long id) {
        DrawActivity drawActicity = drawActivityDao.getDrawActicity(id);
        drawActicity.setRemaintime(drawActicity.getRemaintime()-1);
        drawActicity.setLuckyDrawCount(drawActicity.getLuckyDrawCount()+1);
    }

    @Transactional
    public void setUsedNum(long id) {
        TicketAmount ticketAmount = ticketDao.getTicketAmount(id);
        ticketAmount.setUsednum(ticketAmount.getUsednum()+1);
    }

    @Transactional
    public void savePublisherDeduTicket(long apId, long publisherId, TicketAmount ticket) {
        PublisherDeduTicket publisherDeduTicket = new PublisherDeduTicket();
        publisherDeduTicket.setPubliserId(publisherId);
        publisherDeduTicket.setUseType(1);
        publisherDeduTicket.setTicketName(ticket.getTicketName());
        publisherDeduTicket.setValidDate(ticket.getEndTime());
        publisherDeduTicket.setAmount(ticket.getAmount());
        publisherDeduTicket.setApId(apId);
        publisherDeduTicket.setStatus(1);
        publisherDeduTicket.setMemo(ticket.getTicketName()+"("+ticket.getAmount()+"元)");
        publisherDeduTicket.setWinningTime(new Date());
        publisherDeduTicketService.savePublisherDeduTicket(publisherDeduTicket);
    }

    @Transactional
    public void savePublisherTeleCharge(long apId, long publisherId, TicketAmount ticket) {
        PublisherTeleCharge publisherTeleCharge = new PublisherTeleCharge();
        publisherTeleCharge.setPubliserId(publisherId);
        publisherTeleCharge.setValidDate(ticket.getEndTime());
        publisherTeleCharge.setAmount(ticket.getAmount());
        publisherTeleCharge.setApId(apId);
        publisherTeleCharge.setStatus(1);
        publisherTeleCharge.setIspay(false);
        publisherTeleCharge.setMemo(ticket.getTicketName()+"("+ticket.getAmount()+"元)");
        publisherTeleCharge.setWinningTime(new Date());
        publisherTeleChargeService.savePublisherTeleCharge(publisherTeleCharge);
    }

    @Transactional
    public void savePublisherTicket(long apId, long publisherId, TicketAmount ticket) {
        PublisherTicket publisherTicket = new PublisherTicket();
        publisherTicket.setPublisherId(publisherId);
        publisherTicket.setValidDate(ticket.getEndTime());
        publisherTicket.setTicketName(ticket.getTicketName());
        publisherTicket.setApId(apId);
        publisherTicket.setStatus(1);
        publisherTicket.setMemo(ticket.getTicketName()+"("+ticket.getAmount()+"元)");
        publisherTicket.setWinningTime(new Date());
        publisherTicketService.savePublisherTicket(publisherTicket);
    }

    @Transactional
    public List<DrawActivity> setRemaintime() {
        List<DrawActivity> drawActicitys = drawActivityDao.getDrawActicitys();
        for (DrawActivity drawActivity : drawActicitys) {
            drawActivity.setRemaintime(0);
            drawActivity.setLuckyDrawCount(0);
        }
        return drawActicitys;
    }


    public DrawActivity getDrawActicity(long activityId, long publisherId) {
        return drawActivityDao.getDrawActivityByActivityIdAndPublisherId(activityId, publisherId);
    }
}
