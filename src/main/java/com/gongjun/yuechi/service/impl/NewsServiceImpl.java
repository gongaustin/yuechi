package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.News;
import com.gongjun.yuechi.mapper.NewsMapper;
import com.gongjun.yuechi.service.INewsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻资讯列表 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
