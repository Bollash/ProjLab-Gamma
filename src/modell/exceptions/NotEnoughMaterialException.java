package modell.exceptions;

public class NotEnoughMaterialException extends Exception{
    public NotEnoughMaterialException(String errorMessage){
        super(errorMessage);
    }
}
