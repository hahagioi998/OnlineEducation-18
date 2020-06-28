package com.ghazi.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-22 16:18
 */
@Data
public class OneSubject {

    private String id;

    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
