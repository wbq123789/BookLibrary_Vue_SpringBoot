/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试接口
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/hello")
    public String Test() {
        return "hello";
    }
}
