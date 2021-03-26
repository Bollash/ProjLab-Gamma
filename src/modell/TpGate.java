package modell;

import modell.exceptions.MoveFailedException;

/**
 * Ez az osztály írja le, hogy a modell.TpGate-et hogyan tudjuk használni
 */
public class TpGate implements IAsteroid {

    private TpGate linkedTpGate;
    private Asteroid onAsteroid;

    /**
     * modell.TpGate konstruktora
     */
    public void TpGate(){ }

    /**
     * Annak az aszteroidának a lekérdezése, amelyiken az adott modell.TpGate van
     * @return      Az az aszteroida, amelyiken az adott modell.TpGate van
     */
    public Asteroid getOnAsteroid() {
        return onAsteroid;
    }

    /**
     * Az adott modell.TpGate párjának lekérdezésa
     * @return      Az adott modell.TpGate párja
     */
    public TpGate getLinkedTpGate() {
        return linkedTpGate;
    }

    /**
     * Beállítja, hogy a modell.TpGate melyik aszteroidára lett lehelyezve
     * @param Asteroid       az aszteroida, amire tesszük a modell.TpGate-et
     */
    public void setOnAsteroid(Asteroid Asteroid) {
        onAsteroid = Asteroid;
    }

    /**
     * Beállítja a modell.TpGate párját
     * @param TpGate       a modell.TpGate-ünk párja
     */
    public void setLinkedTpGate(TpGate TpGate) {
        linkedTpGate = TpGate;
    }

    /**
     * Ha egy karakter átlép egy olyan aszteroidára, amelyiken a modell.TpGate párja van,
     * akkor levesszük az előző aszteroidáról, és áttesszük az újra
     * @param character       a karakter, aki használja a modell.TpGate-et
     */
    @Override
    public void addCharacter(Character character) throws MoveFailedException {
        if(linkedTpGate.getOnAsteroid() == null){
            throw new MoveFailedException("TpGate not on asteroid");
        }
        Asteroid ast = linkedTpGate.getOnAsteroid();
        ast.addCharacter(character);
        ast.removeCharacter(character);
    }

    /**
     * Ha egy egy olyan aszteroida felrobban, amelyiken a modell.TpGate párja van,
     * akkor levesszük kivesszük az adott aszteroida szomszédai közül
     * @param asteroid       a karakter, aki használja a modell.TpGate-et
     */
    @Override
    public void removeNeighbour(Asteroid asteroid) {
        if(getLinkedTpGate().getOnAsteroid() == asteroid)
            getLinkedTpGate().getOnAsteroid().removeNeighbour(asteroid);
    }

    @Override
    public int getLayer() {
        return linkedTpGate.getOnAsteroid().getLayer();
    }
}
