package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

import java.util.List;
import java.util.Random;

public class Robot extends Actor implements iDrill{

    /**
     * Radioaktív robbanás miatt egy szomszédos aszteroidán landol a robot
     */
    @Override
    public boolean radExplode() {
        List<IAsteroid> destination = currentAsteroid.getNeighbours();

        Random rnd = new Random(System.currentTimeMillis());

        //Ha egyikre se lehet lépni akkor infinite loop
        //TODO: rendesen megoldani ezt
        while(true){
            try{
                destination.get(rnd.nextInt(destination.size())).addActor(this);
                return false;
            }catch(MoveFailedException ignored){
            }
        }
    }

    @Override
    public boolean getSunStormed() {
        // Ha meghal leveszi magát a spaceről
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
            currentAsteroid.getDrilled();
            return;
        }
        try {
            currentAsteroid.getDrillableNeighbour().addActor(this);
        }catch (NoDrillableNeighbourException ex){
            Random rnd = new Random(System.currentTimeMillis());
            try {
                currentAsteroid.getNeighbours().get(rnd.nextInt(currentAsteroid.getNeighbours().size())).addActor(this);
            } catch (MoveFailedException e) {
                e.printStackTrace();
            }
        }catch (MoveFailedException ignored){}
    }

    @Override
    public void drill() {
        currentAsteroid.getDrilled();
    }
}
