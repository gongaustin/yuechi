package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.model.Role;
import com.gongjun.yuechi.service.IRoleService;
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

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/role", description = "角色前端控制器接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService service;

    /**
     * 分页查询菜单
     * */
    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean selectRolePage(Page<Role> page){
        Page<Role> roles = this.service.selectPage(page);
        return new ResponseBean(HttpStatus.OK.value(),"",roles);
    }

    @ApiOperation(value = "查询所有已启用的角色", notes = "查询所有已启用的角色")
    @RequiresAuthentication
    @GetMapping("/list")
    public ResponseBean selectRoleList(){
        List<Role> roles = this.service.selectList(new EntityWrapper<Role>().where("status={0}",1));
        return new ResponseBean(HttpStatus.OK.value(),"",roles);
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"name","description"})
    public ResponseBean addRole(Role role){

        try {
            this.service.insert(role);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"add success",null);

    }
    @ApiOperation(value = "禁用角色（用户组）", notes = "禁用角色（用户组）")
    @RequiresAuthentication
    @PostMapping(value = "/forbidden",params = {"id"})
    public ResponseBean forbiddenRole(String id){

        Role r = new Role();
        r.setId(id);
        r.setStatus(0);

        try {
            this.service.updateById(r);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }

        return new ResponseBean(HttpStatus.OK.value(),"forbidden success",null);

    }

    @ApiOperation(value = "禁用角色（用户组）", notes = "禁用角色（用户组）")
    @RequiresAuthentication
    @PostMapping(value = "/delete",params = {"id"})
    public ResponseBean deleteRole(String id){

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"delete success",null);
    }

    @ApiOperation(value = "给角色添加菜单", notes = "给角色添加菜单")
    @RequiresAuthentication
    @PostMapping(value = "/add/permission",params = {"id"})
    public ResponseBean addPermissionToRole(String id){

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"delete success",null);
    }



}

