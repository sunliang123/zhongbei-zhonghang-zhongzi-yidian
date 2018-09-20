package com.waben.stock.applayer.tactics.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.activity.ActivityDto;
import com.waben.stock.interfaces.dto.activity.ActivityPublisherDto;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.dto.activity.PublisherTeleChargeDto;
import com.waben.stock.interfaces.dto.activity.PublisherTicketDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.ActivityMngInterface;
import com.waben.stock.interfaces.service.activity.ActivityPublisherInterface;
import com.waben.stock.interfaces.service.activity.PublisherDeduTicketInterface;
import com.waben.stock.interfaces.service.activity.PublisherTeleChargeInterface;
import com.waben.stock.interfaces.service.activity.PublisherTicketInterface;

@Service("tacticsActivityBusiness")
public class ActivityBusiness {

    @Autowired
    private ActivityMngInterface activityReference;

    @Autowired
    private PublisherDeduTicketInterface publisherDeduTicketReference;

    @Autowired
    private PublisherTeleChargeInterface publisherTeleChargeReference;

    @Autowired
    private PublisherTicketInterface publisherTicketReference;

    @Autowired
    private ActivityPublisherInterface activityPublisherReference;

    @Autowired
    private PublisherBusiness publisherBusiness;

    public ActivityDto findActivityById(long activityId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Map<String,String>> listWinning = new ArrayList<>();

        ActivityDto activityDto = activityReference.getActivity(activityId).getResult();

        List<ActivityPublisherDto> activityPublisherDtos = activityPublisherReference.getActivityPublishersByActivityId(activityId).getResult();

        for (ActivityPublisherDto activityPublisherDto : activityPublisherDtos) {
            long apId = activityPublisherDto.getApId();
            PublisherDto publisherDto = publisherBusiness.findById(activityPublisherDto.getPublisherId());

            //抵扣券
            Response<List<PublisherDeduTicketDto>> publisherDeduTickets = publisherDeduTicketReference.getPublisherDeduTicketsByApId(apId);
            if(publisherDeduTickets.getCode().equals("200")&&publisherDeduTickets.getResult()!=null) {
                for (PublisherDeduTicketDto publisherDeduTicket : publisherDeduTickets.getResult()) {
                    Map<String,String> mapPDT = new HashMap<>();
                    mapPDT.put("winningTime",sdf.format(publisherDeduTicket.getWinningTime()));
                    mapPDT.put("publisherPhone",publisherDto.getPhone());
                    mapPDT.put("award",publisherDeduTicket.getMemo());
                    listWinning.add(mapPDT);
                }
            }

            //话费
            Response<List<PublisherTeleChargeDto>> publisherTeleCharges = publisherTeleChargeReference.getPublisherTeleChargesByApId(apId);
            if(publisherTeleCharges.getCode().equals("200")&&publisherTeleCharges.getResult()!=null) {
                for(PublisherTeleChargeDto publisherTeleCharge : publisherTeleCharges.getResult()) {
                    Map<String,String> mapPTC = new HashMap<>();
                    mapPTC.put("winningTime",sdf.format(publisherTeleCharge.getWinningTime()));
                    mapPTC.put("publisherPhone",publisherDto.getPhone());
                    mapPTC.put("award",publisherTeleCharge.getMemo());
                    listWinning.add(mapPTC);
                }
            }

            //实物
            Response<List<PublisherTicketDto>> publisherTickets = publisherTicketReference.getPublisherTicketsByApId(apId);
            if(publisherTickets.getCode().equals("200")&&publisherTickets.getResult()!=null) {
                for(PublisherTicketDto publisherTicket : publisherTickets.getResult()) {
                    Map<String,String> mapPT = new HashMap<>();
                    mapPT.put("winningTime",sdf.format(publisherTicket.getWinningTime()));
                    mapPT.put("publisherPhone",publisherDto.getPhone());
                    mapPT.put("award",publisherTicket.getMemo());
                    listWinning.add(mapPT);
                }
            }
        }
        activityDto.setListWinningInfo(sort(listWinning));
        return activityDto;
    }

    public List<Map<String,String>> sort(List<Map<String,String>> list) {
        List<Map<String,String>> result = new ArrayList<>();
        for (Map<String,String> mapI : list) {
            String winningTimeI = mapI.get("winningTime");
            for(Map<String,String> mapJ : list) {
                String winningTimeJ = mapJ.get("winningTime");
                if(winningTimeI.compareTo(winningTimeJ)<0) {
                    break;
                }
            }
            if(result.size()<=5) {
                result.add(mapI);
            }else {
                break;
            }
        }
        return result;
    }

}
