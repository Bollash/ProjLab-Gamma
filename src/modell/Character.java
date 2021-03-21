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
        System.out.println("Belépett a getSunStormed-ba");
        System.out.println("Kilépett a getSunStromed-ból");
        return currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null;
    }

    /**
     * megfúrja a currentAsteroid-ot
     */
    public void drill(){
        System.out.println("Belépett a drill-be");
        currentAsteroid.getDrilled();
        System.out.println("Kilépett a drill-ből");
    }

    /**
     * A kapott IAsteroid-ra mozog
     * @param ast A mozgás uticélja
     * @throws MoveFailedException Sikertelen volt a mozgás
     */
    public void move(IAsteroid ast) throws MoveFailedException {
        System.out.println("Belépett a move-ba");
        ast.addCharacter(this);
        System.out.println("Kilépett a move-ból");
    }

    public abstract void act() throws NoDrillableNeighbourException;

    public MaterialArray getMaterials(){
        System.out.println("Belépett a getMaterials-ba");
        System.out.println("Kilépett a getMaterials-ból");
        return new MaterialArray();
    }

    public void setCurrentAsteroid(Asteroid ast){
        currentAsteroid = ast;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
