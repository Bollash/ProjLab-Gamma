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
            drill();
            space.incrementCurrentActor();
            return;
        }
        try {
            //Szomszédos fúrható aszteroidára mozog
            currentAsteroid.getDrillableNeighbour().addActor(this);
            System.out.println("Az actor egy szomszédos aszteroidára mozgott.");
        //Nincs fúrható aszteroida szomszéd
        }catch (NoDrillableNeighbourException ex){
            if(currentAsteroid.getNeighbours().size() != 0){
                try {
                    //Random szomszédos aszteroidára mozgott
                    currentAsteroid.getNeighbours().get(0).addActor(this);
                    System.out.println("Az actor egy szomszédos aszteroidára mozgott.");
                } catch (MoveFailedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Az actor nem tud szomszédos aszteroidára mozogni.");
            }
        }catch (MoveFailedException e){
            e.printStackTrace();
        }
        space.incrementCurrentActor();
    }

    @Override
    public void drill() {
        currentAsteroid.getDrilled();
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
