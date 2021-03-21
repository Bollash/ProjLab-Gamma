package Tester;

import modell.*;
import modell.Character;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
    }

    public static void craftRobot(){
        Settler s = new Settler();
        List<Character> chars = new ArrayList<>();
        chars.add(s);
        Asteroid a = new Asteroid();
        List<Asteroid> asts = new ArrayList<>();
        asts.add(a);
        Space space = new Space(1, 10, 10, asts, chars);
        s.setSpace(space);
        s.setCurrentAsteroid(a);
        a.addCharacter(s);

        MaterialArray arr = new MaterialArray();
        arr.addMaterial(new Iron());
        arr.addMaterial(new Coal());
        arr.addMaterial(new Uran());

        s.setMaterialArray(arr);
        System.out.println("------------test starts now----------------");
        s.craftRobot();
    }

    public void settlerDrill(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.setCurrentAsteroid(a);
        System.out.println("------------test starts now----------------");
        s.drill();
    }

    public void settlerMine(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.mine();
    }
}
