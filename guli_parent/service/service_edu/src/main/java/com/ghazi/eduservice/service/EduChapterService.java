package com.ghazi.eduservice.service;

import com.ghazi.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ghazi.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-22
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    //根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
