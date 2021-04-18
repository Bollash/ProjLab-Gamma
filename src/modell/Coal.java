package modell;

public class Coal extends Material implements java.io.Serializable{
    /**
     * Ez a függvény visszaadja, hogy az adott material Coal
     * @return az adott material Coal típusú
     */
    @Override
    public MaterialType getType() {
        return MaterialType.Coal;
    }

    @Override
    public void status() {
        System.out.println("Coal");
    }
}
