package org.sixcity.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_SUPER_ADMIN')")
    public String index() {
        return "index";
    }
}
