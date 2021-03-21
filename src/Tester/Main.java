package Tester;

import modell.Asteroid;
import modell.Settler;
import modell.Space;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){}

    public void craftRobot(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        Space space = new Space(1, 10, 10, new ArrayList<Asteroid>(a))
    }

    public void settlerDrill(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.drill();
    }

    public void settlerMine(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.mine();
    }
}
