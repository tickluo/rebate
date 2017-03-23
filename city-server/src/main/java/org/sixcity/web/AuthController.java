package org.sixcity.web;

import com.alibaba.fastjson.JSONObject;
import org.sixcity.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/auth", method = RequestMethod.GET)
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "auth/register";
    }

    @ResponseBody
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String registerPost(@RequestBody JSONObject regFormData) {
        User user = JSONObject.toJavaObject(regFormData, User.class);

        String code = regFormData.getString("code");

        return "state";
    }
}
