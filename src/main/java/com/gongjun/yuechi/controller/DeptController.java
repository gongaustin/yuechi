package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Dept;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.model.vo.UserVo;
import com.gongjun.yuechi.service.IDeptService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 科室 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "部门科室前端控制器接口", description = "部门科室前端控制器接口")
@RestController
@RequestMapping("/dept")
@Validated
public class DeptController {

    @Autowired
    private IDeptService service;

    @GetMapping("")
    public ResponseBean test(String s){
        List<Dept> userList = this.service.selectList(null);
        userList.get(0).setCtime(new Date());
        this.service.updateById(userList.get(0));





        return new ResponseBean(200,"",userList);
    }

    @ApiOperation(value = "添加科室", notes = "添加科室")
    @RequiresAuthentication
    @GetMapping(value = "/add")
    public ResponseBean add(Dept dept){

        return null;


    }


    @ApiOperation(value = "删除科室", notes = "删除科室")
    @RequiresAuthentication
    @GetMapping(value = "/delete")
    public ResponseBean delete(String id){

        return null;


    }


    @ApiOperation(value = "修改科室信息", notes = "修改科室信息")
    @RequiresAuthentication
    @GetMapping(value = "/edit",params = {"id","deptName"})
    public ResponseBean delete(Dept dept){

        return null;

    }


    @ApiOperation(value = "禁用/启用科室", notes = "禁用/启用科室")
    @RequiresAuthentication
    @GetMapping(value = "/forbidden",params = {"id","status"})
    public ResponseBean delete(@NotBlank String id, @Max(1) @Min(0) Integer status){
        Dept dept = new Dept();
        dept.setId(id);
        dept.setStatus(status);
        try {
            this.service.updateById(dept);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),(status == 0?"disable":"enable")+" success",null);

    }


    @ApiOperation(value = "查询所有已启用的科室", notes = "查询所有已启用的科室")
    @RequiresAuthentication
    @GetMapping(value = "/selectAllEnableDept")
    public ResponseBean selectAllEnableDept(String id){

        List<Dept> depts;
        try {
            depts = this.service.selectList(new EntityWrapper<Dept>().where("status={0}",1).and("degree={0}",2).orderDesc(Lists.newArrayList("ctime")));
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"select success",depts);

    }

}

