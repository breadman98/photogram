package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    // 런타임 익셉션이 발동하는 모든 것을 이 함수가 가로챌것이다.
    // 클라이언트에게 응답하므로 Script를 리턴
    public String validationException(CustomValidationException e){
       return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    // Ajax 통신에 대한 에러이므로 CMResponseDto를 리턴
    public ResponseEntity<CMResponseDto<?>> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMResponseDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }
}

//public class CMResponseDto<T> {
//    private int errCode; // 1:성공 -1:실패
//    private String message;
//    private T data;
//}