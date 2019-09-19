package com.gongjun.yuechi.controller;


import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.constant.TimeConstant;
import com.gongjun.yuechi.service.IAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin
@MultipartConfig(maxRequestSize = 1024L*100000)
public class AttachmentController {
    @Autowired
    IAttachmentService service;

    @PostMapping("/upload")
    @ApiOperation(value = "附件上传接口（测试）", notes = "附件上传接口（测试）")
    public ResponseBean upload(@RequestPart("files") MultipartFile[] files,String s) throws Exception{
        this.service.upload(files,s);
        return new ResponseBean(HttpStatus.OK.value(),s,null);
    }



}

