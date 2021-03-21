package modell;

import modell.exceptions.MoveFailedException;

public abstract class Character {
    protected Space space;
    protected Asteroid currentAsteroid;

    public abstract void radExplode();

    public void getSunStormed(){
        if(currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null) die();
    }

    public void drill(){
        currentAsteroid.getDrilled();
    }

    public void move(IAsteroid ast) throws MoveFailedException {
        System.out.println("Belépett a move-ba");
        ast.addCharacter(this);
    }

    public void act(){};

    public abstract void die();

    public MaterialArray getMaterials(){
        return new MaterialArray();
    }
}
