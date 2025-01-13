package team.sheet.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class index {
    
    @GetMapping("/")
    public String getMethodName() {
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "builder";
    }
}
