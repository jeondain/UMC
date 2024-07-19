package umc.spring.study.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class RootRestController {

    @GetMapping("health")
    public String health() {
        return "OK";
    }
}
