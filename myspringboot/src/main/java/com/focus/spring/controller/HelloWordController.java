package com.focus.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * focus Create in 14:43 2019/3/29
 */
@RestController
@RequestMapping("/focus")
public class HelloWordController {
    @RequestMapping("/hello")
    public String info(){
        return "hello word hot restart";
    }
}
