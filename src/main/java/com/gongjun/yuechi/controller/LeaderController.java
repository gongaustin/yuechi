package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseBean selectProposePage(Page page, String keyword){

        EntityWrapper ew = new EntityWrapper();
        ew.orderDesc(Lists.newArrayList("ctime"));
        try {
            page = this.service.selectPage(page,ew);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"select success",page);

    }

    @ApiOperation(value = "ID查询领导人", notes = "ID查询领导人")
    @RequiresAuthentication
    @GetMapping(value = "/findProposeById",params = {"id"})
    public ResponseBean findProposeById(String id){
        LeaderVo leader;
        try {
            leader = null;
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"find success",leader);

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

