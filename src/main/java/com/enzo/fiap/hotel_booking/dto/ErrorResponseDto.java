package com.enzo.fiap.hotel_booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDto(
        int status,
        String message,
        LocalDateTime timestamp,
        List<FieldErrorResponseDto> fieldErrors
) {
}
