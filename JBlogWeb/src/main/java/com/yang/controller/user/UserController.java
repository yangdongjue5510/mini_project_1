/*
 * UserController.java
 *
 * 2021-11-22
 *
 * copyright by yangdongjue5510
 * all rights reserved.
 */
package com.yang.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 사용자 관련 컨트롤러
 */
@Controller
public class UserController {

    @GetMapping("/loginView")
    public String loginView(HttpSession session){
        if (session.getAttribute("user") == null) {
            return "bloglogin";
        }
        return "redirect:/";
    }
}
