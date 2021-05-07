package Graphics;

import modell.*;

public class GraphicsTest {
    public static void main(String[] args){
        //new MainMenuScreen();
        //new NewGameScreen();
        //System.out.println(MaterialType.Coal.toString());
        //Space sp = new Space(3, 4, 5, 6);
        //Asteroid ast = new Asteroid(sp);
        //new NeighbourInfo(ast);
        //new SaveGameScreen(new Space(0, 0, 0, 10));
        //System.out.println(MaterialType.Coal.toString());
        /*
        Space spacee = new Space(5,20,20,20,70);
        new GameScreen(spacee);
        */
        Space spacexd = new Space(0,0,0,10);
        spacexd.addAsteroid(new Asteroid(spacexd));
        spacexd.addActor(new Settler());
        spacexd.addActor(new Ufo());
        spacexd.addActor(new Robot());
        spacexd.getAsteroids().get(0).addActor(spacexd.getActors().get(0));
        spacexd.getAsteroids().get(0).addActor(spacexd.getActors().get(1));
        spacexd.getAsteroids().get(0).addActor(spacexd.getActors().get(2));
        new GameScreen(spacexd);
    }
}
