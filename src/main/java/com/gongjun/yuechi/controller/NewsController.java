package com.gongjun.yuechi.controller;


import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Attachment;
import com.gongjun.yuechi.model.Leader;
import com.gongjun.yuechi.model.News;
import com.gongjun.yuechi.service.IAttachmentService;
import com.gongjun.yuechi.service.INewsService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 新闻资讯列表 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/news", description = "新闻资讯前端控制器接口")
@RestController
@RequestMapping("/news")

public class NewsController {

    @Autowired
    INewsService service;

    @Autowired
    IAttachmentService atservice;


    @ApiOperation(value = "上传附件接口", notes = "上传附件接口")
    @RequiresAuthentication
    @PostMapping(value = "/upload/img")
    @CrossOrigin
    public ResponseBean add(@RequestPart MultipartFile[] files){
        List<Attachment> ats = atservice.upload(files);
        List<String> urls = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(ats))  urls = ats.stream().map(Attachment::getUrl).collect(Collectors.toList());
        return new ResponseBean(HttpStatus.OK.value(),"add success",urls);
    }


    @ApiOperation(value = "添加文章", notes = "添加文章")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"title"})
    public ResponseBean add(News news){
        try {
            this.service.insert(news);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"add success",null);
    }

}

