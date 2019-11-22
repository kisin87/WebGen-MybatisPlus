package com.kisin.gen.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-14 17:25
 * @Description:
 */
@Controller
@RequestMapping("")
public class indexController {

    @GetMapping
    String index(){
        return "redirect:/gen/list";
    }


    @GetMapping("/test")
    String test() {
        return "/test";
    }
}
