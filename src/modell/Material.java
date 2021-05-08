package modell;

import modell.exceptions.ExplodeException;

public abstract class Material implements java.io.Serializable{
    public void closeToSunAction(Asteroid ast) throws ExplodeException {}

    public abstract MaterialType getType();

    public abstract void status();

    public int getCounter(){return 0;}

    public void setCounter(int newValue){return;}
}
