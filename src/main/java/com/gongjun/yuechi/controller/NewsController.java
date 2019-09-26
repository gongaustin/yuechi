package com.gongjun.yuechi.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.utils.JWTUtil;
import com.gongjun.yuechi.model.Attachment;
import com.gongjun.yuechi.model.News;
import com.gongjun.yuechi.model.Permission;
import com.gongjun.yuechi.service.IAttachmentService;
import com.gongjun.yuechi.service.INewsService;
import com.gongjun.yuechi.service.IUserService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 新闻资讯列表 前端控制器
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Api(value = "/news", description = "新闻资讯前端控制器接口")
@RestController
@RequestMapping("/news")

public class NewsController {

    @Autowired
    INewsService service;

    @Autowired
    IAttachmentService atservice;


    @ApiOperation(value = "分页查询文章", notes = "分页查询文章")
    @RequiresAuthentication
    @GetMapping(value = "/page")
    public ResponseBean page(Page page,String keyword){
        EntityWrapper ew = new EntityWrapper();
        ew.orderAsc(Lists.newArrayList("ctime"));
        if(StringUtils.isNotBlank(keyword)) ew.like("title",keyword).or("author like{0}","%"+keyword+"%").or("type like{0}","%"+keyword+"%");
        try {
         page = this.service.selectPage(page,ew);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"find success",page);
    }


    @ApiOperation(value = "ID查询文章", notes = "ID查询文章")
    @RequiresAuthentication
    @GetMapping(value = "/findNewsById",params = {"id"})
    public ResponseBean findNewsById(String id){
        News news;
        try {
            news = this.service.selectById(id);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"delete success",news);
    }

    @Autowired
    private IUserService uservice;

    @ApiOperation(value = "查询用户模块权限", notes = "查询用户模块权限")
    @RequiresAuthentication
    @GetMapping(value = "/findmoduleByUserToken")
    public ResponseBean findmoduleByUserToken(@RequestParam(defaultValue = "3") Integer type){

        List<Permission> permissions = this.uservice.selectUserModulessByWrapper(JWTUtil.getUserId());
        return new ResponseBean(HttpStatus.OK.value(),"find success",permissions);
    }




    @ApiOperation(value = "上传附件接口", notes = "上传附件接口")
    @PostMapping(value = "/upload/ats")
    @CrossOrigin
    public ResponseBean add(@RequestPart MultipartFile[] files){
        List<Attachment> ats = atservice.upload(files);
        List<String> urls = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(ats))  urls = ats.stream().map(Attachment::getUrl).collect(Collectors.toList());
        return new ResponseBean(HttpStatus.OK.value(),"add success",urls);
    }

    @ApiOperation(value = "添加、发表文章", notes = "添加、发表文章")
    @RequiresAuthentication
    @PostMapping(value = "/add",params = {"title"})
    public ResponseBean add(News news){
        try {
            news.setAuthor(this.uservice.selectById(JWTUtil.getUserId()).getRealname());
        } catch (Exception e) {
            news.setAuthor(null);
        }
        try {
            this.service.insert(news);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"add success",null);
    }


    @ApiOperation(value = "编辑、修改文章", notes = "编辑、修改文章")
    @RequiresAuthentication
    @PostMapping(value = "/edit",params = {"id"})
    public ResponseBean edit(News news){
        try {
            this.service.updateById(news);
        } catch (Exception e) {
            return new ResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return new ResponseBean(HttpStatus.OK.value(),"edit success",null);
    }


    @ApiOperation(value = "删除文章", notes = "删除文章")
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

