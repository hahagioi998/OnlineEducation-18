package com.ghazi.eduservice.service.impl;

import com.ghazi.eduservice.entity.EduCourse;
import com.ghazi.eduservice.entity.EduCourseDescription;
import com.ghazi.eduservice.entity.vo.CourseInfoVo;
import com.ghazi.eduservice.entity.vo.CoursePublishVo;
import com.ghazi.eduservice.mapper.EduCourseMapper;
import com.ghazi.eduservice.service.EduChapterService;
import com.ghazi.eduservice.service.EduCourseDescriptionService;
import com.ghazi.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghazi.eduservice.service.EduVideoService;
import com.ghazi.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-22
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加数据
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0){
            throw new GuliException(20001, "添加课程信息失败");
        }

        //获取添加课程id
        String cid = eduCourse.getId();

        //向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id为课程id
        courseDescription.setId(cid);
        eduCourseDescriptionService.save(courseDescription);

        return cid;
    }

    //根据课程id查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);

        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }

        //修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());

        eduCourseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //删除小节
        videoService.removeVideoByCourseId(courseId);

        //删除章节
        chapterService.removeChapterByCourseId(courseId);

        //删除描述
        eduCourseDescriptionService.removeById(courseId);

        //删除课程
        int result = baseMapper.deleteById(courseId);
        if (result == 0){
            throw new GuliException(20001, "删除失败");
        }
    }

}
