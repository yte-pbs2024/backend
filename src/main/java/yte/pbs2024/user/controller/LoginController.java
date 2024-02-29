package yte.pbs2024.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.pbs2024.user.controller.request.LoginRequest;
import yte.pbs2024.user.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
}
