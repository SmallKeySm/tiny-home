package com.wzj.controller.auth;

import com.wzj.form.auth.MUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/register")
    public String toRegister(){
        return "/auth/register";
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(MUserForm mUserForm){
        return "";
    }
}
