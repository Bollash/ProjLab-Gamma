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
            System.out.println("modell.Asteroid cant be mined");
        }

    }

    /**
     * Radioaktív robbbanás következtében felrobban és meghal a telepes.
     */
    @Override
    public void radExplode() {
        die();
    }

    @Override
    public void act() {

    }


    @Override
    public void die() {
        space.removeCharacter(this);
        space.setAliveSettlerCnt(space.getAliveSettlerCnt() - 1);
    }

    public void buildBase(){
        if(currentAsteroid.countMaterialsOnSurface().canBuildBase()){
            space.setGameOver(true);
        }
    }

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

    public void craftRobots(){
        try{
            Robot r = materials.buildRobot();
            space.addCharacter(r);
            currentAsteroid.addCharacter(r);
        }catch(NotEnoughMaterialException e){
            System.out.println("Not enough material to craft");
        }
    }

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
}
