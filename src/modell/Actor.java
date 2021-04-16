package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

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
    }

    public abstract void act();

    public MaterialArray getMaterials(){
        return new MaterialArray();
    }

    public void setCurrentAsteroid(Asteroid ast){
        currentAsteroid = ast;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
