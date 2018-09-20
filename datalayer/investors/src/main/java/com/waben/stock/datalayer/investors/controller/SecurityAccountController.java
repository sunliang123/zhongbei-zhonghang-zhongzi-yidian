package com.waben.stock.datalayer.investors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.investors.entity.SecurityAccount;
import com.waben.stock.datalayer.investors.service.SecurityAccountService;
import com.waben.stock.interfaces.dto.investor.SecurityAccountDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.SecurityAccountQuery;
import com.waben.stock.interfaces.service.inverstors.SecurityAccountInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
@RestController
@RequestMapping("/securityaccount")
public class SecurityAccountController  implements SecurityAccountInterface{

    @Autowired
    private SecurityAccountService securityAccountService;

    /**
     * 证券账户列表
     */
    public Response<PageInfo<SecurityAccountDto>> pagesByQuery(@RequestBody SecurityAccountQuery securityAccountQuery) {
        Page<SecurityAccount> page = securityAccountService.pagesByQuery(securityAccountQuery);
        PageInfo<SecurityAccountDto> result = PageToPageInfo.pageToPageInfo(page, SecurityAccountDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<SecurityAccountDto> fetchById(@PathVariable Long id) {
        SecurityAccount securityAccount = securityAccountService.fetchById(id);
        SecurityAccountDto securityAccountDto = CopyBeanUtils.copyBeanProperties(securityAccount, new SecurityAccountDto(), false);
        return new Response<>(securityAccountDto);
    }

    @Override
    public void delete(@PathVariable Long id) {
        securityAccountService.delete(id);
    }
}
