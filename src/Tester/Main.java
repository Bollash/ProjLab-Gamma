package Tester;

import modell.*;
import modell.Character;
import modell.exceptions.MoveFailedException;

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

    public void putTpGateDown() {
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        TpGate tpg1 = new TpGate();
        s.setCurrentAsteroid(a);
        a.addCharacter(s);
        s.putTpGateDown();
    }

    public void settlerMoves() throws MoveFailedException {
        Settler s = new Settler();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        s.setCurrentAsteroid(a1);
        a1.addCharacter(s);
        a1.addNeighbour(a2);
        a2.addNeighbour(a1);
        s.move(a2);
    }

    public void settlerUsesTpGate() {
        Settler s = new Settler();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        TpGate t1 = new TpGate();
        TpGate t2 = new TpGate();
        t1.setLinkedTpGate(t2);
        t2.setLinkedTpGate(t1);
        a1.addCharacter(s);
        s.setCurrentAsteroid(a1);
    }
}
