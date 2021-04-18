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
            drill();
            System.out.println("Az actor megfúrja az aszteroidát");
            return;
        }
        try {
            //Szomszédos fúrható aszteroidára mozog
            currentAsteroid.getDrillableNeighbour().addActor(this);
            System.out.println("Az actor egy szomszédos aszteroidára mozgott.");
        //Nincs fúrható aszteroida szomszéd
        }catch (NoDrillableNeighbourException ex){
            if(currentAsteroid.getNeighbours().size() != 0){
                Random rnd = new Random(System.currentTimeMillis());
                try {
                    //Random szomszédos aszteroidára mozgott
                    currentAsteroid.getNeighbours().get(rnd.nextInt(currentAsteroid.getNeighbours().size())).addActor(this);
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
    }

    @Override
    public void drill() {
        currentAsteroid.getDrilled();
    }

    @Override
    public void putMaterialBack(Material mat){}
}
