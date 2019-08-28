package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.model.Log;
import com.gongjun.yuechi.mapper.LogMapper;
import com.gongjun.yuechi.service.ILogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
