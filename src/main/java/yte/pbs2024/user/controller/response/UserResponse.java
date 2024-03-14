package yte.pbs2024.user.controller.response;

import yte.pbs2024.user.entity.Users;

public record UserResponse(

        Long id,
        String name,
        String surname,
        String email,
        String picture
) {
    public UserResponse(Users users) {
        this(users.getId(),users.getName(), users.getSurname(), users.getEmail(), users.getPicture());
    }
}
