package com.gongjun.tools.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gongjun.tools.mapper.SysLogMapper;
import com.gongjun.tools.model.log.SysLog;
import com.gongjun.tools.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * Author:GongJun
 * Date:2019/1/17
 */
@Service
public class SysLogImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
}
