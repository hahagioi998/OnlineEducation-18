package com.ghazi.eduservice.service;

import com.ghazi.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ghazi.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-21
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}

