package com.waben.stock.applayer.admin.business;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("adminUploadBusiness")
public class UploadBusiness {

    @Value("${fileSystem}")
    private String fileSystem;

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    public String upload(MultipartFile file) throws IllegalStateException, IOException {
    	String resultPath = null;
    	//取得当前上传文件的文件名称  
        String myFileName = file.getOriginalFilename();
        int index = myFileName.lastIndexOf(".");
        String fileSuffix = myFileName.substring(index);
        //如果名称不为"",说明该文件存在，否则说明该文件不存在  
        if (myFileName.trim() != "") {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int secends = cal.get(Calendar.SECOND);
            int millis = cal.get(Calendar.MILLISECOND);
            //定义上传路径  
            String resoultPath = "" + year + month + day + "/" + hour + minute + secends + millis + fileSuffix;
            resultPath = fileSystem + resoultPath;
            String path = uploadFilePath + resoultPath;
            String paths[] = path.split("/");
            String dir = paths[0];
            for (int i = 0; i < paths.length - 2; i++) {
                try {
                    dir = dir + "/" + paths[i + 1];
                    File dirFile = new File(dir);
                    if (!dirFile.exists()) {
                        dirFile.mkdir();
                    }
                } catch (Exception err) {
                    return "error";
                }
            }
            File localFile = new File(path);
            file.transferTo(localFile);
        }
        return resultPath;
    	
    	/*
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断 request 是否有文件上传,即多部分请求 
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxInMemorySize(40960);
        multipartResolver.setMaxUploadSize(104857600L);
        String resultPath = "";
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得上传文件  
            MultipartFile file = multiRequest.getFile("uploadFile");
            if (file != null) {
                //取得当前上传文件的文件名称  
                String myFileName = file.getOriginalFilename();
                int index = myFileName.lastIndexOf(".");
                String fileSuffix = myFileName.substring(index);
                //如果名称不为"",说明该文件存在，否则说明该文件不存在  
                if (myFileName.trim() != "") {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    int day = cal.get(Calendar.DATE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minute = cal.get(Calendar.MINUTE);
                    int secends = cal.get(Calendar.SECOND);
                    int millis = cal.get(Calendar.MILLISECOND);
                    //定义上传路径  
                    String resoultPath = "" + year + month + day + "/" + hour + minute + secends + millis + fileSuffix;
                    String path = uploadFilePath + resoultPath;
                    resultPath = fileSystem + resoultPath;
                    String paths[] = path.split("/");
                    String dir = paths[0];
                    for (int i = 0; i < paths.length - 2; i++) {
                        try {
                            dir = dir + "/" + paths[i + 1];
                            File dirFile = new File(dir);
                            if (!dirFile.exists()) {
                                dirFile.mkdir();
                            }
                        } catch (Exception err) {
                            return "error";
                        }
                    }
                    File localFile = new File(path);
                    file.transferTo(localFile);
                }
            }
        }
        return resultPath;
        */
    }
}
