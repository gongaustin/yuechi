package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.Md5;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class PermissionController {

    @Autowired
    private IPermissionService service;

    /**
     * 分页查询菜单
     * */
    @ApiOperation(value = "分页查询菜单", notes = "分页查询菜单")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean selectPermissionPage(Page<Permission> page){

        Page<Permission> permissions = this.service.selectPage(page);
        return new ResponseBean(HttpStatus.OK.value(),"",permissions);
    }

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"name","permissionValue"})
    public ResponseBean addPermission(Permission permission){

        try {
            this.service.insert(permission);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"add success",null);

    }
    @ApiOperation(value = "禁用菜单", notes = "禁用菜单")
    @RequiresAuthentication
    @PostMapping(value = "/forbidden",params = {"id"})
    public ResponseBean forbiddenPermission(String id){

        Permission p = new Permission();
        p.setId(id);
        p.setStatus(0);

        try {
            this.service.updateById(p);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"forbidden success",null);

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




}

