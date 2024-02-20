package yte.pbs2024.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/standard-user")
    @PreAuthorize("hasAnyRole('STANDARD_USER','AUTHORIZED_USER','SUPER_USER','ADMIN')")
    public String standard_user(){
        return "standard user";
    }
    @GetMapping("/authorized-user")
    @PreAuthorize("hasAnyRole('AUTHORIZED_USER','SUPER_USER','ADMIN')")
    public String authorize_user(){
        return "authorized user";
    }
    @GetMapping("/super-user")
    @PreAuthorize("hasAnyRole('SUPER_USER','ADMIN')")
    public String super_user(){
        return "super user";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "admin";
    }

}
