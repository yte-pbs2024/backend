package yte.pbs2024.exception;


import yte.pbs2024.common.response.MessageResponse;
import yte.pbs2024.common.response.MessageType;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found " + id );
    }
}