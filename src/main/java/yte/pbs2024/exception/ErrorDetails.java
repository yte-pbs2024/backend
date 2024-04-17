package yte.pbs2024.exception;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ErrorDetails {


    private int statusCode;

    private LocalDateTime timeStamp;

    private String errorMessage;
    private String status;


}
