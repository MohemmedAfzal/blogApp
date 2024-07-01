//package com.example.blogapp.ExceptionHandling;
//
//import com.example.blogapp.Controllers.APIResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<APIResponse> responseNotFoundHandler(ResourceNotFoundException e){
//    String message=e.getMessage();
//    return new ResponseEntity<APIResponse>(new APIResponse(message,false), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException e){
//    Map<String,String> map=new HashMap<>();
//    e.getBindingResult().getAllErrors().forEach(error->{
//        String fieldName=((FieldError)error).getField();
//        String defaultMessage=error.getDefaultMessage();
//        map.put(fieldName,defaultMessage);
//    });
//        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
//    }
//}
