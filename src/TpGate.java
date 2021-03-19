/**
 * Ez az osztály írja le, hogy a TpGate-et hogyan tudjuk használni
 */
public class TpGate implements IAsteroid {

    private TpGate linkedTpGate;
    private Asteroid onAsteroid;

    /**
     * TpGate konstruktora
     */
    public void TpGate(){

    }

    /**
     * Annak az aszteroidának a lekérdezése, amelyiken az adott TpGate van
     * @return      Az az aszteroida, amelyiken az adott TpGate van
     */
    public Asteroid getOnAsteroid() {
        return onAsteroid;
    }

    /**
     * Az adott TpGate párjának lekérdezésa
     * @return      Az adott TpGate párja
     */
    public TpGate getLinkedTpGate() {
        return linkedTpGate;
    }

    /**
     * Beállítja, hogy a TpGate melyik aszteroidára lett lehelyezve
     * @param Asteroid       az aszteroida, amire tesszük a TpGate-et
     */
    public void setOnAsteroid(Asteroid Asteroid) {
        onAsteroid = Asteroid;
    }

    /**
     * Beállítja a TpGate párját
     * @param TpGate       a TpGate-ünk párja
     */
    public void setLinkedTpGate(TpGate TpGate) {
        linkedTpGate = TpGate;
    }

    /**
     * Ha egy karakter átlép egy olyan aszteroidára, amelyiken a TpGate párja van,
     * akkor levesszük az előző aszteroidáról, és áttesszük az újra
     * @param character       a karakter, aki használja a TpGate-et
     */
    @Override
    public void addCharacter(Character character) {
        getLinkedTpGate().getOnAsteroid().addCharacter(character);
        getLinkedTpGate().getOnAsteroid().removeCharacter(character);
    }

    /**
     * Ha egy egy olyan aszteroida felrobban, amelyiken a TpGate párja van,
     * akkor levesszük kivesszük az adott aszteroida szomszédai közül
     * @param asteroid       a karakter, aki használja a TpGate-et
     */
    @Override
    public void removeNeighbour(Asteroid asteroid) {
        if(getLinkedTpGate().getOnAsteroid() == asteroid)
            getLinkedTpGate().getOnAsteroid().removeNeighbour(asteroid);
    }
}
