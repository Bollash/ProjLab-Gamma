package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.CoreFullException;
import modell.exceptions.NotEnoughMaterialException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settler extends Actor implements iDrill, iMine, java.io.Serializable{
    /**
     * Telepes nyersanyagai
     */
    private MaterialArray materials;

    /**
     * Telepes teleportkapui. Nullától kettőig terjedhet a márete.
     */
    private List<TpGate> tpGates;

    public Settler(){
        materials = new MaterialArray();
        tpGates = new ArrayList<>();
    }

    /**
     * Kibányássza az asteroida magját, és beteszi a nyersanyagok közé, ha ott még 10 nél kevesebb darab van.
     * @throws CantBeMinedException Ha az aszteroidát nem lehet bányászni ilyen exceptiont dobunk.
     */
    public void mine() throws CantBeMinedException {
        Material material;
        material = currentAsteroid.getMined();
        if(materials.getMaterials().size() < 10){
            materials.addMaterial(material);
        }
    }

    /**
     * Radioaktív robbbanás következtében felrobban és meghal a telepes.
     */
    @Override
    public boolean radExplode() {
        space.removeActor(this);
        int value = space.getAliveSettlerCnt() - 1;
        space.setAliveSettlerCnt(value);
        if(value <= 1) space.setGameOver(true);
        return true;
    }

    @Override
    public boolean getSunStormed(){
        if(currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null){
            space.removeActor(this);
            int value = space.getAliveSettlerCnt() - 1;
            space.setAliveSettlerCnt(value);
            if(value <= 1) space.setGameOver(true);
        }
        return false;
    }

    //Todo
    @Override
    public void act() {
        System.out.println("Az actor telepes.");
    }

    /**
     * Felépíti a bázist, ha van elég nyersanyag
     */
    public void buildBase(){
        if(currentAsteroid.countMaterialsOnSurface().canBuildBase()){
            space.setGameOver(true);
        }
    }

    /**
     * gyárt egy kapupárt ha van nyersanyag, és nála nincs 1 darab kapu se
     */
    public void craftGates(){
        if(tpGates.size() <= 1){
            TpGate[] gates;
            try {
                gates = materials.buildGates();
                gates[0].setLinkedTpGate(gates[1]);
                gates[0].setInSettler(this);
                gates[1].setLinkedTpGate(gates[0]);
                gates[1].setInSettler(this);
                tpGates.addAll(Arrays.asList(gates));
            } catch (NotEnoughMaterialException e) {
                System.out.println("Not enough material to craft");
            }

        }
    }

    /**
     * Ha van elég nyersanyaga akkor gyárt egy robotot amit letesz a currenAsteroid-ra. Majd betesz azt a space-be
     */
    public void craftRobot(){
        try{
            Robot r = materials.buildRobot();
            space.addActor(r);
            currentAsteroid.addActor(r);
        }catch(NotEnoughMaterialException e){
            System.out.println("Not enough material to craft");
        }
    }

    /**
     * Visszateszi a paraméterként kapott nyersanyagot a currentAsteroid-ba
     * @param mat A nyersanyag amit vissza kell tenni
     */
    public void putMaterialBack(Material mat){
        if(materials.getMaterials().contains(mat)){
            try {
                currentAsteroid.addCore(mat);
                materials.getMaterials().remove(mat);
            } catch (CoreFullException e) {
                System.out.println("The asteroid core is not empty");
            }
        }
    }

    /**
     * Leteszi a currentAsteroidára az egyik TpGate-t
     */
    public void putTpGateDown(){
        if(tpGates.size() > 0){
            currentAsteroid.addActor(tpGates.get(0));
            currentAsteroid.addNeighbour(tpGates.get(0));
            tpGates.remove(0);
        }
    }

    public MaterialArray getMaterials() {
        return materials;
    }

    public void setMaterialArray(MaterialArray arr) {
        materials = arr;
    }

    public void addTpGate(TpGate tpg1) {
        tpGates.add(tpg1);
    }

    @Override
    public void drill() {
        currentAsteroid.getDrilled();
    }
}
