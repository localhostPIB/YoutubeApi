package exceptions;

public class InvalidVideoIdException extends RuntimeException{
    private static final String MESSAGE = "Invalid Video-Id!";

    public InvalidVideoIdException(){
        super(MESSAGE);
    }
}
