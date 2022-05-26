package exceptions;

public class StringIsEmptyException extends RuntimeException {
        private static final String MESSAGE = "String may not be empty!";

        public StringIsEmptyException() {
            super(MESSAGE);
        }
}

