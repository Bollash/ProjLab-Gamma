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

    @Override
    public boolean getSunStormed() {
        return false;
    }

    @Override
    public void act(){
        try {
            mine();
            System.out.println("Az actor bányászik");
        } catch (CantBeMinedException | LayerNot0Exception e) {
            //TODO: Jelenleg az első szomszédosra mozog. Jelenleg így működik, de ha hasznosra akarnánk csinálni akkor randomizálni kéne. De ez a teszteknél kiszámíthatatlan lenne.
            if(currentAsteroid.getNeighbours().size() != 0){
                try {
                    move(currentAsteroid.getNeighbours().get(0));
                } catch (MoveFailedException moveFailedException) {
                    moveFailedException.printStackTrace();
                }
            }else{
                System.out.println("Az actor nem tud szomszédos aszteroidára mozogni.");
            }
        }
    }

    @Override
    public void mine() throws CantBeMinedException, LayerNot0Exception {
        currentAsteroid.getMined();
    }

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
