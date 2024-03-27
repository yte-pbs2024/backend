package yte.pbs2024.user.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import yte.pbs2024.user.entity.Users;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,
        String name,
        String surname,
        String email,
        String picture,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime lastModifiedDate,

        String socialSecurityNumber,

        String gender,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime birthDate,

        String phoneNumber
) {


    public UserResponse(Users users) {
        this(users.getId(), users.getName(), users.getSurname(), users.getEmail(), users.getPicture(), users.getCreatedDate(), users.getLastModifiedDate(), users.getSocialSecurityNumber(), users.getGender(), users.getBirthDate(), users.getPhoneNumber());
    }
}
