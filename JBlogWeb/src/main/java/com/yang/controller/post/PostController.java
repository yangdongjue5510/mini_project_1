package com.yang.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @GetMapping("/blogAdminPostView")
    public String blogAdminPostView() {
        return "adminPost";
    }
}
