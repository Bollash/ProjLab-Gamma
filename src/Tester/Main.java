package Tester;

import modell.*;
import modell.Character;
import modell.exceptions.MoveFailedException;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        System.out.println("Írjon be egy teszteset számot !");
        while(true){
            Scanner input = new Scanner(System.in);
            int testcase = input.nextInt();
            if(testcase == 0)
                break;
            if(testcase ==1){
                craftRobot();
            }
            if(testcase ==2){
                settlerDrill();
            }
            if(testcase ==3){
                settlerMine();
            }
            if(testcase ==4){
                craftGates();
            }
            if(testcase ==5){
                putTpGateDown();
            }
            if(testcase ==6){
                //settlerMoves();
            }
            if(testcase ==7){
                settlerUsesTpGate();
            }
            if(testcase ==8){
                sunStorm();
            }
            if(testcase ==9){
                iceGetsCloseToSun();
            }
            if(testcase ==10){
                settlerPutsMaterialBack();
            }
        }
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

    public static void settlerDrill(){
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        s.setCurrentAsteroid(a);
        System.out.println("------------test starts now----------------");
        s.drill();
    }

    public static void settlerMine(){
        Settler s = new Settler();
        Iron irn = new Iron();
        Asteroid a = new Asteroid(0,5, 5, irn);
        a.addCharacter(s);
        s.setCurrentAsteroid(a);
        System.out.println("------------test starts now----------------");
        s.mine();
    }

    public static void craftGates() {
        Settler s = new Settler();
        Iron i1 = new Iron();
        Iron i2 = new Iron();
        Uran u = new Uran();
        Ice ic = new Ice();
        s.getMaterials().addMaterial(i1);
        s.getMaterials().addMaterial(i2);
        s.getMaterials().addMaterial(ic);
        s.getMaterials().addMaterial(u);
        System.out.println("------------test starts now----------------");
        s.craftGates();
    }

    public static void putTpGateDown() {
        Settler s = new Settler();
        Asteroid a = new Asteroid();
        TpGate tpg1 = new TpGate();
        s.setCurrentAsteroid(a);
        a.addCharacter(s);
        s.addTpGate(tpg1);
        System.out.println("------------test starts now----------------");
        s.putTpGateDown();
    }

    public static void settlerMoves() throws MoveFailedException {
        Settler s = new Settler();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        s.setCurrentAsteroid(a1);
        a1.addCharacter(s);
        a1.addNeighbour(a2);
        a2.addNeighbour(a1);
        System.out.println("------------test starts now----------------");
        s.move(a2);
    }

    public static void settlerUsesTpGate() {
        Settler s = new Settler();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        TpGate t1 = new TpGate();
        TpGate t2 = new TpGate();
        t1.setLinkedTpGate(t2);
        t2.setLinkedTpGate(t1);
        a1.addCharacter(s);
        s.setCurrentAsteroid(a1);

        t1.setOnAsteroid(a1);
        t2.setOnAsteroid(a2);
        try {
            System.out.println("------------test starts now----------------");
            s.move(t1);
        } catch (MoveFailedException e) {
            e.printStackTrace();
        }

    }

    public static void sunStorm(){
        Settler s = new Settler();
        List<Character> chars = new ArrayList<>();
        chars.add(s);
        Asteroid a = new Asteroid();
        List<Asteroid> asts = new ArrayList<>();
        asts.add(a);
        Space space = new Space(1, 1, 10, asts, chars);
        s.setSpace(space);
        s.setCurrentAsteroid(a);
        a.addCharacter(s);

        System.out.println("------------test starts now----------------");
        space.handleCountDown();
    }

    public static void iceGetsCloseToSun() {
        Ice i = new Ice();
        Asteroid a = new Asteroid(0, 5, 0, i);
        System.out.println("------------test starts now----------------");
        a.handleCountDown();
    }
    public static void settlerPutsMaterialBack() {
        Settler s = new Settler();
        Asteroid a = new Asteroid(0, 5, 5, null);
        s.setCurrentAsteroid(a);
        a.addCharacter(s);
        Ice i = new Ice();
        s.getMaterials().addMaterial(i);
        System.out.println("------------test starts now----------------");
        s.putMaterialBack(i);
    }
}
