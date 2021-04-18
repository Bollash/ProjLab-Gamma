package modell;

import modell.exceptions.CantBeMinedException;

public class Ice extends Material implements java.io.Serializable{
    /**
     * Ez a függvény visszaadja, hogy az adott material Ice
     * @return az adott material Ice típusú
     */
    @Override
    public MaterialType getType() {
        return MaterialType.Ice;
    }

    @Override
    public void status() {
        System.out.println("Ice");
    }

    /**
     * Ha napközelben van és az aszteroida kérge ki van fúrva, akkor kikerül a magban lévő nyersanyag
     * a getMined() függvény segítségével.
     * @param ast az adott aszteroidán lévő Ice
     */
    @Override
    public void closeToSunAction(Asteroid ast) {

        try {
            ast.getMined();
            System.out.println("A magban lévő jég elpárolgott.");
        } catch (CantBeMinedException ignored) {
        }
    }
}
