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
        System.out.println("Belépett a mine-ba");
        Material material;
        try {
            material = currentAsteroid.getMined();
            if(materials.getMaterials().size() < 10){
                materials.addMaterial(material);
            }
        } catch (CantBeMinedException e) {
            System.out.println("modell.Asteroid cant be mined");
        }
        System.out.println("Kilépett a mine-ból");
    }

    /**
     * Radioaktív robbbanás következtében felrobban és meghal a telepes.
     */
    @Override
    public boolean radExplode() {
        System.out.println("Belépett a radExplode-ba");
        space.removeCharacter(this);
        int value = space.getAliveSettlerCnt() - 1;
        space.setAliveSettlerCnt(value);
        if(value <= 1) space.setGameOver(true);
        System.out.println("Kilépett a radexplode-ból");
        return true;
    }

    @Override
    public boolean getSunStormed(){
        System.out.println("Belépett a getSunStormed-ba");
        System.out.println("Kilépett a getSunStromed-ból");
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
        System.out.println("Belépett a buildBase-be");
        if(currentAsteroid.countMaterialsOnSurface().canBuildBase()){
            space.setGameOver(true);
        }
        System.out.println("Kilépett a buildBase-ből");
    }

    /**
     * gyárt egy kapupárt ha van nyersanyag, és nála nincs 1 darab kapu se
     */
    public void craftGates(){
        System.out.println("Belépett a craftGates-be");
        if(tpGates.size() == 0){
            TpGate[] gates;
            try {
                gates = materials.buildGates();
                gates[0].setLinkedTpGate(gates[1]);
                gates[1].setLinkedTpGate(gates[0]);
                tpGates.addAll(Arrays.asList(gates));
                System.out.println("Kilépett a craftGates-ből");
            } catch (NotEnoughMaterialException e) {
                System.out.println("Not enough material to craft");
            }

        }
    }

    /**
     * Ha van elég nyersanyaga akkor gyárt egy robotot amit letesz a currenAsteroid-ra. Majd betesz azt a space-be
     */
    public void craftRobot(){
        System.out.println("Belépett a craftRobot-ba");
        try{
            Robot r = materials.buildRobot();
            space.addCharacter(r);
            currentAsteroid.addCharacter(r);
            System.out.println("Kilépett a craftRobot-ból");
        }catch(NotEnoughMaterialException e){
            System.out.println("Not enough material to craft");
        }
    }

    /**
     * Visszateszi a paraméterként kapott nyersanyagot a currentAsteroid-ba
     * @param mat A nyersanyag amit vissza kell tenni
     */
    public void putMaterialBack(Material mat){
        System.out.println("Belépett a putMaterialBack-be");
        if(materials.getMaterials().contains(mat)){
            try {
                currentAsteroid.addCore(mat);
                materials.getMaterials().remove(mat);
                System.out.println("Kilépett a putMaterialBack-ből");
            } catch (CoreFullException e) {
                System.out.println("The asteroid core is not empty");
            }
        }
    }

    /**
     * Leteszi a currentAsteroidára az egyik TpGate-t
     */
    public void putTpGateDown(){
        System.out.println("Belépett a putTpGateDown-ba");
        if(tpGates.size() > 0){
            tpGates.get(0).setOnAsteroid(currentAsteroid);
            currentAsteroid.addNeighbour(tpGates.get(0));
            tpGates.remove(0);
        }
        System.out.println("Kilépett a putTpGateDown-ból");
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
