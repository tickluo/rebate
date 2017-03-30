package org.sixcity.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/developer")
public class DeveloperController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String index() {
        return "developer/index";
    }
}
