package com.gongjun.yuechi.controller;


import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.constant.TimeConstant;
import com.gongjun.yuechi.model.Attachment;
import com.gongjun.yuechi.service.IAttachmentService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/attachment", description = "附件前端控制器接口")
@RestController
@RequestMapping("/attachment")
@MultipartConfig(maxRequestSize = 1024L*100000)

public class AttachmentController {
    @Autowired
    IAttachmentService service;

    @PostMapping("/upload")
    @ApiOperation(value = "附件上传接口（单独）", notes = "附件上传接口（单独）")
    @CrossOrigin
    public ResponseBean upload(@RequestPart("files") MultipartFile[] files) throws Exception{
        List<Attachment> ats = this.service.upload(files);
        List<String> ids = Lists.newArrayList();
        if(CollectionUtils.isEmpty(ats)) ids = ats.stream().map(Attachment::getId).collect(Collectors.toList());
        return new ResponseBean(HttpStatus.OK.value(),"upload success",ids);
    }



}

