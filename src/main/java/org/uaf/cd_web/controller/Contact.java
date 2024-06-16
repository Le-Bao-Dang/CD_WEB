package org.uaf.cd_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Contact {

    @GetMapping("/chatbox")
    public String Showchatbox(){
        return "chat";
    }
}
