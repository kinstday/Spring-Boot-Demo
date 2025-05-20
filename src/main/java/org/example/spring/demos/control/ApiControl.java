package org.example.spring.demos.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * restful接口类
 */
@RestController
@RequestMapping("/api/v1/")
public class ApiControl {
    @GetMapping("test")
    public String test()
    {
        return "test";
    }
}
