package org.jeecg.modules.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping(value = "/hi")
    public Result<String> hello() {
        Result<String> result= new Result<String>();
        result.setMessage("sds");
        result.setSuccess(true);
        return result;
    }

}
