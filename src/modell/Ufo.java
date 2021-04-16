package modell;

import modell.exceptions.CantBeMinedException;
import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

public class Ufo extends Actor implements iMine{
    @Override
    public boolean radExplode() {
        return false;
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
        } catch (CantBeMinedException e) {
            //TODO: Jelenleg az első szomszédosra mozog. Jelenleg így működik, de ha hasznosra akarnánk csinálni akkor randomizálni kéne. De ez a teszteknél kiszámíthatatlan lenne.
            if(currentAsteroid.getNeighbours().size() != 0){
                try {
                    move(currentAsteroid.getNeighbours().get(0));
                    System.out.println("Az actor egy szomszédos aszteroidára mozog");
                } catch (MoveFailedException moveFailedException) {
                    moveFailedException.printStackTrace();
                }
            }else{
                System.out.println("Az actor nem tud szomszédos aszteroidára mozogni.");
            }
        }
    }

    @Override
    public void mine() throws CantBeMinedException {
        currentAsteroid.getMined();
    }
}
