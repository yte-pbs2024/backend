package yte.pbs2024.exception;

public class UserExistException extends  RuntimeException{
    public UserExistException(String message){
        super(message);
    }
}
