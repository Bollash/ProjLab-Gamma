package modell;

import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;

public abstract class Character {
    protected Space space;
    protected Asteroid currentAsteroid;

    public abstract boolean radExplode();

    /**
     * Napvihar éri a karaktert és ha nem tud elbújni meghal
     */
    public boolean getSunStormed(){
        return currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null;
    }

    /**
     * megfúrja a currentAsteroid-ot
     */
    public void drill(){
        currentAsteroid.getDrilled();
    }

    /**
     * A kapott IAsteroid-ra mozog
     * @param ast A mozgás uticélja
     * @throws MoveFailedException Sikertelen volt a mozgás
     */
    public void move(IAsteroid ast) throws MoveFailedException {
        ast.addCharacter(this);
    }

    public abstract void act() throws NoDrillableNeighbourException;

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
