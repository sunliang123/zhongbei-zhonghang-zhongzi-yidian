package com.waben.stock.applayer.admin.controller;

import com.waben.stock.applayer.admin.business.UploadBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("adminUploadController")
@RequestMapping("/file")
@Api(description = "图片上传")
public class UploadController {

    @Autowired
    private UploadBusiness uploadBusiness;

    @PostMapping("/upload")
    @ResponseBody
    @ApiImplicitParam(paramType = "query", dataType = "MultipartFile", name = "file", value = "文件对象", required = true)
    @ApiOperation(value = "上传图片")
    public String upload(@RequestParam("file") MultipartFile file){
        String resultPath;
        try {
           resultPath =  uploadBusiness.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return resultPath;
    }

}
