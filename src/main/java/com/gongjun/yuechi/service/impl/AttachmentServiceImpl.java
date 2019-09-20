package com.gongjun.yuechi.service.impl;

import com.gongjun.yuechi.core.bean.ResponseBean;
import com.gongjun.yuechi.core.constant.TimeConstant;
import com.gongjun.yuechi.model.Attachment;
import com.gongjun.yuechi.mapper.AttachmentMapper;
import com.gongjun.yuechi.service.IAttachmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author GongJun
 * @since 2019-08-28
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${prop.upload-folder}")
    private String FILE_PATH;
    @Override
    public List<Attachment> upload(MultipartFile[] files) {
        List<Attachment> ats = Lists.newArrayList();
        if(null != files){
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                if(null == file) continue;
                Attachment at = this.writeFile(file);
                ats.add(at);
            }
        }
        return ats;
    }

    private Attachment writeFile(MultipartFile file){
        Attachment at = new Attachment();
        byte[] bytes;
        Path result = null;
        try {
            bytes = file.getBytes();
            String fileName = file.getOriginalFilename(); at.setFileName(fileName);
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1); at.setExt(fileSuffix);
            long nowTimes = System.currentTimeMillis();
            String nowDate = new SimpleDateFormat(TimeConstant.DATE_FORMAT).format(Calendar.getInstance().getTime());
            String newFileName = fileName.substring(0,fileName.lastIndexOf("."))+"-"+String.valueOf(nowTimes)+"."+fileSuffix;
            Path path = Paths.get(FILE_PATH+"/"+nowDate);
            if(!Files.isWritable(path)) Files.createDirectories(path);
            at.setUrl("http://120.24.241.113/upload/file"+"/"+nowDate+"/"+newFileName);
            this.baseMapper.insert(at);
            result = Files.write(Paths.get(FILE_PATH+"/"+nowDate+"/"+newFileName),bytes);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        System.out.println(result.getFileName());
        return at;
    }
}
