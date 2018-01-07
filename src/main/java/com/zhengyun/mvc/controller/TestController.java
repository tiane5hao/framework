package com.zhengyun.mvc.controller;

import com.zhengyun.mvc.annotation.Controller;
import com.zhengyun.mvc.annotation.RequestMapping;

/**
 * Created by 听风 on 2017/12/30.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/getName", method = RequestMapping.GET)
    public String getName(String id){
        return "query " + id + ", result = tom";
    }
}
