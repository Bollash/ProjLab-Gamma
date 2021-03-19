package modell.exceptions;

public class MoveFailedException extends Exception{
    public MoveFailedException(String errorMessage){
        super(errorMessage);
    }
}
