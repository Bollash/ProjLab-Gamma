package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.CoreFullException;
import modell.exceptions.NoDrillableNeighbourException;

import java.util.ArrayList;
import java.util.List;

public class Asteroid implements IAsteroid {

    private List<IAsteroid> neighbours;

    private List<Character> charactersOnSurface;

    private int closeToSunFreq;

    private int layer;

    private int turnsTillCloseToSun;

    private Material coreMaterial;

    /**
     * constructor with default parameter
     */
    public Asteroid(){
        neighbours = new ArrayList<>();
        charactersOnSurface = new ArrayList<>();

        layer = 3;
        closeToSunFreq = 3;
        turnsTillCloseToSun = 3;
        coreMaterial = new Iron();
    }

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
        if(layer != 0) {
            layer = layer-1;
        }
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
        for (Character character : charactersOnSurface) {
            materialArray = materialArray.plus(character.getMaterials());
        }
        return materialArray;
    }


    /**
     * Ha az aszteroida magja üres, akkor a kapott nyersanyagot beteszi a magjába, egyébként kivételt dob.
     * @param material Ezt teszi be a magba
     * @throws CoreFullException Már van material a magban
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
        charactersOnSurface.removeIf(Character::radExplode);
    }

    /**
     * Ha a kapott karakter szerepel az aszteroidán lévők listájában, akkor kiveszi onnan.
     * @param character kivevendő karakter
     */
    public void removeCharacter(Character character){
        charactersOnSurface.remove(character);
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
     * @param character felvevendő karakter
     */
    @Override
    public void addCharacter(Character character) {
        if(!charactersOnSurface.contains(character)){
            charactersOnSurface.add(character);
        }
    }

    /**
     * Kapott asteroidát kivesszük a szomszédok közül
     * @param asteroid kivevendő aszteroida
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
}
