package org.example.spring.demos.trans;

import lombok.Builder;
import lombok.Data;

/**
 * @author 12
 * Create By 下午5:18
 */

@Data
@Builder
public class Source {
    private Long id;
    private Long age;
    private String userNick;
}