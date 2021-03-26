package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.CoreFullException;
import modell.exceptions.NotEnoughMaterialException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settler extends Character{
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
     */
    public void mine(){
        Material material;
        try {
            material = currentAsteroid.getMined();
            if(materials.getMaterials().size() < 10){
                materials.addMaterial(material);
            }
        } catch (CantBeMinedException e) {
        }
    }

    /**
     * Radioaktív robbbanás következtében felrobban és meghal a telepes.
     */
    @Override
    public boolean radExplode() {
        space.removeCharacter(this);
        int value = space.getAliveSettlerCnt() - 1;
        space.setAliveSettlerCnt(value);
        if(value <= 1) space.setGameOver(true);
        return true;
    }

    @Override
    public boolean getSunStormed(){
        if(currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null){
            currentAsteroid.removeCharacter(this);
            int value = space.getAliveSettlerCnt() - 1;
            space.setAliveSettlerCnt(value);
            if(value <= 1) space.setGameOver(true);
        }
        return false;
    }

    //Todo
    @Override
    public void act() {
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
        if(tpGates.size() == 0){
            TpGate[] gates;
            try {
                gates = materials.buildGates();
                gates[0].setLinkedTpGate(gates[1]);
                gates[1].setLinkedTpGate(gates[0]);
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
            space.addCharacter(r);
            currentAsteroid.addCharacter(r);
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
            tpGates.get(0).setOnAsteroid(currentAsteroid);
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
}
