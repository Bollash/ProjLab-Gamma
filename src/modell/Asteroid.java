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
        System.out.println("Belépett a getDrilled-be");
        if(layer != 0) {
            layer = layer-1;
        }
        System.out.println("Kilépett a getDrilled-ből");
    }

    /**
     * Ha már át lett fúrva a kéreg, akkor kiveszi a materialt és a meghívó megkapja azt.
     * @return Magjában lévő material
     * @throws CantBeMinedException
     */
    public Material getMined()throws CantBeMinedException {
        System.out.println("Belépett a getMined-be");
        if (layer == 0) {
            if(coreMaterial == null){
                throw new CantBeMinedException("No core material");
            }
                System.out.println("kilépett a getMined-ból");
                return coreMaterial;
            }
            else{
            System.out.println("CantBeMinedException exception-t dobott");
                throw new CantBeMinedException("Layer is not 0");
            }

    }

    /**
     * Ha a  paraméterként kapott objektum még nincs a szomszédok között, akkor bekerül oda.
     */
    public void addNeighbour(IAsteroid neighbour){
        System.out.println("Belépett az addNeighbour-ba");
        if (!neighbours.contains(neighbour)){
            neighbours.add(neighbour);
        }
        System.out.println("Kilépett az addNeighbour-ből");
    }

    /**
     * Az aszteroidán lévő karakterek nyersanyagait összegyűjti és visszatér egy MaterialArray-el
     * amiben ezek vannak.
     */
    public MaterialArray countMaterialsOnSurface(){
        System.out.println("Belépett a countMaterialOnSurface-be");
        MaterialArray materialArray = new MaterialArray();
        for (Character character : charactersOnSurface) {
            materialArray = materialArray.plus(character.getMaterials());
        }
        System.out.println("Kilépett a countMaterialOnSurface-ből");
        return materialArray;
    }


    /**
     * Ha az aszteroida magja üres, akkor a kapott nyersanyagot beteszi a magjába, egyébként kivételt dob.
     * @param material Ezt teszi be a magba
     * @throws CoreFullException Már van material a magban
     */
    public void addCore(Material material)throws CoreFullException {
        System.out.println("Belépett az addCore-ba");
        if( coreMaterial == null){
            coreMaterial = material;
            System.out.println("Kilépett az addCore-ből");
        }
        else{
            throw new CoreFullException("Core is already full");
        }
    }

    /**
     * Radioaktív robbanás következtében meghívja minden rajta tartózkodó radExplode() metódusát.
     */
    public void explode(){
        System.out.println("Belépett a explode-ba");
        for (Character character : charactersOnSurface) {
            character.radExplode();
        }
        System.out.println("Kilépett az explode-ból");
    }

    /**
     * Ha a kapott karakter szerepel az aszteroidán lévők listájában, akkor kiveszi onnan.
     * @param character kivevendő karakter
     */
    public void removeCharacter(Character character){
        System.out.println("Belépett a removeCharacter-be");
        charactersOnSurface.remove(character);
        System.out.println("Kilépett a removeCharacter-ből");
    }

    /**
     * Csökkenti a napközelségi számlálót. Ha a számláló 0 lesz és a kéreg is át van fúrva, akkor meghívja
     * a magban lévő nyersanyag closeToSunAction-jét, majd visszaállítja a számlálót.
     */
    public void handleCountDown(){
        System.out.println("Belépett a handleCountDown-ba");
        turnsTillCloseToSun = turnsTillCloseToSun -1;
        if(turnsTillCloseToSun ==0){
            if(layer ==0){
                coreMaterial.closeToSunAction(this);

            }
            turnsTillCloseToSun = closeToSunFreq;
        }
        System.out.println("Kilépett a handleCountDown-ból");
    }

    /**
     * Ha a kapott karakter még nem szerepel az aszteroidán lévők listájában, akkor hozzáadja.
     * @param character felvevendő karakter
     */
    @Override
    public void addCharacter(Character character) {
        System.out.println("Belépett az addCharacter-be");
        if(!charactersOnSurface.contains(character)){
            charactersOnSurface.add(character);
        }
        System.out.println("Kilépett az addCharacter-ből");
    }

    /**
     * Kapott asteroidát kivesszük a szomszédok közül
     * @param asteroid kivevendő aszteroida
     */
    @Override
    public void removeNeighbour(Asteroid asteroid) {
        System.out.println("Belépett a removeNeighbour-ba");
        neighbours.remove(asteroid);
        System.out.println("Kilépett a removeNeighbour-ból");
    }

    public int getLayer() {
        System.out.println("Belépett a getLayer-be");
        System.out.println("Kilépett a getLayer-ből");
        return layer;

    }

    public void setLayer(int layer) {
        System.out.println("Belépett a setLayer-be");
        System.out.println("Kilépett a setLayer-ből");
        this.layer = layer;
    }

    public Material getCoreMaterial() {
        System.out.println("Belépett a getCoreMaterial-ba");
        System.out.println("Kilépett a getCoreMaterial-ból");
        return coreMaterial;
    }

    public void setCoreMaterial(Material coreMaterial) {
        System.out.println("Belépett a setCoreMaterial-ba");
        System.out.println("Kilépett a setCoreMaterial-ból");
        this.coreMaterial = coreMaterial;
    }

    /**
     * Visszaad egy olyan IAsteroidát amire ha lépünk akkor fúrható aszteroidára kerülünk
     * @return Fúrható aszteroida
     * @throws NoDrillableNeighbourException nincs fúrható aszteroida
     */
    public IAsteroid getDrillableNeighbour()throws NoDrillableNeighbourException {
        System.out.println("Belépett a getDrillableNeighbour-be");
        for(IAsteroid ast: neighbours){
            if(ast.getLayer() != 0){
                System.out.println("Kilépett a getDrillableNeighbour-ból");
                return ast;
            }
        }
        System.out.println("NoDrillableNeighbourException exception-t dobott");
        throw new NoDrillableNeighbourException("");
    }


    public List<IAsteroid> getNeighbours() {
        System.out.println("Belépett a getNeighbour-ba");
        System.out.println("Kilépett a getNeighbour-ből");
        return neighbours;
    }
}
