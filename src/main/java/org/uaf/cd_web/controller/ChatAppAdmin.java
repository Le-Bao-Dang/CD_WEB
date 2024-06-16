package org.uaf.cd_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ChatAppAdmin {
    @GetMapping("/chatApp")
    public String PageChatApp(){
        return "chatAdmin";
    }
}
