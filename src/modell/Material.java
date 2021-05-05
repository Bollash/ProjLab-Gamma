package modell;

public abstract class Material implements java.io.Serializable{
    public void closeToSunAction(Asteroid ast){}

    public abstract MaterialType getType();

    public abstract void status();

    public int getCounter(){return 0;}
}
