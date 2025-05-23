package org.example.spring.demos.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @author 12
 * Create By 下午7:21
 */
@Data
@Builder
public class ApiException extends Exception{
    private int code;
    private String message;
}
