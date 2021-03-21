package modell;

import modell.exceptions.MoveFailedException;

public abstract class Character {
    protected Space space;
    protected Asteroid currentAsteroid;

    public abstract void radExplode();

    public void getSunStormed(){
        System.out.println("Belépett a getSunStormed-ba");
        if(currentAsteroid.getLayer() != 0 || currentAsteroid.getCoreMaterial() != null) die();
        System.out.println("Kilépett a getSunStromed-ból");
    }

    public void drill(){
        System.out.println("Belépett a drill-be");
        currentAsteroid.getDrilled();
        System.out.println("Kilépett a drill-ből");
    }

    public void move(IAsteroid ast) throws MoveFailedException {
        System.out.println("Belépett a move-ba");
        ast.addCharacter(this);
        System.out.println("Kilépett a move-ból");
    }

    public abstract void act();

    public abstract void die();

    public MaterialArray getMaterials(){
        System.out.println("Belépett a getMaterials-ba");
        System.out.println("Kilépett a getMaterials-ból");
        return new MaterialArray();
    }
}
