package com.kisin.gen.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 17:06
 * @Description:
 */
@Controller
@RequestMapping("/gen/table")
public class TableInfoPageController {

    @GetMapping("/edit")
    String edit(){
        return "/gen/table/edit";
    }
}
