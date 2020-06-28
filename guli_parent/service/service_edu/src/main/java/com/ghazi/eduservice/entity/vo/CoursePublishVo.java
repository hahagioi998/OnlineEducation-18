package com.ghazi.eduservice.entity.vo;

import lombok.Data;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-26 22:22
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
