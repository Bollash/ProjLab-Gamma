import java.util.Random;

public class Robot extends Character{

    @Override
    public void radExplode() {
        IAddCharacter[] destination = currentAsteroid.getNeighbours();

        Random rnd = new Random(System.currentTimeMillis());

        //Ha egyikre se lehet lépni akkor infinite loop
        //TODO: rendesen megoldani ezt
        while(true){
            try{
                destination[rnd.nextInt(destination.length)].addCharacter(this);
                break;
            }catch(MoveFailedException ignored){
            }
        }
    }

    @Override
    public void act() {
        if(currentAsteroid.getLayer() > 0){
            currentAsteroid.getDrilled();
            return;
        }
        currentAsteroid.getDrillableNeighbour().addCharacter(this);
    }

    @Override
    public void die() {
        space.removeCharacter(this);
    }
}
