package modell.exceptions;

public class NoDrillableNeighbourException extends Exception{
    public NoDrillableNeighbourException(String errorMessage){
        super(errorMessage);
    }
}
