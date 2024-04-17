package yte.pbs2024.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import yte.pbs2024.user.entity.Users;

public record UserAddRequest (
    @NotEmpty(message = "xx")
    String name,
    @NotEmpty
    String surname,
    @NotBlank
    @Email
    String email,
    @NotEmpty
    String picture


){
    public Users toEntity(){
        return new Users(name,surname,email,picture);
    }

        }
