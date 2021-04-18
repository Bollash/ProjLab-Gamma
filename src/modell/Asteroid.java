package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.CoreFullException;
import modell.exceptions.LayerNot0Exception;
import modell.exceptions.NoDrillableNeighbourException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.Serializable;

public class Asteroid implements IAsteroid, java.io.Serializable{

    private List<IAsteroid> neighbours;

    private List<Actor> actorsOnSurface;

    private int closeToSunFreq;

    private int layer;

    private int turnsTillCloseToSun;

    private Material coreMaterial;

    /**
     * constructor with default parameter
     */
    public Asteroid(){
        neighbours = new ArrayList<>();
        actorsOnSurface = new ArrayList<>();

        layer = 3;
        closeToSunFreq = 3;
        turnsTillCloseToSun = 3;
        coreMaterial = new Iron();
    }

    public Asteroid(int layer, int closeToSunFreq, int turnsTillCloseToSun, Material core){
        neighbours = new ArrayList<>();
        actorsOnSurface = new ArrayList<>();

        this.layer = layer;
        this.closeToSunFreq = closeToSunFreq;
        this.turnsTillCloseToSun = turnsTillCloseToSun;
        coreMaterial = core;
    }

    /**
     * A kéreg mérete 1-gyel csökken, ha pedig már 0 a méret, akkor nem történik semmi.
     */
    public void getDrilled(){
        if(layer != 0) {
            layer = layer-1;
            System.out.println("Az actor fúrt!");
            return;
        }
        System.out.println("Az actor nem tudott fúrni, mert a réteg 0!");
    }

    /**
     * Ha már át lett fúrva a kéreg, akkor kiveszi a materialt és a meghívó megkapja azt.
     * @return Magjában lévő material
     * @throws CantBeMinedException
     */
    public Material getMined()throws CantBeMinedException {
        if (layer == 0) {
            if(coreMaterial == null){
                throw new CantBeMinedException("No core material");
            }
                return coreMaterial;
            }
            else{
                throw new CantBeMinedException("Layer is not 0");
            }

    }

    /**
     * Ha a  paraméterként kapott objektum még nincs a szomszédok között, akkor bekerül oda.
     */
    public void addNeighbour(IAsteroid neighbour){
        if (!neighbours.contains(neighbour)){
            neighbours.add(neighbour);
        }
    }

    /**
     * Az aszteroidán lévő karakterek nyersanyagait összegyűjti és visszatér egy MaterialArray-el
     * amiben ezek vannak.
     */
    public MaterialArray countMaterialsOnSurface(){
        MaterialArray materialArray = new MaterialArray();
        for (Actor actor : actorsOnSurface) {
            materialArray = materialArray.plus(actor.getMaterials());
        }
        return materialArray;
    }


    /**
     * Ha az aszteroida magja üres, akkor a kapott nyersanyagot beteszi a magjába, egyébként kivételt dob.
     * @param material Ezt teszi be a magba
     * @throws CoreFullException Már van material a magban
     */
    public void addCore(Material material) throws CoreFullException, LayerNot0Exception {
        if(layer == 0){
            if( coreMaterial == null){
                coreMaterial = material;
            }
            else{
                throw new CoreFullException("Core is already full");
            }
        }
        else{
            throw new LayerNot0Exception("Layer is not 0");
        }

    }

    /**
     * Radioaktív robbanás következtében meghívja minden rajta tartózkodó radExplode() metódusát.
     */
    public void explode(){
        actorsOnSurface.removeIf(Actor::radExplode);
    }

    /**
     * Ha a kapott karakter szerepel az aszteroidán lévők listájában, akkor kiveszi onnan.
     * @param actor kivevendő karakter
     */
    public void removeActor(Actor actor){
        actorsOnSurface.remove(actor);
    }

    /**
     * Csökkenti a napközelségi számlálót. Ha a számláló 0 lesz és a kéreg is át van fúrva, akkor meghívja
     * a magban lévő nyersanyag closeToSunAction-jét, majd visszaállítja a számlálót.
     */
    public void handleCountDown(){
        turnsTillCloseToSun = turnsTillCloseToSun -1;
        if(turnsTillCloseToSun == 0)
            {
            System.out.println("Napközelbe került az aszteroida.");
            if(layer == 0){
                coreMaterial.closeToSunAction(this);
                //System.out.println("Napközelbe került az aszteroida.");
            }else{
                //System.out.println("Napközelbe került az aszteroida.");
            }
            turnsTillCloseToSun = closeToSunFreq;
        }else{
            System.out.println("A napközeli számláló eggyel csökkent.");
        }
    }

    /**
     * Ha a kapott karakter még nem szerepel az aszteroidán lévők listájában, akkor hozzáadja.
     * @param actor felvevendő karakter
     */
    @Override
    public void addActor(Actor actor) {
        if(!actorsOnSurface.contains(actor)){
            if(actor.currentAsteroid != null){
                actor.currentAsteroid.removeActor(actor);
            }
            actor.currentAsteroid = this;
            actorsOnSurface.add(actor);
        }
    }

    /**
     * Kapott asteroidát kivesszük a szomszédok közül
     * @param asteroid kivevendő aszteroida
     */
    @Override
    public void removeNeighbour(IAsteroid asteroid) {
        neighbours.remove(asteroid);
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Material getCoreMaterial() {
        return coreMaterial;
    }

    public void setCoreMaterial(Material coreMaterial) {
        this.coreMaterial = coreMaterial;
    }

    /**
     * Visszaad egy olyan IAsteroidát amire ha lépünk akkor fúrható aszteroidára kerülünk
     * @return Fúrható aszteroida
     * @throws NoDrillableNeighbourException nincs fúrható aszteroida
     */
    public IAsteroid getDrillableNeighbour()throws NoDrillableNeighbourException {
        for(IAsteroid ast: neighbours){
            if(ast.getLayer() != 0){
                return ast;
            }
        }
        throw new NoDrillableNeighbourException("");
    }


    public List<IAsteroid> getNeighbours() {
        return neighbours;
    }

    /**
     * napvihar söpör végig az aszteroidán. Minden actorra meghívódik a getSunStormed().
     */
    public void sunStorm(){
        // Minden actorra meghívjuk a getSunStormed-et. Ha ez true-val tér vissza akkor levesszük az aszteroidáról, mert meghalt. Eléggé érdekesen néz ki ez a megoldás, de csak így lehetett megcsinálni.
        actorsOnSurface.removeIf(Actor::getSunStormed);
    }

    public int getTurnsTillCloseToSun() {
        return turnsTillCloseToSun;
    }

    public void setTurnsTillCloseToSun(int c) { turnsTillCloseToSun = c;}

    public int getCloseToSunFreq() {
        return closeToSunFreq;
    }

    public int neighborCount() {
        return neighbours.size();
    }

    public int actorsOnSurfaceCount() {
        return actorsOnSurface.size();
    }

    public List<Actor> getActorsOnSurface() {
        return actorsOnSurface;
    }

    public void status(Space space){
        System.out.println("Asteroid");
        System.out.println(space.getAsteroids().indexOf(this));
        System.out.println(turnsTillCloseToSun);
        System.out.println(closeToSunFreq);
        System.out.println(layer);
        coreMaterial.status();
        System.out.println(neighborCount());
        for(IAsteroid iast : neighbours){
            if(iast instanceof TpGate) {
                System.out.println("tp " + space.getActors().indexOf(iast));
            }
            if(iast instanceof Asteroid) {
                System.out.println("ast " + space.getAsteroids().indexOf(iast));
            }
        }
        System.out.println(actorsOnSurface.size());
        List<Integer> lList = new ArrayList<>();
        for(Actor actor : actorsOnSurface){
            lList.add(space.getActors().indexOf(actor));
        }
        Collections.sort(lList);
        for(Integer n : lList){
            System.out.println(n);
        }
    }
}
