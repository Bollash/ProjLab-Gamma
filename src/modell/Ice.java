package modell;

public class Ice extends Material{
    @Override
    public MaterialType getType() {
        return MaterialType.Ice;
    }

    @Override
    public void closeToSunAction(Asteroid ast) {
        ast.getMined();
    }
}
