package modell;

public class Uran extends Material{
    /**
     * Ez a függvény visszaadja, hogy az adott material Uran
     * @return az adott material Uran típusú
     */
    @Override
    public MaterialType getType() {
        System.out.println("Belépett a getType-ba");
        System.out.println("Kilépett a getType-ból");
        return MaterialType.Uran;
    }

    @Override
    public void closeToSunAction(Asteroid ast) {
        System.out.println("Belépett a closeToSunAction-be");
        ast.explode();
        System.out.println("Kilépett a closeToSunAction-ból");
    }
}
