/**
 * This class is responsible control all exception in the system
 * @author thushan vimukthi
 * @version 1.0
 * @since 05th of August 2022
 */
package org.airretailer.exception;

import java.util.stream.Collectors;
import org.airretailer.constant.CommonMessage;
import org.airretailer.util.CommonResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MandatoryFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleMandatoryNotFoundException(MandatoryFieldException ex) {
        List<String> response = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mapToErrorCommonResponse(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicationErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleDuplicationErrorException(DuplicationErrorException ex) {
        List<String> response = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mapToErrorCommonResponse(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleDataNotFoundException(DataNotFoundException ex) {
        List<String> response = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mapToErrorCommonResponse(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSeatsFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleNoSeatsFoundException(NoSeatsFoundException ex) {
        List<String> response = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mapToErrorCommonResponse(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleUnexpectedException(Exception e) {
        List<String> response = Collections.singletonList(CommonMessage.UNEXPECTED_ERROR);
        return new ResponseEntity<>(mapToErrorCommonResponse(response), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus httpStatus, WebRequest request) {
        List<String> response = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mapToErrorCommonResponse(response), HttpStatus.BAD_REQUEST);
    }

    public  CommonResponse mapToErrorCommonResponse(List<String> errors) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessages(errors);
        return commonResponse;
    }
}




