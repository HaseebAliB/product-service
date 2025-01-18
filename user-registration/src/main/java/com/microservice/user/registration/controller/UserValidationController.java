package com.microservice.user.registration.controller;

import com.microservice.user.registration.Service.NotificationService;
import com.microservice.user.registration.controller.model.UserDto;
import com.microservice.user.registration.controller.model.UserStatus;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
@RequiredArgsConstructor
public class UserValidationController {

   private final NotificationService notificationService;
    @PostMapping("/register")
public ResponseEntity<?> validateUser(@Valid @RequestBody UserDto userDto){
 log.debug("Validating User for Registration");
        UserDto vo = UserDto.builder()
         .email(userDto.getEmail())
         .name(userDto.getName())
         .userRole(userDto.getUserRole())
         .userStatus(UserStatus.VERIFIED)
         .build();
        log.debug("User Validated successfully");
        log.debug("sending notificaiton!");

        notificationService.sendUserDetails(vo);

        return new ResponseEntity<>(vo, HttpStatus.OK);

}

@ExceptionHandler(value = ConstraintViolationException.class)
public ResponseEntity<List> ErrorResponse(ConstraintViolationException e){
        log.error("User Validation failed!");
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(ce -> errors.add(ce.getPropertyPath()+ " : "+ ce.getMessage()));
 return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
}

    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    public ResponseEntity<?> ErrorResponse(MethodArgumentNotValidException ex){
        log.error("User Validation failed!");
        BindingResult bindingResult = ex.getBindingResult();

        // Iterate over the FieldErrors to collect all validation messages
        StringBuilder errorMessages = new StringBuilder();
        Map<String,String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // Add field name and its validation message
            errorMessages.append("Field: ")
                    .append(fieldError.getField())
                    .append(" - Error: ")
                    .append(fieldError.getDefaultMessage())
                    .append("\n");
        }
        errors.put("error",errorMessages.toString());
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


}
