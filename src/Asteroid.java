import exceptions.CantBeMinedException;
import exceptions.CoreFullException;

import java.util.ArrayList;
import java.util.List;

public class Asteroid implements IAsteroid {

    private List<IAsteroid> neighbours;

    private List<Character> charactersOnSurface;

    private int closeToSunFreq;

    private int layer;

    private int turnsTillCloseToSun;

    private Material coreMaterial;

    public Asteroid(int layer, int closeToSunFreq, int turnsTillCloseToSun, Material core){
        neighbours = new ArrayList<>();
        charactersOnSurface = new ArrayList<>();

        this.layer = layer;
        this.closeToSunFreq = closeToSunFreq;
        this.turnsTillCloseToSun = turnsTillCloseToSun;
        coreMaterial = core;
    }

    public void getDrilled(){}

    public Material getMined()throws CantBeMinedException {}

    public void addNeighbour(IAsteroid neighbour){}

    public MaterialArray countMaterialOnSurface(){}

    public void addCore(Material material)throws CoreFullException {}

    public void explode(){}

    public void removeCharacter(Character character){}

    public void handleCountDown(){}

    @Override
    public void addCharacter(Character character) {
        if(!charactersOnSurface.contains(character)){
            charactersOnSurface.add(character);
        }
    }

    @Override
    public void removeNeighbour(Asteroid asteroid) {
        neighbours.remove(asteroid);
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Material getCoreMaterial() {
        return coreMaterial;
    }

    public void setCoreMaterial(Material coreMaterial) {
        this.coreMaterial = coreMaterial;
    }
}
