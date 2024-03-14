package yte.pbs2024.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.pbs2024.user.controller.request.LoginRequest;
import yte.pbs2024.user.service.LoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginService loginService;
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest){
        return loginService.login(loginRequest);

    }
}
