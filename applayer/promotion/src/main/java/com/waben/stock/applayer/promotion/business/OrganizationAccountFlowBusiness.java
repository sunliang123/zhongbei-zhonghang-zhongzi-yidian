package com.waben.stock.applayer.promotion.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.organization.OrganizationAccountFlowDto;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountFlowWithTradeInfoDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationAccountFlowQuery;
import com.waben.stock.interfaces.service.organization.OrganizationAccountFlowInterface;

@Service("promotionOrganizationAccountFlowBusiness")
public class OrganizationAccountFlowBusiness {

    @Autowired
    private OrganizationAccountFlowInterface organizationAccountFlowReference;

    public PageInfo<OrganizationAccountFlowWithTradeInfoDto> pagesWithTradeInfo(OrganizationAccountFlowQuery query) {
        Response<PageInfo<OrganizationAccountFlowWithTradeInfoDto>> response = organizationAccountFlowReference.pagesWithTradeInfo(query);
        if ("200".equals(response.getCode())) {
            return response.getResult();
        }
        throw new ServiceException(response.getCode());
    }

    public List<OrganizationAccountFlowDto> list(){
        Response<List<OrganizationAccountFlowDto>> response = organizationAccountFlowReference.list();
        if ("200".equals(response.getCode())) {
            return response.getResult();
        }
        throw new ServiceException(response.getCode());
    }
}
