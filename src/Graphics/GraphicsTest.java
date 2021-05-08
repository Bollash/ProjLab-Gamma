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
        //cm
        spacexd.getAsteroids().get(0).setCoreMaterial(new Uran());
        //closetosunset
        spacexd.getAsteroids().get(0).setCloseToSunFreq(5);
        spacexd.getAsteroids().get(0).setTurnsTillCloseToSun(5);
        //settler
        spacexd.addActor(new Settler());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Iron());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Iron());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Uran());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Uran());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Ice());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Ice());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Coal());
        spacexd.getActors().get(0).getMaterials().addMaterial(new Coal());
        //settlerAddAst
        spacexd.getAsteroids().get(0).addActor(spacexd.getActors().get(0));
        //new GameScreen(spacexd);
        new GameOverScreen("asd123");
    }
}
