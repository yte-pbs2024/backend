package yte.pbs2024.exception;

import yte.pbs2024.common.response.MessageResponse;
import yte.pbs2024.common.response.MessageType;

public class UserExistException extends  RuntimeException{
    public UserExistException(String message){
        super(message);
    }
}
