package com.kisin.gen.controller.page;

import org.springframework.stereotype.Controller;
;import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 11:38
 * @Description:
 */
@Controller
@RequestMapping("/gen")
public class GenPageController {

    @GetMapping("/list")
    String list() {
        return "/gen/list";
    }

    @GetMapping("/edit")
    String edit(){
        return "/gen/edit";
    }
}
