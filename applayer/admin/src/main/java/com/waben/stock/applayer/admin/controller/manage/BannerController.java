package com.waben.stock.applayer.admin.controller.manage;

import com.waben.stock.applayer.admin.business.manage.BannerBusiness;
import com.waben.stock.interfaces.dto.manage.BannerDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BannerQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminBannerController")
@RequestMapping("/banner")
@Api(description="轮播图")
public class BannerController {

    @Autowired
    private BannerBusiness bannerBusiness;

    @GetMapping("/pages")
    @ApiImplicitParam(paramType = "query", dataType = "BannerQuery", name = "query", value = "查询对象", required = true)
    @ApiOperation(value = "轮播图分页")
    public Response<PageInfo<BannerDto>> pages(BannerQuery query) {
        PageInfo<BannerDto> pageInfo = bannerBusiness.pages(query);
        return new Response<>(pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "轮播图id", required = true)
    @ApiOperation(value = "轮播图删除")
    public Response<Integer> delete(@PathVariable Long id){
        bannerBusiness.delete(id);
        return new Response<>(1);
    }


    @PostMapping("/save")
    @ApiImplicitParam(paramType = "query", dataType = "BannerDto", name = "bannerDto", value = "轮播图对象", required = true)
    @ApiOperation(value = "轮播图添加")
    public Response<BannerDto> add(BannerDto bannerDto){
        BannerDto response = bannerBusiness.save(bannerDto);
        return new Response<>(response);
    }

    @PostMapping("/modify")
    @ApiImplicitParam(paramType = "query", dataType = "BannerDto", name = "bannerDto", value = "轮播图对象", required = true)
    @ApiOperation(value = "轮播图修改")
    public Response<BannerDto> modify(BannerDto bannerDto){
        BannerDto response = bannerBusiness.revision(bannerDto);
        return new Response<>(response);
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "轮播图id", required = true)
    @ApiOperation(value = "通过轮播图id获取轮播图")
    public Response<BannerDto> fetchById(@PathVariable Long id) {
        BannerDto response = bannerBusiness.findById(id);
        return new Response<>(response);
    }
}
