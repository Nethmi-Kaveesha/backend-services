package lk.kavee.expenseservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("type", e.getClass().getName());
        e.printStackTrace();
        return ResponseEntity.status(500).body(body);
    }
}
