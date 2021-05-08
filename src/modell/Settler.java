package modell;

import modell.exceptions.*;

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
     */
    public void mine(){
        Material material;
        try {
            material = currentAsteroid.getMined();

            System.out.println("Telepes bányászott.");
            if(materials.getMaterials().size() < 10){
                materials.addMaterial(material);
                System.out.println("A nyersanyag el lett tárolva");
            }else{
                System.out.println("A nyersanyagnak nincs hely ezért elpusztult.");
            }
        } catch (CantBeMinedException | LayerNot0Exception e) {
            System.out.println("Cant be mined");
        }
        space.incrementCurrentActor();
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
            return true;
        }
        return false;
    }

    //Todo
    @Override
    public void act() throws SettlerActingException {
        throw new SettlerActingException("Settler acts");
    }

    /**
     * Felépíti a bázist, ha van elég nyersanyag
     */
    public void buildBase(){
        if(currentAsteroid.countMaterialsOnSurface().canBuildBase()){
            space.incrementCurrentActor();
            space.setGameOver(true);
            System.out.println("Felépült a bázis. Nyertek a telepesek");
        }else{
            System.out.println("Nem sikerült a bázis építés, mert nincs elég nyersanyag az aszteroidán.");
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
                space.incrementCurrentActor();
                gates[0].setLinkedTpGate(gates[1]);
                gates[0].setInSettler(this);
                gates[1].setLinkedTpGate(gates[0]);
                gates[1].setInSettler(this);
                tpGates.addAll(Arrays.asList(gates));
                space.addActor(gates[0]);
                space.addActor(gates[1]);
                gates[0].setSpace(space);
                gates[1].setSpace(space);
                System.out.println("A telepes teleportkapukat épített.");
            } catch (NotEnoughMaterialException e) {
                System.out.println("Nem sikerült a teleportkapu építés, mert nincs elég nyersanyag.");
            }

        }else{
            System.out.println("Nem sikerült a teleportkapu építés, mert nincs hely.");
        }
    }

    /**
     * Ha van elég nyersanyaga akkor gyárt egy robotot amit letesz a currenAsteroid-ra. Majd betesz azt a space-be
     */
    public void craftRobot(){
        try{
            Robot r = materials.buildRobot();
            space.incrementCurrentActor();
            space.addActor(r);
            currentAsteroid.addActor(r);
            r.setCurrentAsteroid(currentAsteroid);
            r.setSpace(space);
            System.out.println("A telepes robotot épített.");
        }catch(NotEnoughMaterialException e){
            System.out.println("Nem sikerült a robot építés, mert nincs elég nyersanyag.");
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
                space.incrementCurrentActor();
                System.out.println("Telepes visszatette a nyersanyagot.");
                materials.getMaterials().remove(mat);
            } catch (CoreFullException e) {
                System.out.println("Nem sikerült a visszatétel, mert a mag nem volt üres.");
            } catch (LayerNot0Exception e) {
                System.out.println("Nem sikerült a visszatétel, mert a kéreg nem volt 0.");
            }
        }
    }

    /**
     * Leteszi a currentAsteroidára az egyik TpGate-t
     */
    @Override
    public void putTpGateDown(){
        if(tpGates.size() > 0){
            currentAsteroid.addActor(tpGates.get(0));
            currentAsteroid.addNeighbour(tpGates.get(0));
            tpGates.get(0).setInSettler(null);
            tpGates.remove(0);
            space.incrementCurrentActor();
        }
    }

    public MaterialArray getMaterials() {
        return materials;
    }

    public void addTpGate(TpGate tpg1) {
        tpGates.add(tpg1);
    }

    @Override
    public void drill() {
        currentAsteroid.getDrilled();
        space.incrementCurrentActor();
    }

    public List<TpGate> getTpGates(){
        return tpGates;
    }

    public void status() {
        System.out.println("Settler");
        System.out.println(space.getActors().indexOf(this));
        System.out.println(space.getAsteroids().indexOf(currentAsteroid));
        System.out.println(tpGates.size());
        System.out.println(materials.getMaterials().size());
        for(Material m : materials.getMaterials()){
            m.status();
        }
    }


    @Override
    public String getType(){
        return "Settler";
    }

}
