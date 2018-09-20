package com.waben.stock.datalayer.stockoption.controller;

import com.waben.stock.datalayer.stockoption.entity.MailUrlInfo;
import com.waben.stock.datalayer.stockoption.service.MailUrlInfoService;
import com.waben.stock.interfaces.dto.stockoption.MailUrlInfoDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.MailUrlInfoInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/3/16.
 */
// @RestController
// @RequestMapping("/mailUrlInfo")
@Component
public class MailUrlInfoController implements MailUrlInfoInterface {

    @Autowired
    private MailUrlInfoService mailUrlInfoService;

    @Override
    public Response<MailUrlInfoDto> add(@RequestBody MailUrlInfoDto mailUrlInfoDto) {
        MailUrlInfo mailUrlInfo = CopyBeanUtils.copyBeanProperties(MailUrlInfo.class, mailUrlInfoDto, false);
        mailUrlInfo.setUnderlying(mailUrlInfoDto.getUnderlying());
        mailUrlInfo.setCode(mailUrlInfoDto.getCode());
        mailUrlInfo.setLocalUrl(mailUrlInfoDto.getLocalUrl());
        mailUrlInfo.setTemplateName(mailUrlInfoDto.getTemplateName());
        MailUrlInfo mailUrlInfo1 = mailUrlInfoService.save(mailUrlInfo);
        MailUrlInfoDto result = CopyBeanUtils.copyBeanProperties(MailUrlInfoDto.class, mailUrlInfo1, false);
        return new Response<>(result);
    }
}
