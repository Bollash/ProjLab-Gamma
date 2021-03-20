package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.CoreFullException;

import java.util.ArrayList;
import java.util.List;

public class Asteroid implements IAsteroid {

    private List<IAsteroid> neighbours;

    private List<Character> charactersOnSurface;

    private int closeToSunFreq;

    private int layer;

    private int turnsTillCloseToSun;

    private Material coreMaterial;

    public Asteroid(int layer, int closeToSunFreq, int turnsTillCloseToSun, Material core){
        neighbours = new ArrayList<>();
        charactersOnSurface = new ArrayList<>();

        this.layer = layer;
        this.closeToSunFreq = closeToSunFreq;
        this.turnsTillCloseToSun = turnsTillCloseToSun;
        coreMaterial = core;
    }

    /**
     * A kéreg mérete 1-gyel csökken, ha pedig már 0 a méret, akkor nem történik semmi.
     */
    public void getDrilled(){
        if(layer == 0){
            return;
        }
        else {
            layer = layer-1;
        }

    }
    /**
    * Ha már át lett fúrva a kéreg, akkor kiveszi a materialt és a meghívó megkapja azt.
     */
    public Material getMined()throws CantBeMinedException {
            if (layer == 0) {
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
    //TODO
    public MaterialArray countMaterialOnSurface(){
        MaterialArray materialArray = new MaterialArray();
        for (Character character : charactersOnSurface) {


        }
        return materialArray;
    }

    /**
     * Ha az aszteroida magja üres, akkor a kapott nyersanyagot beteszi a magjába, egyébként kivételt dob.
     */
    public void addCore(Material material)throws CoreFullException {
        if( coreMaterial == null){
            coreMaterial = material;
        }
        else{
            throw new CoreFullException("Core is already full");
        }
    }

    /**
     * Radioaktív robbanás következtében meghívja minden rajta tartózkodó radExplode() metódusát.
     */
    public void explode(){
        for (Character character : charactersOnSurface) {
            character.radExplode();
        }
    }

    /**
     * Ha a kapott karakter szerepel az aszteroidán lévők listájában, akkor kiveszi onnan.
     */
    public void removeCharacter(Character character){
        if (charactersOnSurface.contains(character)){
            charactersOnSurface.remove(character);
        }
    }

    /**
     * Csökkenti a napközelségi számlálót. Ha a számláló 0 lesz és a kéreg is át van fúrva, akkor meghívja
     * a magban lévő nyersanyag closeToSunAction-jét, majd visszaállítja a számlálót.
     */
    public void handleCountDown(){
        turnsTillCloseToSun = turnsTillCloseToSun -1;
        if(turnsTillCloseToSun ==0){
            if(layer ==0){
                coreMaterial.closeToSunAction(this);

            }
            turnsTillCloseToSun = closeToSunFreq;
        }
    }

    /**
     * Ha a kapott karakter még nem szerepel az aszteroidán lévők listájában, akkor hozzáadja.
     */
    @Override
    public void addCharacter(Character character) {
        if(!charactersOnSurface.contains(character)){
            charactersOnSurface.add(character);
        }
    }

    /**
     * A paraméterként kapott objektum eltávolítása a szomszédokból.
     */
    @Override
    public void removeNeighbour(Asteroid asteroid) {
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
    //TODO
    public Asteroid getDrillableNeighbour(){}


    public List<IAsteroid> getNeighbours() {
        return neighbours;
    }
}
