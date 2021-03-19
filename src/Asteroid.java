import java.util.ArrayList;
import java.util.List;

public class Asteroid implements IAsteroid {

    private List<IAsteroid> neighbours;

    private List<Character> charactersOnSurface;

    private int closeToSunFreq;

    private int layer;

    private int turnsTillCloseToSun;

    private Material coreMaterial;

    public Asteroid(int layer, int closeToSunFreq, int turnsTillCloseToSun){
        neighbours = new ArrayList<>();
        charactersOnSurface = new ArrayList<>();

        this.layer = layer;
        this.closeToSunFreq = closeToSunFreq;
        this.turnsTillCloseToSun = turnsTillCloseToSun;
    }

    public void getDrilled(){}

    public Material getMined(){}

    public void addNeighbour(IAsteroid neighbour){}

    public MaterialArray countMaterialOnSurface(){}

    public void addChore(Material material){}

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
}
