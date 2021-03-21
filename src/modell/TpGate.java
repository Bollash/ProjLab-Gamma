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
        System.out.println("Belépett a getOnAsteroid-ba");
        System.out.println("Kilépett a getOnAsteroid-ból");
        return onAsteroid;
    }

    /**
     * Az adott modell.TpGate párjának lekérdezésa
     * @return      Az adott modell.TpGate párja
     */
    public TpGate getLinkedTpGate() {
        System.out.println("Belépett a getLinkedTpGate-ba");
        System.out.println("Kilépett a getLinkedTpGate-ből");
        return linkedTpGate;
    }

    /**
     * Beállítja, hogy a modell.TpGate melyik aszteroidára lett lehelyezve
     * @param Asteroid       az aszteroida, amire tesszük a modell.TpGate-et
     */
    public void setOnAsteroid(Asteroid Asteroid) {
        System.out.println("Belépett a setOnAsteroid-ba");
        System.out.println("Kilépett a setOnAsteroid-ból");
        onAsteroid = Asteroid;
    }

    /**
     * Beállítja a modell.TpGate párját
     * @param TpGate       a modell.TpGate-ünk párja
     */
    public void setLinkedTpGate(TpGate TpGate) {
        System.out.println("Belépett a setLinkedTpGate-ba");
        System.out.println("Kilépett a setLinkedTpGate-ből");
        linkedTpGate = TpGate;
    }

    /**
     * Ha egy karakter átlép egy olyan aszteroidára, amelyiken a modell.TpGate párja van,
     * akkor levesszük az előző aszteroidáról, és áttesszük az újra
     * @param character       a karakter, aki használja a modell.TpGate-et
     */
    @Override
    public void addCharacter(Character character) throws MoveFailedException {
        System.out.println("Belépett az addCharacter-ba");
        if(linkedTpGate.getOnAsteroid() == null){
            throw new MoveFailedException("TpGate not on asteroid");
        }
        linkedTpGate.getOnAsteroid().addCharacter(character);
        linkedTpGate.getOnAsteroid().removeCharacter(character);
        System.out.println("Kilépett az addCharacter-ból");
    }

    /**
     * Ha egy egy olyan aszteroida felrobban, amelyiken a modell.TpGate párja van,
     * akkor levesszük kivesszük az adott aszteroida szomszédai közül
     * @param asteroid       a karakter, aki használja a modell.TpGate-et
     */
    @Override
    public void removeNeighbour(Asteroid asteroid) {
        System.out.println("Belépett a removeNeighbor-be");
        if(getLinkedTpGate().getOnAsteroid() == asteroid)
            getLinkedTpGate().getOnAsteroid().removeNeighbour(asteroid);
        System.out.println("Kilépett a removeNeihbor-ből");
    }

    @Override
    public int getLayer() {
        System.out.println("Belépett a getLayer-be");
        System.out.println("Kilépett a getLayer-ből");
        return linkedTpGate.getOnAsteroid().getLayer();
    }
}
