package com.gestaotech.api.infra.config.filter;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RestErrorMessage {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;

    public RestErrorMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }


}
