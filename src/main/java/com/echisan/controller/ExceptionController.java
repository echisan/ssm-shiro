package com.echisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author E73AN
 */
@Controller
public class ExceptionController {

    @GetMapping(value = "/404")
    public String page404(){
        return "/404.jsp";
    }
}
