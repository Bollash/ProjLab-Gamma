package modell;

import modell.exceptions.MoveFailedException;

public interface IAsteroid {
    void addCharacter(Character character)throws MoveFailedException;

    void removeNeighbour(Asteroid asteroid);

    int getLayer();
}
