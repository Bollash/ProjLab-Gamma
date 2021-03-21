package modell;

import modell.exceptions.CantBeMinedException;

public class Ice extends Material{
    /**
     * Ez a függvény visszaadja, hogy az adott material Ice
     * @return az adott material Ice típusú
     */
    @Override
    public MaterialType getType() {
        System.out.println("Belépett a getType-ba");
        System.out.println("Kilépett a getType-ból");
        return MaterialType.Ice;
    }

    @Override
    public void closeToSunAction(Asteroid ast) {
        System.out.println("Belépett a closeToSunAction-be");

        try {
            ast.getMined();
        } catch (CantBeMinedException ignored) {
        }
        System.out.println("Kilépett a closeToSunAction-ból");
    }
}
