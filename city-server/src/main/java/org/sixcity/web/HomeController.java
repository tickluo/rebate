package org.sixcity.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home", method = RequestMethod.GET)
public class HomeController {

    @RequestMapping(value = "/index")
    @PreAuthorize("hasRole('USER')")
    public String index() {
        return "index";
    }
}
