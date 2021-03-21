package modell;

public class Coal extends Material{
    /**
     * Ez a függvény visszaadja, hogy az adott material Coal
     * @return az adott material Coal típusú
     */
    @Override
    public MaterialType getType() {
        System.out.println("Belépett a getType-ba");
        System.out.println("Kilépett a getType-ból");
        return MaterialType.Coal;
    }
}
