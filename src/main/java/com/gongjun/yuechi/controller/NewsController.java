package com.gongjun.yuechi.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

}

