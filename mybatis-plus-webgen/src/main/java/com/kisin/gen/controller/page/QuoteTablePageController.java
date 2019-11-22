package com.kisin.gen.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-25 10:51
 * @Description:
 */
@Controller
@RequestMapping("/gen/quote/table")
public class QuoteTablePageController {

    @GetMapping("/list")
    String list() {
        return "/gen/quote/table/list";
    }

    @GetMapping("/edit")
    String edit() {
        return "/gen/quote/table/edit";
    }

    @GetMapping("/choose")
    String choose() {
        return "/gen/quote/table/choose";
    }
}
