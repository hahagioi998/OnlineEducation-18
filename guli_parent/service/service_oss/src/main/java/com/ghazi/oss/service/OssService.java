package com.ghazi.oss.service;

import org.springframework.web.multipart.MultipartFile; /**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-21 18:22
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
