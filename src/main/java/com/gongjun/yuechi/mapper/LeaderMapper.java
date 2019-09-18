package com.gongjun.yuechi.mapper;

import com.gongjun.yuechi.model.Leader;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gongjun.yuechi.model.vo.LeaderVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 领导人表 Mapper 接口
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
public interface LeaderMapper extends BaseMapper<Leader> {

    LeaderVo findLeaderById(@Param("id") String id);

}
