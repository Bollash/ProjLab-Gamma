package modell;

public class Iron extends Material{
    /**
     * Ez a függvény visszaadja, hogy az adott material Iron
     * @return az adott material Iron típusú
     */
    @Override
    public MaterialType getType() {
        System.out.println("Belépett a getType-ba");
        System.out.println("Kilépett a getType-ból");
        return MaterialType.Iron;
    }
}
