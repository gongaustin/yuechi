package com.gongjun.yuechi.service;

import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.model.Attachment;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
public interface IAttachmentService extends IService<Attachment> {

    List<Attachment> upload(MultipartFile[] files);

}
