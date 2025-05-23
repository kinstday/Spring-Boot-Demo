package org.example.spring.demos.config;

import com.google.common.collect.ImmutableMap;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.example.spring.demos.exception.ApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author 12
 * Create By 下午7:18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(HttpServletRequest http, ApiException e) {
        System.out.println(404);
        Map<String, Object> map = ImmutableMap.of(
                "key1", "value1",
                "key2", "value2"
        );
        return map;
    }
}
