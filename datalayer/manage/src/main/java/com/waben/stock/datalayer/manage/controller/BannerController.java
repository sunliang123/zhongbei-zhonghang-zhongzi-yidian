package com.waben.stock.datalayer.manage.controller;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.BannerForward;
import com.waben.stock.datalayer.manage.entity.Circulars;
import com.waben.stock.interfaces.dto.manage.BannerForwardDto;
import com.waben.stock.interfaces.dto.manage.CircularsDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.manage.entity.Banner;
import com.waben.stock.datalayer.manage.service.BannerService;
import com.waben.stock.interfaces.dto.manage.BannerDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BannerQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.manage.BannerInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/***
 * @author yuyidi 2017-11-21 10:59:28
 * @class com.waben.stock.datalayer.manage.controller.BannerController
 * @description 系统轮播图
 */
// @RestController
// @RequestMapping("/banner")
@Component
public class BannerController implements BannerInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BannerService bannerService;

    @Override
    public Response<List<BannerDto>> fetchBanners(Boolean enable) {
        logger.info("是否获取是否可用的轮播图列表:{}", enable);
        List<Banner> banners = bannerService.findBanners(enable);
        List<BannerDto> bannerDtos = CopyBeanUtils.copyListBeanPropertiesToList(banners, BannerDto.class);
        return new Response<>(bannerDtos);
    }

    @Override
    public Response<PageInfo<BannerDto>> pages(@RequestBody BannerQuery query) {
        Page<Banner> page = bannerService.pagesByQuery(query);
        PageInfo<BannerDto> result = PageToPageInfo.pageToPageInfo(page, BannerDto.class);
        for(int i=0; i<page.getContent().size(); i++) {
            if(page.getContent().get(i).getBannerForward()!=null) {
                Long id = page.getContent().get(i).getBannerForward().getId();
                result.getContent().get(i).setBannerForwardId(id);
                result.getContent().get(i).setForward(page.getContent().get(i).getBannerForward().getForward());
            }
        }
        return new Response<>(result);
    }

    @Override
    public Response<BannerDto> fetchById(@PathVariable Long id) {
        Banner banner = bannerService.fetchById(id);
        BannerDto bannerDto = CopyBeanUtils.copyBeanProperties(banner, new BannerDto(), false);
        return new Response<>(bannerDto);
    }

    @Override
    public Response<BannerDto> modify(@RequestBody BannerDto bannerDto) {
        Banner banner = CopyBeanUtils.copyBeanProperties(Banner.class, bannerDto, false);
        BannerForward bannerForward = new BannerForward();
        bannerForward.setId(bannerDto.getBannerForwardId());
        banner.setBannerForward(bannerForward);
        BannerDto result = CopyBeanUtils.copyBeanProperties(BannerDto.class,bannerService.revision(banner),false);
        if(banner.getBannerForward()!=null) {
            result.setBannerForwardId(banner.getBannerForward().getId());
        }
        return new Response<>(result);
    }

    @Override
    public void delete(@PathVariable Long id) {
        bannerService.delete(id);
    }

    @Override
    public Response<BannerDto> add(@RequestBody BannerDto requestDto) {
        Banner banner = CopyBeanUtils.copyBeanProperties(Banner.class, requestDto, false);
        BannerForward bannerForward = new BannerForward();
        bannerForward.setId(requestDto.getBannerForwardId());
        banner.setBannerForward(bannerForward);
        Banner result = bannerService.save(banner);
        BannerDto response = CopyBeanUtils.copyBeanProperties(BannerDto.class,result,false);
        if(result.getBannerForward()!=null) {
            response.setBannerForwardId(result.getBannerForward().getId());
        }
        return new Response<>(response);
    }
}
