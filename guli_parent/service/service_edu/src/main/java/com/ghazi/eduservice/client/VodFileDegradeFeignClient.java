package com.ghazi.eduservice.client;

import com.ghazi.commonutils.R;

import java.util.List;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-28 20:12
 */
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAliyunVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
