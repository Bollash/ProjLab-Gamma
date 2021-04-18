package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

/**
 * Ez az osztály írja le, hogy a modell.TpGate-et hogyan tudjuk használni
 */
public class TpGate extends Actor implements IAsteroid, java.io.Serializable {

    private TpGate linkedTpGate;
    private Settler inSettler;
    private boolean activated;

    /**
     * modell.TpGate konstruktora
     */
    public TpGate(){
        linkedTpGate = null;
        inSettler = null;
        activated = false;
    }

    /**
     * Az adott modell.TpGate párjának lekérdezésa
     * @return      Az adott modell.TpGate párja
     */
    public TpGate getLinkedTpGate() {
        return linkedTpGate;
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
     * @param actor       a karakter, aki használja a modell.TpGate-et
     */
    @Override
    public void addActor(Actor actor) throws MoveFailedException {
        if(linkedTpGate.currentAsteroid == null){
            throw new MoveFailedException("TpGate not on asteroid");
        }
        Asteroid ast = linkedTpGate.currentAsteroid;
        ast.addActor(actor);
        currentAsteroid.removeActor(actor);
    }

    @Override
    public void removeNeighbour(IAsteroid asteroid) {
    }

    @Override
    public int getLayer() {
        // Ha le van téve a tp gate akkor visszatér a párja layerével
        if(linkedTpGate.currentAsteroid != null){
            return linkedTpGate.currentAsteroid.getLayer();
        }
        // Ha nincs akkor -1 es visszatéréssel jelezzük, hogy ez nem valid uticél
        return -1;
    }

    @Override
    public boolean radExplode() {
        linkedTpGate.explode();
        return true;
    }

    public void explode(){
        currentAsteroid.removeNeighbour(this);
        currentAsteroid.removeActor(this);
        space.removeActor(this);
    }

    @Override
    public boolean getSunStormed() {
        // Aktiváljuk
        if(!activated) activated = true;
        // A tpgate nem hal meg sose napviharban
        return false;
    }

    @Override
    public void act(){
        if(activated){
            try {
                //Ha van legalább egy szomszéd akkor mozog
                if(currentAsteroid.getNeighbours().size() != 0){
                    for(int i = 0; i < currentAsteroid.getNeighbours().size(); i++){
                        // ellenőrizzük, hogy magára ne lépjen hanem az első lehetőségre
                        if(currentAsteroid.getNeighbours().get(i) != this){
                            move(currentAsteroid.getNeighbours().get(i));
                            System.out.println("Az actor egy szomszédos aszteroidára mozog.");
                            break;
                        }
                    }
                }else{
                    System.out.println("Az actor nem tud szomszédos aszteroidára mozogni.");
                }

            } catch (MoveFailedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("A TpGate nem csinál semmit.");
        }
    }

    @Override
    public void putMaterialBack(Material mat){}

    @Override
    public void status() {
        System.out.println("TpGate");
        System.out.println(space.getActors().indexOf(this));
        if(currentAsteroid != null){
            System.out.println("ast " + space.getAsteroids().indexOf(currentAsteroid));
        }else if(inSettler != null){
            System.out.println("set " + space.getActors().indexOf(inSettler));
        }
        System.out.println(space.getActors().indexOf(linkedTpGate));
        if(activated){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }

    public Settler getInSettler() {
        return inSettler;
    }

    public void setInSettler(Settler inSettler) {
        this.inSettler = inSettler;
    }

    public void setActivated(boolean b){
        activated = b;
    }
}
