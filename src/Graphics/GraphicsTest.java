package Graphics;

import modell.*;

public class GraphicsTest {
    public static void main(String[] args){
        new MainMenuScreen();
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

        /*
        Space spacexd = new Space(0,0,0,1);
        spacexd.setAliveSettlerCnt(2);

        Asteroid a1 = new Asteroid(spacexd);
        Asteroid a2 = new Asteroid(spacexd);

        a1.setLayer(0);
        a1.setCoreMaterial(null);

        a1.addNeighbour(a2);
        a2.addNeighbour(a1);

        Settler s = new Settler();
        Settler s2 = new Settler();

        spacexd.addActor(s);
        spacexd.addActor(s2);
        a2.addActor(s2);
        a2.addActor(s);

        s.getMaterials().addMaterial(new Coal());
        s.getMaterials().addMaterial(new Coal());
        s.getMaterials().addMaterial(new Coal());
        s.getMaterials().addMaterial(new Iron());
        s.getMaterials().addMaterial(new Iron());
        s.getMaterials().addMaterial(new Iron());

        s2.getMaterials().addMaterial(new Ice());
        s2.getMaterials().addMaterial(new Ice());
        s2.getMaterials().addMaterial(new Ice());
        s2.getMaterials().addMaterial(new Uran());
        s2.getMaterials().addMaterial(new Uran());
        s2.getMaterials().addMaterial(new Uran());

        spacexd.addAsteroid(a1);
        spacexd.addAsteroid(a2);

        spacexd.getAsteroids().get(0).setTurnsTillCloseToSun(1);

        new GameScreen(spacexd);
       // new GameOverScreen("asd123");

         */
    }
}
