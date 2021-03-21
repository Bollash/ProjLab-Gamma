package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

import java.util.List;
import java.util.Random;

public class Robot extends Character{

    @Override
    public void radExplode() {
        System.out.println("Belépett a radExplode-be");
        List<IAsteroid> destination = currentAsteroid.getNeighbours();

        Random rnd = new Random(System.currentTimeMillis());

        //Ha egyikre se lehet lépni akkor infinite loop
        //TODO: rendesen megoldani ezt
        while(true){
            try{
                destination.get(rnd.nextInt(destination.size())).addCharacter(this);
                System.out.println("Kilépett a radExplode-ból");
                return;
            }catch(MoveFailedException ignored){
            }
        }
    }

    @Override
    public void act() throws NoDrillableNeighbourException, MoveFailedException {
        System.out.println("Belépett az act-ba");
        if(currentAsteroid.getLayer() > 0){
            currentAsteroid.getDrilled();
            System.out.println("Kilépett az act-ból");
            return;
        }
        try {
            currentAsteroid.getDrillableNeighbour().addCharacter(this);
        }catch (NoDrillableNeighbourException ex){
            Random rnd = new Random(System.currentTimeMillis());
            currentAsteroid.getNeighbours().get(rnd.nextInt(currentAsteroid.getNeighbours().size()));
        }catch (MoveFailedException ignored){}
    }

    @Override
    public void die() {
        System.out.println("Belépett a die-ba");
        space.removeCharacter(this);
        System.out.println("Kilépett a die-ból");
    }
}
