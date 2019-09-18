package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.FileTools;
import com.gongjun.yuechi.model.Leader;
import com.gongjun.yuechi.model.Propose;
import com.gongjun.yuechi.model.vo.LeaderVo;
import com.gongjun.yuechi.service.ILeaderService;
import com.gongjun.yuechi.service.IProposeService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 领导人表 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/leader", description = "领导人前端控制器接口")
@RestController
@RequestMapping("/leader")
public class LeaderController {

    @Autowired
    private ILeaderService service;

    @ApiOperation(value = "分页查询领导人", notes = "分页查询领导人")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean selectLeaderPage(Page page, String keyword){

        EntityWrapper ew = new EntityWrapper();
        ew.orderDesc(Lists.newArrayList("ctime"));
        try {
            page = this.service.selectPage(page,ew);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"select success",page);

    }


    @ApiOperation(value = "查询所有领导人(主页使用)", notes = "查询所有领导人(主页使用)")
    @GetMapping("/findAll")
    public ResponseBean findAll(String keyword){

        EntityWrapper ew = new EntityWrapper();
        ew.orderAsc(Lists.newArrayList("order"));
        try {
            List<LeaderVo> leaders= null;
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"select success",null);

    }

    @ApiOperation(value = "ID查询领导人", notes = "ID查询领导人")
    @RequiresAuthentication
    @GetMapping(value = "/findLeaderById",params = {"id"})
    public ResponseBean findLeaderById(String id){
        LeaderVo leader;
        try {
            leader = this.service.findLeaderById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"find success",leader);

    }

    @ApiOperation(value = "添加领导人", notes = "添加领导人")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"id"})
    public ResponseBean add(@RequestPart("files") MultipartFile files, Leader leader){
        try {
            this.service.insert(leader);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"add success",null);

    }



    @ApiOperation(value = "修改领导人", notes = "修改领导人")
    @RequiresAuthentication
    @PostMapping(value = "/edit",params = {"id"})
    public ResponseBean edit(Leader leader){
        try {
            this.service.updateById(leader);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"update success",null);

    }

    @ApiOperation(value = "删除领导人", notes = "删除领导人")
    @RequiresAuthentication
    @PostMapping(value = "/delete",params = {"id"})
    public ResponseBean delete(String id){
        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"delete success",null);

    }



    /**
     * 校验文件
     * @param token
     * @param mltfile
     * @param response
     * @return
     */
    private Boolean checkFile(String token, MultipartFile mltfile, ResponseBean response) throws IOException {
        Boolean flag = false;
        // 大小
        long fileSize = mltfile.getSize();
        if (fileSize > 20*1024*1024) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("file is too big");
            flag = true;
            return flag;
        }

        // 校验文件名是否包含特殊字符
        String fileName = mltfile.getOriginalFilename();
        String fileNameSub = fileName.substring(0, fileName.lastIndexOf("."));
        // String regEx1 =  "<script[\s\s]+</script *>";
        String regEx = "^[a-zA-Z0-9\u4E00-\u9FA5 .。_-]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(fileNameSub);
        if (!m.matches()) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("file name is not legal");
            flag = true;
            return flag;
        }
        // 头文件校验后缀
        String type = FileTools.getCorrectType(mltfile);
        if(type == null){
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("file type is not enable");
            flag = true;
            return flag;
        }

        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        List<String> endList = new ArrayList<>();
        endList.add("doc");
        endList.add("docx");
        endList.add("docm");
        endList.add("dotx");
        endList.add("dotm");
        endList.add("xls");
        endList.add("xlsx");
        endList.add("xltx");
        endList.add("xltm");
        endList.add("xlsb");
        endList.add("xlam");
        endList.add("ppt");
        endList.add("pptx");
        endList.add("ppsx");
        endList.add("potx");
        endList.add("potm");
        endList.add("ppam");
        endList.add("ppsm");
        endList.add("pdf");
        if (!endList.contains(fileEnd)) {
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("file format is not enable");
            flag = true;
            return flag;
        }
        return flag;
    }

}

