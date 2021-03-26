package modell;

public class Uran extends Material{
    /**
     * Ez a függvény visszaadja, hogy az adott material Uran
     * @return az adott material Uran típusú
     */
    @Override
    public MaterialType getType() {
        return MaterialType.Uran;
    }

    /**
     * Radioaktív robbanás következik be és meghívja az aszteroida explode metódusát.
     * @param ast az adott aszteroida, amiben az Uran van.
     */
    @Override
    public void closeToSunAction(Asteroid ast) {
        ast.explode();
    }
}
