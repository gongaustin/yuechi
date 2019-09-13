package com.gongjun.yuechi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.Md5;
import com.gongjun.yuechi.model.*;
import com.gongjun.yuechi.model.vo.UserVo;
import com.gongjun.yuechi.service.IUserRoleService;
import com.gongjun.yuechi.service.IUserService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理员管理 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/manager", description = "管理员管理前端控制器接口")
@RestController
@RequestMapping("/manager")
public class ManagerController {


    @Autowired
    private IUserService service;

    @Autowired
    private IUserRoleService urservice;


    @ApiOperation(value = "分页查询管理员", notes = "分页查询管理员")
    @RequiresAuthentication
    @GetMapping("/page")
    public ResponseBean selectManagerPage(Page page, String keyword){

        EntityWrapper ew  = new EntityWrapper();
        ew.and("uu.backup={0}","管理员");
        if(StringUtils.isNotBlank(keyword)){
            ew.like("uu.username",keyword)
                    .or("uu.realname like{0}","%"+keyword+"%")
                    .or("uu.position like{0}","%"+keyword+"%")
                    .or("ud.dept_name like{0}","%"+keyword+"%");
        }
        List<UserVo> users = this.service.selectUserVoPage(page,ew);
        if(!CollectionUtils.isEmpty(users)){
            users.forEach(e->{e.setPassword(null);e.setSalt(null);});
        }
        page.setRecords(users);
        return new ResponseBean(HttpStatus.OK.value(),"",page);
    }


    @ApiOperation(value = "查询所有已启用的管理员", notes = "查询所有已启用的管理员")
    @RequiresAuthentication
    @GetMapping("/list")
    public ResponseBean selectRoleList() {
        List<User> managers = this.service.selectList(new EntityWrapper<User>().where("status={0}", 1).and("backup={0}","管理员"));
        return new ResponseBean(HttpStatus.OK.value(), "", managers);
    }

    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    @RequiresAuthentication
    @PostMapping(value = "/add", params = {"username","password"})
    public ResponseBean addRole(User user) {

        user.setBackup("管理员");

        try {
            this.service.insert(user);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }

        return new ResponseBean(HttpStatus.OK.value(), "add success", null);

    }

    @ApiOperation(value = "编辑管理员", notes = "编辑管理员")
    @RequiresAuthentication
    @PostMapping(value = "/edit",params = {"id"})
    public ResponseBean editUser(User user){
        String password = user.getPassword();
        if(StringUtils.isNotBlank(password)){
            String passwordMD5 = Md5.md5Encode(password);
            user.setPassword(passwordMD5);
            user.setSalt(passwordMD5);
        }
        try {
            this.service.updateById(user);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"edit success",user);
    }

    @ApiOperation(value = "禁用/启用管理员", notes = "禁用/启用管理员")
    @RequiresAuthentication
    @PostMapping(value = "/forbidden", params = {"id", "status"})
    public ResponseBean forbiddenRole(String id, @Min(0) @Max(1) Integer status) {

        User manager = new User();
        manager.setId(id);
        manager.setStatus(status);

        try {
            this.service.updateById(manager);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }

        return new ResponseBean(HttpStatus.OK.value(), (status == 0 ? "disable":"enable") + " success", null);

    }

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @RequiresAuthentication
    @PostMapping(value = "/delete", params = {"id"})
    public ResponseBean deleteManager(String id) {

        try {
            this.service.deleteById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
        return new ResponseBean(HttpStatus.OK.value(), "delete success", null);
    }

    @ApiOperation(value = "给管理员分配角色", notes = "给管理员分配角色")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id", value = "角色ID", required = true, dataType = "String"), @ApiImplicitParam(paramType = "query", name = "roleIds", value = "角色ID", required = true, allowMultiple = true, dataType = "String"),})
    @RequiresAuthentication
    @PostMapping(value = "/add/role", params = {"id", "roleIds"})
    public ResponseBean addRoleToManager(String id, @NotNull String[] roleIds) {
        try {
            this.urservice.delete(new EntityWrapper<UserRole>().where("user_id={0}", id).notIn("role_id", Lists.newArrayList(roleIds)));
            List<UserRole> urexist = this.urservice.selectList(new EntityWrapper<UserRole>().where("user_id={0}", id).in("role_id", Lists.newArrayList(roleIds)));
            List<String> urexistRoleIds = CollectionUtils.isEmpty(urexist)?Lists.newArrayList():urexist.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<UserRole> urs = Lists.newArrayList();
            for (int i = 0; i < roleIds.length; i++) {
                UserRole ur = new UserRole();
                ur.setUserId(id);
                ur.setRoleId(roleIds[i]);
                if(!urexistRoleIds.contains(roleIds[i])) urs.add(ur);
            }
            if(!CollectionUtils.isEmpty(urs)) this.urservice.insertBatch(urs);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
        return new ResponseBean(HttpStatus.OK.value(), "add success", null);
    }

    @ApiOperation(value = "根据管理员ID查询已经拥有的角色ID的数组", notes = "根据管理员ID查询已经拥有的角色ID的数组")
    @RequiresAuthentication
    @GetMapping(value = "/getRolesByManagerId",params = {"id"})
    public ResponseBean getRolesByManagerId(@NotBlank String id) {
        List<String> ids = Lists.newArrayList();
        try {
            List<Role> roles = this.service.getRolesByManagerId(id);
            if(!CollectionUtils.isEmpty(roles)) ids = roles.stream().map(Role::getId).collect(Collectors.toList());
        } catch (Exception e){
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"find success",ids);
    }
    
}
