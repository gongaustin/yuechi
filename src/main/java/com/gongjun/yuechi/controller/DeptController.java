package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Dept;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.model.vo.UserVo;
import com.gongjun.yuechi.service.IDeptService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    @ApiOperation(value = "分页查询科室", notes = "分页查询科室")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean page(Page<Dept> page,String keyword){
        EntityWrapper ew  = new EntityWrapper();

        if(StringUtils.isNotBlank(keyword)){
            ew.like("dept_name",keyword)
                    .or("description like{0}","%"+keyword+"%")
                    .or("dept_no like{0}","%"+keyword+"%");
        }
        ew.orderDesc(Lists.newArrayList("ctime"));
        try {
            page = this.service.selectPage(page,ew);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"",page);
    }

    @ApiOperation(value = "添加科室", notes = "添加科室")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"deptName"})
    public ResponseBean add(Dept dept,@RequestParam(defaultValue = "岳池县妇幼保健院") String parent){
        Dept d = this.service.selectOne(new EntityWrapper<Dept>().where("dept_name={0}",parent));
        dept.setpId(d==null?null:d.getId());
        dept.setCtime(new Date());
        dept.setDegree((d.getDegree()==null?0:d.getDegree())+1);
        try {
            this.service.insert(dept);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"add success",null);
    }

    @ApiOperation(value = "ID查询科室信息", notes = "ID查询科室信息")
    @RequiresAuthentication
    @GetMapping(value = "/findDeptById",params = {"id"})
    public ResponseBean findDeptById(String id){
        Dept dept;
        try {
            dept = this.service.selectById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"find success",dept);
    }


    @ApiOperation(value = "删除科室", notes = "删除科室")
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


    @ApiOperation(value = "修改科室信息", notes = "修改科室信息")
    @RequiresAuthentication
    @PostMapping(value = "/edit",params = {"id"})
    public ResponseBean delete(Dept dept){

        try {
            this.service.updateById(dept);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"update success",null);

    }


    @ApiOperation(value = "禁用/启用科室", notes = "禁用/启用科室")
    @RequiresAuthentication
    @PostMapping(value = "/forbidden",params = {"id","status"})
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

