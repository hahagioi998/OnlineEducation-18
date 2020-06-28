package com.ghazi.servicebase.exceptionhandler;


import com.ghazi.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-17 11:39
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行统一异常处理");
    }

    //指定出现什么异常执行这个方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行特定异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){

        log.error(e.getMessage());
        e.printStackTrace();

        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
