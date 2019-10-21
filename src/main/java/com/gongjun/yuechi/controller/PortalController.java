package com.gongjun.yuechi.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Dept;
import com.gongjun.yuechi.model.News;
import com.gongjun.yuechi.model.User;
import com.gongjun.yuechi.service.IDeptService;
import com.gongjun.yuechi.service.INewsService;
import com.gongjun.yuechi.service.IUserService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description:门户主页相关接口
 * @Author:GongJun
 * @Date:2019/10/21
 */
@Api(value = "/portal", description = "门户前端控制器")
@RestController
@RequestMapping("/portal")
@Validated
public class PortalController {


    @Autowired
    private INewsService nservice;


    /**
     * 医院简介
     * */
    @ApiOperation(value = "医院简介", notes = "医院简介")
    @GetMapping("introduce")
    public ResponseBean introduce (@RequestParam(defaultValue = "医院简介") String type){

        List<News> news = this.nservice.selectList(new EntityWrapper<News>().where("type={0}",type).orderDesc(Lists.newArrayList("ctime")));
        News introduce = new News();
        if(CollectionUtils.isEmpty(news))  introduce = news.get(0);

        return new ResponseBean(HttpStatus.OK.value(),"",introduce);
    }

    /**
     * 板块列表
     * */

    @ApiOperation(value = "板块列表", notes = "板块列表")
    @GetMapping("plate")
    public ResponseBean plateList (@RequestParam String type){

        List<News> news = this.nservice.selectList(new EntityWrapper<News>().where("type={0}",type).orderDesc(Lists.newArrayList("ctime")));

        return new ResponseBean(HttpStatus.OK.value(),"",news);
    }

    /**
     * 重点科室
     * */
    @Autowired
    private IDeptService dservice;
    @ApiOperation(value = "科室", notes = "科室")
    @GetMapping("dept")
    public ResponseBean deptList (Integer isImprotant){

        List<Dept> depts = this.dservice.selectList(new EntityWrapper<Dept>().where("type={0}",1).orderDesc(Lists.newArrayList("ctime")));

        return new ResponseBean(HttpStatus.OK.value(),"",depts);
    }


    /**
     * 医生列表
     * */
    @Autowired
    private IUserService uservice;

    @GetMapping("doctor")
    public ResponseBean doctorList (){

        List<User> doctors = this.uservice.selectList(new EntityWrapper<User>().where("type={0}",1).orderDesc(Lists.newArrayList("ctime")));

        return new ResponseBean(HttpStatus.OK.value(),"",doctors);
    }

    /**
     * 医生排班信息
     * */



    /**
     * 文章详情
     * */
    @GetMapping(value = "doctor",params = {"id"})
    public ResponseBean doctorList (@NotBlank String id){
         News news = this.nservice.selectById(id);
        return new ResponseBean(HttpStatus.OK.value(),"",news);
    }


}
