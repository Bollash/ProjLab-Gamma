package Tester;

import modell.*;
import modell.Character;
import modell.exceptions.MoveFailedException;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        while(true){
            System.out.println("1 -> craftRobot\n" +
                    "2 -> settlerDrill\n" +
                    "3 -> settlerMine\n" +
                    "4 -> craftGates\n" +
                    "5 -> putTpGateDown\n" +
                    "6 -> settlerMoves\n" +
                    "7 -> settlerUsesTpGate\n" +
                    "8 -> sunStorm\n" +
                    "9 -> iceGetsCloseToSun\n" +
                    "10 -> settlerPutsMaterialBack\n" +
                    "11 -> _2ndToLastSettlerDies\n" +
                    "12 -> aiControllsRobot\n" +
                    "13 -> uranExplodes\n" +
                    "14 -> buildBase\n" +
                    "15 -> exit\n" +
                    "-----------------------------------\n" +
                    "Válasszon egy tesztesetet!");
            Scanner input = new Scanner(System.in);
            int testcase = input.nextInt();
            switch (testcase) {
                case 1 : craftRobot();
                break;
                case 2 : settlerDrill();
                break;
                case 3 : settlerMine();
                break;
                case 4 : craftGates();
                break;
                case 5 : putTpGateDown();
                break;
                case 6 : settlerMoves();
                break;
                case 7 : settlerUsesTpGate();
                break;
                case 8 : sunStorm();
                break;
                case 9 : iceGetsCloseToSun();
                break;
                case 10 : settlerPutsMaterialBack();
                break;
                case 11 : _2ndToLastSettlerDies();
                break;
                case 12 : aiControllsRobot();
                break;
                case 13 : uranExplodes();
                break;
                case 14 : buildBase();
                break;
                case 15 : System.exit(0);
                break;
            }
            System.out.println("------------test ended----------------");

        }
    }

    public static void uranExplodes() {
        ArrayList<Asteroid> astList = new ArrayList<Asteroid>();
        Asteroid a1 = new Asteroid(0,5,0, null);
        Asteroid a2 = new Asteroid();
        Robot r = new Robot();
        Uran u = new Uran();
        a1.addNeighbour(a2);
        a2.addNeighbour(a1);
        a1.setCoreMaterial(u);
        a1.addCharacter(r);
        astList.add(a1);
        astList.add(a2);
        r.setCurrentAsteroid(a1);
        Space s = new Space(0,5,5,astList,null);
        System.out.println("------------test starts now----------------");
        a1.handleCountDown();
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

    public static void settlerMoves() {
        Settler s = new Settler();
        Asteroid a1 = new Asteroid();
        Asteroid a2 = new Asteroid();
        s.setCurrentAsteroid(a1);
        a1.addCharacter(s);
        a1.addNeighbour(a2);
        a2.addNeighbour(a1);
        System.out.println("------------test starts now----------------");
        try {
            s.move(a2);
        } catch (MoveFailedException e) {
            e.printStackTrace();
        }
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

    public static void _2ndToLastSettlerDies(){
        Settler s1 = new Settler();
        Settler s2 = new Settler();

        List<Character> chars = new ArrayList<>();
        chars.add(s1);
        chars.add(s2);

        Space space = new Space(2, 3, 3, new ArrayList<Asteroid>(), chars);

        Asteroid ast = new Asteroid();
        s1.setSpace(space);
        s1.setCurrentAsteroid(ast);
        s2.setSpace(space);
        s2.setCurrentAsteroid(ast);

        System.out.println("------------test starts now----------------");
        s1.radExplode();
    }

    public static void aiControllsRobot(){
        Robot r = new Robot();
        Asteroid a1 = new Asteroid(0, 3, 3, null);
        Asteroid a2 = new Asteroid();
        a1.addNeighbour(a2);
        a2.addNeighbour(a1);
        r.setCurrentAsteroid(a1);
        a1.addCharacter(r);
        System.out.println("------------test starts now----------------");
        r.act();
    }

    public static void buildBase(){
        Asteroid ast = new Asteroid();

        Settler s1 = new Settler();
        s1.setCurrentAsteroid(ast);
        ast.addCharacter(s1);

        Iron iron1 = new Iron();
        Iron iron2 = new Iron();
        Iron iron3 = new Iron();

        Coal coal1 = new Coal();
        Coal coal2 = new Coal();
        Coal coal3 = new Coal();

        Ice ice1 = new Ice();
        Ice ice2 = new Ice();
        Ice ice3 = new Ice();

        MaterialArray arr1 = new MaterialArray();

        arr1.addMaterial(iron1);
        arr1.addMaterial(iron2);
        arr1.addMaterial(iron3);
        arr1.addMaterial(coal1);
        arr1.addMaterial(coal2);
        arr1.addMaterial(coal3);
        arr1.addMaterial(ice1);
        arr1.addMaterial(ice2);
        arr1.addMaterial(ice3);

        s1.setMaterialArray(arr1);

        Settler s2 = new Settler();
        s2.setCurrentAsteroid(ast);
        ast.addCharacter(s2);

        Uran uran1 = new Uran();
        Uran uran2 = new Uran();
        Uran uran3 = new Uran();

        MaterialArray arr2 = new MaterialArray();
        arr2.addMaterial(uran1);
        arr2.addMaterial(uran2);
        arr2.addMaterial(uran3);

        s2.setMaterialArray(arr2);

        List<Character> chars = new ArrayList<>();
        chars.add(s1);
        chars.add(s2);

        List<Asteroid> asts = new ArrayList<>();
        asts.add(ast);

        Space space = new Space(2, 3, 3, asts, chars);
        s1.setSpace(space);
        s2.setSpace(space);

        s1.buildBase();
    }


}
