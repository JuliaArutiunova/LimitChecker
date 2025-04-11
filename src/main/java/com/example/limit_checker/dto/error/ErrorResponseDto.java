package com.example.limit_checker.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private int status;
    private List<String> message;
}
