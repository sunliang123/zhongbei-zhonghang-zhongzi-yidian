package com.waben.stock.interfaces.service.stockoption;

import com.waben.stock.interfaces.dto.stockoption.MailUrlInfoDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/3/16.
 */
public interface MailUrlInfoInterface {
    @RequestMapping(value = "/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<MailUrlInfoDto> add(@RequestBody MailUrlInfoDto mailUrlInfoDto);
}
