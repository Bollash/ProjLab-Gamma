package modell;

public class Iron extends Material implements java.io.Serializable{
    /**
     * Ez a függvény visszaadja, hogy az adott material Iron
     * @return az adott material Iron típusú
     */
    @Override
    public MaterialType getType() {
        return MaterialType.Iron;
    }

    @Override
    public void status() {
        System.out.println("Iron");
    }
}
