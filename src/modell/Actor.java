package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;
import java.io.Serializable;

public abstract class Actor implements java.io.Serializable{


    protected Space space;

    protected Asteroid currentAsteroid;

    public abstract boolean radExplode();

    public abstract boolean getSunStormed();

    /**
     * A kapott IAsteroid-ra mozog
     * @param ast A mozgás uticélja
     * @throws MoveFailedException Sikertelen volt a mozgás
     */
    public void move(IAsteroid ast) throws MoveFailedException {
        ast.addActor(this);
        System.out.println("Az aktor az aszteroidára mozgott.");
    }

    public abstract void act();

    public abstract void putMaterialBack(Material mat);

    public void putTpGateDown(){}

    public MaterialArray getMaterials(){
        return new MaterialArray();
    }

    public Asteroid getCurrentAsteroid() { return currentAsteroid; }

    public void setCurrentAsteroid(Asteroid ast){
        currentAsteroid = ast;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public abstract void status();
}
