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

    public void move(IAddCharacter ast){
        ast.addCharacter(this);
    }

    public abstract void act();

    public abstract void die();

    public void countMaterials(MaterialArray ma){}
}
