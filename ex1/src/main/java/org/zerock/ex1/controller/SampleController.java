package org.zerock.ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @GetMapping("/home")
    public String[] hello(){
        return new String[]{"Hello World"};
    }
}
