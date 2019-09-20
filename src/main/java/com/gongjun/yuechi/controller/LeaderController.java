package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Attachment;
import com.gongjun.yuechi.model.Leader;
import com.gongjun.yuechi.model.vo.LeaderVo;
import com.gongjun.yuechi.service.IAttachmentService;
import com.gongjun.yuechi.service.ILeaderService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Autowired
    IAttachmentService atservice;

    @ApiOperation(value = "添加领导人", notes = "添加领导人")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"name"})
    @CrossOrigin
    public ResponseBean add(MultipartFile[] files, Leader leader){
        try {
            List<Attachment> ats = this.atservice.upload(files);
            if(!CollectionUtils.isEmpty(ats)) leader.setPhoto(ats.get(0).getId());
            this.service.insert(leader);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"add success",null);

    }



    @ApiOperation(value = "修改领导人", notes = "修改领导人")
    @RequiresAuthentication
    @PostMapping(value = "/edit",params = {"id"})
    @CrossOrigin
    public ResponseBean edit(MultipartFile[] files,Leader leader){
        try {
            if(null != files){
                List<Attachment> ats = this.atservice.upload(files);
                if(!CollectionUtils.isEmpty(ats)) leader.setPhoto(ats.get(0).getId());
            }
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
}

