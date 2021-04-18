package modell;

import modell.exceptions.MoveFailedException;

public interface IAsteroid{

    void addActor(Actor actor)throws MoveFailedException;
    void addActor(Actor actor, boolean remove)throws MoveFailedException;

    void removeNeighbour(IAsteroid asteroid);

    int getLayer();
}
