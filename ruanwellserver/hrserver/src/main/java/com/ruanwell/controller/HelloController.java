package com.ruanwell.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robbinqin on 2018/4/10.
 */
@RestController
public class HelloController {
    @RequestMapping("/employee/basic/hello")
    public String hello1() {
        return "hello1";
    }

    @RequestMapping("/system/init/hello")
    public String hello2() {
        return "hello2";
    }
}
