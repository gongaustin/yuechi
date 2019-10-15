package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.service.IPermissionService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/permission", description = "权限前端控制器接口")
@RestController
@RequestMapping("/permission")
@Validated
public class PermissionController {

    @Autowired
    private IPermissionService service;

    /**
     * 分页查询菜单
     * */
    @ApiOperation(value = "分页查询菜单/模块", notes = "分页查询菜单/模块")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean selectPermissionPage(Page<Permission> page,@RequestParam(defaultValue = "2")Integer type){
        EntityWrapper<Permission> ew = new EntityWrapper<>();
        ew.where("type={0}",type);
        page.setAscs(Lists.newArrayList("orders"));
        Page<Permission> permissions = this.service.selectPage(page,ew);
        return new ResponseBean(HttpStatus.OK.value(),"",permissions);
    }

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"name","permissionValue"})
    public ResponseBean addPermission(Permission permission){

        permission.setType(2);

        try {
            this.service.insert(permission);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"add success",null);

    }
    @ApiOperation(value = "禁用/启用菜单", notes = "禁用/启用菜单")
    @RequiresAuthentication
    @PostMapping(value = "/forbidden",params = {"id","status"})
    public ResponseBean forbiddenPermission(String id, @Max(1) @Min(0) Integer status){

        Permission p = new Permission();
        p.setId(id);
        p.setStatus(status);

        try {
            this.service.updateById(p);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),(status == 0?"disable":"enable")+" success",null);

    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @RequiresAuthentication
    @PostMapping(value = "/delete",params = {"id"})
    public ResponseBean deletePermission(String id){

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"delete success",null);

    }

    @ApiOperation(value = "查询所有已启用的菜单/模块", notes = "查询所有已启用的菜单/模块")
    @RequiresAuthentication
    @GetMapping(value = "/getAllEnabled")
    public ResponseBean getAllEnabled(@RequestParam(defaultValue = "2") Integer type){
        List permissions;
        try {
            permissions = this.service.selectList(new EntityWrapper<Permission>().where("status={0}",1).and("type={0}",type).orderAsc(Lists.newArrayList("orders")));
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"find success",permissions);

    }




}

