package modell;

import modell.exceptions.CantBeMinedException;

public class Ice extends Material{
    @Override
    public MaterialType getType() {
        return MaterialType.Ice;
    }

    @Override
    public void closeToSunAction(Asteroid ast) {
        try {
            ast.getMined();
        } catch (CantBeMinedException ignored) {
        }
    }
}
