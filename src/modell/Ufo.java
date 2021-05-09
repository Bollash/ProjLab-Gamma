package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.LayerNot0Exception;
import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

public class Ufo extends Actor implements iMine{
    @Override
    public boolean radExplode() {
        space.removeActor(this);
        return true;
    }

    /**
     * Túléli az ufó
     * @return false-> nem hal meg
     */
    @Override
    public boolean getSunStormed() {
        return false;
    }

    /**
     * Ha tud bányászik, ha nem akkor mozog
     */
    @Override
    public void act(){
        try {
            mine();
        } catch (CantBeMinedException | LayerNot0Exception e) {
            if(currentAsteroid.getNeighbours().size() != 0){
                try {
                    move(currentAsteroid.getNeighbours().get(0));
                } catch (MoveFailedException moveFailedException) {
                    moveFailedException.printStackTrace();
                }
            }
        }
    }

    /**
     * Bányászik az ufo
     * @throws CantBeMinedException
     * @throws LayerNot0Exception
     */
    @Override
    public void mine() throws CantBeMinedException, LayerNot0Exception {
        currentAsteroid.getMined();
        space.incrementCurrentActor();
    }

    //Nem csinálunk semmit, és ennek nem is kéne itt lennie, de utólsó nap nem változtátnék ezen
    /**
     * Nem csinál semmit.
     * @param mat
     */
    @Override
    public void putMaterialBack(Material mat){}

    public void status() {
        System.out.println("Ufo");
        System.out.println(space.getActors().indexOf(this));
        System.out.println(space.getAsteroids().indexOf(currentAsteroid));
    }

    @Override
    public String getType(){
        return "Ufo";
    }
}
