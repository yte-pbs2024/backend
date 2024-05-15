package yte.pbs2024.user.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import yte.pbs2024.user.entity.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record UserResponse(
        Long id,
        String name,
        String surname,
        String email,
        String picture,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime lastModifiedDate,
        String tc,
        String gender,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate birthDate,
        String phoneNumber,
        List<AuthorityResponse> authorities
) {
    public UserResponse(Users users) {
        this(
                users.getId(),
                users.getName(),
                users.getSurname(),
                users.getEmail(),
                users.getPicture(),
                users.getCreatedDate(),
                users.getLastModifiedDate(),
                users.getTc(),
                users.getGender(),
                users.getBirthDate(),
                users.getPhoneNumber(),
                users.getAuthorities().stream()
                        .map(authority -> new AuthorityResponse(authority.getId(), authority.getAuthority()))
                        .collect(Collectors.toList())
        );
    }
}
