package com.kyuwon.spring.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/board")
public class boardController {
    @GetMapping("/user")
    public String testUser() {
        return "hello user";
    }

    @GetMapping("/admin")
    public String testAdmin() {
        return "hello admin";
    }
}
