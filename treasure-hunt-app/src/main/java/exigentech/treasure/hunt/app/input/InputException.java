package exigentech.treasure.hunt.app.input;

final class InputException extends RuntimeException {

  InputException(String message) {
    super(message);
  }

  InputException(String message, Throwable cause) {
    super(message, cause);
  }
}
