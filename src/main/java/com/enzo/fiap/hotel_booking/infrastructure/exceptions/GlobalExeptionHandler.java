package com.enzo.fiap.hotel_booking.infrastructure.exceptions;

import com.enzo.fiap.hotel_booking.dto.ErrorResponseDto;
import com.enzo.fiap.hotel_booking.dto.FieldErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldErrorResponseDto> fieldErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            FieldErrorResponseDto fieldErrorDto = FieldErrorResponseDto.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).build();
            fieldErrors.add(fieldErrorDto);
        });

        ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.BAD_REQUEST.value()).fieldErrors(fieldErrors).timestamp(LocalDateTime.now()).build();

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DuplicatedRoomException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicatedRoomException(DuplicatedRoomException ex){
        ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BookedRoomException.class)
    public ResponseEntity<ErrorResponseDto> handleBookedRoomException(BookedRoomException ex){
        ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(InvalidReservationStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidReservationStatusException(InvalidReservationStatusException ex){
        ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


    @ExceptionHandler(InvalidBookingDateException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidBookingDateException(InvalidBookingDateException ex){
         ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRoomNotFoundException(RoomNotFoundException ex){
        ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleReservationNotFoundException(ReservationNotFoundException ex){
        ErrorResponseDto error = ErrorResponseDto.builder().status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage()).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
