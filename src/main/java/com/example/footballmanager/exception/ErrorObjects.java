package com.example.footballmanager.exception;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ErrorObjects {

    private long httpStatus;
    private String detail;
    private LocalDate time;
}
