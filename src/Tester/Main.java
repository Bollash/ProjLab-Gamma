package Tester;

import modell.*;

public class Main {
    public static void main(String[] args){}

    public void craftRobot(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Iron i = new Iron();
        Uran u = new Uran();
        Coal c = new Coal();
        s.getMaterials().addMaterial(i);
        s.getMaterials().addMaterial(c);
        s.getMaterials().addMaterial(u);
        s.craftRobot();
    }

    public void settlerDrill(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.setCurrentAsteroid(a);
        a.addCharacter(s);
        s.drill();
    }

    public void settlerMine(){
        Settler s = new Settler();
        Iron irn = new Iron();
        Asteroid a = new Asteroid(0,5, 5, irn);
        a.addCharacter(s);
        s.setCurrentAsteroid(a);
        s.mine();
    }
    public void craftGates() {
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Iron i1 = new Iron();
        Iron i2 = new Iron();
        Uran u = new Uran();
        Ice ic = new Ice();
        s.getMaterials().addMaterial(i1);
        s.getMaterials().addMaterial(i2);
        s.getMaterials().addMaterial(ic);
        s.getMaterials().addMaterial(u);
        s.craftGates();
    }
}
