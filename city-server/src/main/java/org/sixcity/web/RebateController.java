package org.sixcity.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/rebate")
public class RebateController {

    @RequestMapping(value = "/rebateCash", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String rebateCash() {
        return "rebate/rebateCash";
    }

    @RequestMapping(value = "/cashRecord", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String cashRecord() {
        return "rebate/cashRecord";
    }

    @RequestMapping(value = "/rebateSite", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String rebateSite() {
        return "rebate/rebateSite";
    }
}
