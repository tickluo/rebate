package org.sixcity.web;

import model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/rebate", method = RequestMethod.POST)
public class ApiController {

    @RequestMapping(value = "saveProduct")
    public Result saveProduct() {


        return Result.createSuccessResult();
    }
}
