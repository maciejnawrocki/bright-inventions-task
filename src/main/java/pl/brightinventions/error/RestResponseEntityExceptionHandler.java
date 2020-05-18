package pl.brightinventions.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.brightinventions.book.exception.BookServiceException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String INVALID_REQUEST = "Invalid request";

  @ExceptionHandler(BookServiceException.class)
  public ResponseEntity<ApiError> handleBookServiceException(BookServiceException e) {
    log.error(e.getMessage(), e);
    return ResponseEntity.badRequest()
        .body(
            new ApiError(
                HttpStatus.BAD_REQUEST, BookServiceException.class.getName(), e.getMessage()));
  }

  @ExceptionHandler(TransactionSystemException.class)
  public ResponseEntity<ApiError> handleConstraintViolation(RuntimeException e) {
    log.error(INVALID_REQUEST, e);
    Throwable cause = ((TransactionSystemException) e).getRootCause();
    if (cause instanceof ConstraintViolationException) {
      List<String> constraintViolations =
          ((ConstraintViolationException) cause)
              .getConstraintViolations().stream()
                  .map(violation -> violation.getMessage() + ": " + violation.getInvalidValue())
                  .collect(Collectors.toList());
      return ResponseEntity.badRequest()
          .body(new ApiError(HttpStatus.BAD_REQUEST, INVALID_REQUEST, constraintViolations));
    }
    return handleRuntime(e);
  }

  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity<ApiError> handleRuntime(RuntimeException e) {
    log.error(INVALID_REQUEST, e);
    return ResponseEntity.badRequest()
        .body(new ApiError(HttpStatus.BAD_REQUEST, INVALID_REQUEST, e.getMessage()));
  }
}
