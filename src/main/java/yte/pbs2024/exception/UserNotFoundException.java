package yte.pbs2024.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found " + id );
    }
}