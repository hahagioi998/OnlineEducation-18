package com.ghazi.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ghazi.eduservice.entity.EduSubject;
import com.ghazi.eduservice.entity.excel.SubjectData;
import com.ghazi.eduservice.entity.subject.OneSubject;
import com.ghazi.eduservice.entity.subject.TwoSubject;
import com.ghazi.eduservice.listener.SubjectExcelListener;
import com.ghazi.eduservice.mapper.EduSubjectMapper;
import com.ghazi.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){

        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> TwoSubjectList = baseMapper.selectList(wrapperTwo);

        List<OneSubject> finalSubjectList = new ArrayList<>();

        for (EduSubject eduSubject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
//            os.setId(subject.getId());
//            os.setTitle(subject.getTitle());
            BeanUtils.copyProperties(eduSubject, oneSubject);

            finalSubjectList.add(oneSubject);

            List<TwoSubject> twoSubjects = new ArrayList<>();

            for (EduSubject twoEduSubject : TwoSubjectList){
                if(twoEduSubject.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject, twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }

            oneSubject.setChildren(twoSubjects);
        }

        return finalSubjectList;
    }
}
