package yte.pbs2024.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import yte.pbs2024.user.entity.Users;

public record UserAddRequest (
    @NotBlank
    String name,
    @NotBlank
    String surname,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String picture


){
    public Users toEntity(){
        return new Users(name,surname,email,picture);
    }

        }
