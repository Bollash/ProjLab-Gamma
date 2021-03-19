package modell.exceptions;

public class CantBeMinedException extends Exception{
    public CantBeMinedException(String errorMessage){
        super(errorMessage);
    }
}
