package yte.pbs2024.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/home")
    public String home(){
        return "Hello";
    }
}
