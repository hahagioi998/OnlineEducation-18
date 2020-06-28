package com.ghazi.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List; /**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-27 22:19
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    //删除多个视频
    void removeMoreAliyunVideo(List videoIdList);
}
