package com.ghazi.eduservice.controller;

import com.ghazi.commonutils.R;
import org.springframework.web.bind.annotation.*;


/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-20 11:26
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){

        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://img2.yuntouxiang.com/file/20190626/d1f6b6e8335def46af113d25d6a35472.jpg");
    }
}
