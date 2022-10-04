package com.hzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EncodingController {

    @RequestMapping(value = "/e/t",method = RequestMethod.POST)
    public String encode(@RequestParam String name, Model model){
        model.addAttribute("msg",name);
        return "test";
    }
}
