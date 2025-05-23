package org.example.spring.demos.trans;

import lombok.Builder;
import lombok.Data;

/**
 * @author 12
 * Create By 下午6:07
 */

@Data
@Builder
public class VO {
    private Long id;
    private Long age;
    private String userNick;
}