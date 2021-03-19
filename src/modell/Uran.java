package modell;

public class Uran extends Material{
    @Override
    public MaterialType getType() {
        return MaterialType.Uran;
    }

    @Override
    public void closeToSunAction(Asteroid ast) {
        ast.explode();
    }
}
