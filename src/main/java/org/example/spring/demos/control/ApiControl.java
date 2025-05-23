package org.example.spring.demos.control;

import org.example.spring.demos.trans.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * restful接口类
 */
@RestController
@RequestMapping("/api/v1/")
public class ApiControl {

    @Resource
    private Converter convert;

    @GetMapping("test")
    public String getTest() {
        final Source source = Source.builder()
                                    .id(1L)
                                    .age(18L)
                                    .userNick("Nick")
                                    .build();
        final Target target = convert.convert(source);
        System.out.println(target);
        final VO vo = VO.builder()
                        .id(1L)
                        .age(18L)
                        .userNick("Nick")
                        .build();
        final DTO dto = convert.convert(vo);
        System.out.println(dto);
        final VO vo1 = convert.convert(dto);
        System.out.println(vo1);
        return "test";
    }

    @GetMapping("test/{name}")
    public String getPathTest(@PathVariable String name) {
        return "test" + name;
    }

    @PostMapping("test")
    public String postTest(@RequestBody String body) {
        return "test" + body;
    }
}
