package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Role;
import com.gongjun.yuechi.model.RolePermission;
import com.gongjun.yuechi.service.IRolePermissionService;
import com.gongjun.yuechi.service.IRoleService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
@Validated
public class RoleController {

    @Autowired
    private IRoleService service;

    @Autowired
    private IRolePermissionService rpservice;

    /**
     * 分页查询菜单
     */
    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @RequiresAuthentication
    @Validated
    @GetMapping("/page")
    public ResponseBean selectRolePage(Page<Role> page) {
        Page<Role> roles = this.service.selectPage(page);
        return new ResponseBean(HttpStatus.OK.value(), "", roles);
    }

    @ApiOperation(value = "查询所有已启用的角色", notes = "查询所有已启用的角色")
    @RequiresAuthentication
    @GetMapping("/list")
    public ResponseBean selectRoleList() {
        List<Role> roles = this.service.selectList(new EntityWrapper<Role>().where("status={0}", 1));
        return new ResponseBean(HttpStatus.OK.value(), "", roles);
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequiresAuthentication
    @PostMapping(value = "/add", params = {"name", "description"})
    public ResponseBean addRole(Role role) {

        try {
            this.service.insert(role);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }

        return new ResponseBean(HttpStatus.OK.value(), "add success", null);

    }

    @ApiOperation(value = "禁用/启用角色（用户组）", notes = "禁用/启用角色（用户组）")
    @RequiresAuthentication
    @PostMapping(value = "/forbidden", params = {"id", "status"})
    public ResponseBean forbiddenRole(String id, @Min(0) @Max(1) Integer status) {

        Role r = new Role();
        r.setId(id);
        r.setStatus(status);

        try {
            this.service.updateById(r);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }

        return new ResponseBean(HttpStatus.OK.value(), (status == 0 ? "disable":"enable") + " success", null);

    }

    @ApiOperation(value = "删除角色（用户组）", notes = "删除角色（用户组）")
    @RequiresAuthentication
    @PostMapping(value = "/delete", params = {"id"})
    public ResponseBean deleteRole(String id) {

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
        return new ResponseBean(HttpStatus.OK.value(), "delete success", null);
    }

    @ApiOperation(value = "给角色添加菜单", notes = "给角色添加菜单")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = true, dataType = "String"), @ApiImplicitParam(paramType = "query", name = "permissionIds", value = "菜单ID", required = true, allowMultiple = true, dataType = "String"),})
    @RequiresAuthentication
    @PostMapping(value = "/add/permission", params = {"id", "permissionIds"})
    public ResponseBean addPermissionToRole(String id, @NotNull String[] permissionIds) {
        try {
            this.rpservice.delete(new EntityWrapper<RolePermission>().where("role_id={0}", id).notIn("permission_id", Lists.newArrayList(permissionIds)));

            List<RolePermission> rpexist = this.rpservice.selectList(new EntityWrapper<RolePermission>().where("role_id={0}", id).in("permission_id", Lists.newArrayList(permissionIds)));
            List<String> rpexistPermissionIds = CollectionUtils.isEmpty(rpexist)?Lists.newArrayList():rpexist.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
            List<RolePermission> rps = Lists.newArrayList();
            for (int i = 0; i < permissionIds.length; i++) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(id);
                rp.setPermissionId(permissionIds[i]);
                if(!rpexistPermissionIds.contains(permissionIds[i])) rps.add(rp);
            }
            if(!CollectionUtils.isEmpty(rps)) this.rpservice.insertBatch(rps);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
        return new ResponseBean(HttpStatus.OK.value(), "add success", null);
    }
}

