package yte.pbs2024.user.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import yte.pbs2024.user.entity.Users;

import java.time.LocalDate;

public record UserAddRequest(
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank @Email String email,
        @NotBlank String picture,
        @NotBlank @Size(min = 11, max = 11, message = "TC must be exactly 11 characters") String tc,
        @NotBlank String gender,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthDate
) {
    public Users toEntity() {
        return new Users(name, surname, email, picture, tc, gender, birthDate);
    }

}
