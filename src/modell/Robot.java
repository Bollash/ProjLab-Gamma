package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

import java.util.List;
import java.util.Random;

public class Robot extends Actor implements iDrill, java.io.Serializable{

    /**
     * Radioaktív robbanás miatt egy szomszédos aszteroidán landol a robot
     */
    @Override
    public boolean radExplode() {
        List<IAsteroid> destination = currentAsteroid.getNeighbours();
        try{
                if(destination.size() != 0){
                    destination.get(0).addActor(this, false);
                }else{
                    space.removeActor(this);
                }
            return true;

        }catch(MoveFailedException ignored){
            return true;
        }
    }

    /**
     * Napvihar éri a robotot és ha nem tud elbújni, akkor meghal és leveszi magát a spaceről
     * @return boolean érték
     */
    @Override
    public boolean getSunStormed() {
        if(currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null){
            space.removeActor(this);
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Ha a current asteroid még nincs átfúrva akkor fúr. Ha át van akkor egy nem átfúrt szomszédra mozog
     */
    @Override
    public void act(){
        if(currentAsteroid.getLayer() > 0){
            drill();
            return;
        }
        try {
            currentAsteroid.getDrillableNeighbour().addActor(this);
        }catch (NoDrillableNeighbourException ex){
            if(currentAsteroid.getNeighbours().size() != 0){
                try {
                    currentAsteroid.getNeighbours().get(0).addActor(this);
                } catch (MoveFailedException e) {
                    e.printStackTrace();
                }
            }else{
            }
        }catch (MoveFailedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void drill() {
        currentAsteroid.getDrilled();
        space.incrementCurrentActor();
    }

    @Override
    public void putMaterialBack(Material mat){}

    @Override
    public void status() {
        System.out.println("Robot");
        System.out.println(space.getActors().indexOf(this));
        System.out.println(space.getAsteroids().indexOf(currentAsteroid));
    }

    @Override
    public String getType(){
        return "Robot";
    }
}
