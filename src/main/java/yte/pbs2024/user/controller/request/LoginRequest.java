package yte.pbs2024.user.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest (
    @NotEmpty
    String username,
    @NotEmpty
    String password
){

}
