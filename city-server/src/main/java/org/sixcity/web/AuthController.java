package org.sixcity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/Login/GetAuthCode", method = RequestMethod.GET)
    public String list(){
        return "";
    }
}
