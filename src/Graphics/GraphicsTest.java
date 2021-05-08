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
        spacexd.addAsteroid(new Asteroid(spacexd));
        //cm
        spacexd.getAsteroids().get(0).setCoreMaterial(null);
        spacexd.getAsteroids().get(1).setCoreMaterial(new Iron());
        //neighbor
        spacexd.getAsteroids().get(0).addNeighbour(spacexd.getAsteroids().get(1));
        spacexd.getAsteroids().get(1).addNeighbour(spacexd.getAsteroids().get(0));
        //settler
        spacexd.addActor(new Settler());
        spacexd.addActor(new Settler());
        //settlerAddAst
        spacexd.getAsteroids().get(0).addActor(spacexd.getActors().get(0));
        spacexd.getAsteroids().get(1).addActor(spacexd.getActors().get(1));
        //new GameScreen(spacexd);
        new MainMenuScreen();
    }
}
