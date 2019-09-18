package com.gongjun.yuechi.service;

import com.gongjun.yuechi.model.Leader;
import com.baomidou.mybatisplus.service.IService;
import com.gongjun.yuechi.model.vo.LeaderVo;

/**
 * <p>
 * 领导人表 服务类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
public interface ILeaderService extends IService<Leader> {

    LeaderVo findLeaderById(String id);

}
