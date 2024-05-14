package yte.pbs2024.user.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UserUpdateRequest(
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank @Email String email,
        @NotBlank String picture,
        @NotBlank @Size(min = 11, max = 11, message = "TC must be exactly 11 characters") String tc,
        @NotBlank String gender,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        @NotBlank String phoneNumber,
        @NotNull List<Long> authorities


) {

}
