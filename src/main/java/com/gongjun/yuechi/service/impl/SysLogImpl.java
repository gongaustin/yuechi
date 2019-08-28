package com.gongjun.yuechi.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gongjun.yuechi.mapper.SysLogMapper;
import com.gongjun.yuechi.model.log.SysLog;
import com.gongjun.yuechi.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * Author:GongJun
 * Date:2019/1/17
 */
@Service
public class SysLogImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
}
