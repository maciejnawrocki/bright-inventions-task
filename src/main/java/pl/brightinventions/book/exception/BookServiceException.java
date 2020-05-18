package pl.brightinventions.book.exception;

public class BookServiceException extends RuntimeException {

  public BookServiceException() {
    super();
  }

  public BookServiceException(String message) {
    super(message);
  }

  public BookServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
