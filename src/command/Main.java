package command;

import modell.*;
import modell.Space;
import modell.exceptions.NoDrillableNeighbourException;
import modell.exceptions.NotEnoughMaterialException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args){
        cmdProg(System.in);
    }

    private static int seed;
    private static Space space;
    private static int currentActor;

    /**
     * Parancs kezelő program
     * @param source System.in vagy new FileInputStream(new File(filename))
     */
    public static void cmdProg(InputStream source){
        Scanner input = new Scanner(source);
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] tokenized = line.split(" ");

            //A switchen belül egy functiont adok át aminek paraméterként egy String[]-et kell adni és voiddal tér vissza
            Consumer<String[]> func;
            switch (tokenized[0]) {
                case "Seed" -> func = Main::seed;
                case "Map" -> func = Main::map;
                case "Act" -> func = Main::act;
                case "Countdown" -> func = Main::countdown;
                default -> func = str -> {System.out.println("Nem létező parancsot hívott meg"); };
            }
            func.accept(Arrays.copyOfRange(tokenized, 1, tokenized.length));
        }
    }

    /**
     * Map parancs feldolgozására szolgáló metódus.
     * @param cmd Egy integer amire a seed-et állítjuk
     */
    public static void seed(String[] cmd){
        if(cmd.length == 1){
            try{
                seed = Integer.parseInt(cmd[0]);
                System.out.println("Sikeres volt a seed beállítás.");
            } catch(NumberFormatException ex){
                System.out.println("Nem sikerült beállítani a seed-et, mert rossz volt a paraméter.");
                return;
            }
        }
        System.out.println("Nem sikerült beállítani a seed-et, mert rossz volt a paraméter.");
    }

    /**
     * Map parancs feldolgozására szolgáló metódus.
     * @param cmd parancs maradék része. 2 féle lehet. 1 fájl név, vagy 3 integer paraméter
     */
    public static void map(String[] cmd){
        if(cmd.length == 1){
            try {
                deserialize(cmd[0]);
                System.out.println("Sikeres volt a pálya betöltés");
            } catch (IOException e) {
                System.out.println("Nem sikerült a pálya betöltés");
            }
        }else if(cmd.length == 3){
            try {
                //Átparseoljuk integerré a paramétereket
                int[] parameters = new int[3];
                for(int i = 0; i < 3; i++){
                    parameters[i] = Integer.parseInt(cmd[i]);
                }
                space = new Space(parameters[0], parameters[1], parameters[2]);
                System.out.println("Sikeres volt az új pálya generálás");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Sikertelen volt a pályagenerálás, mert nem voltak megfelelőek a paraméterek.");
            }

        }else{
            System.out.println("Sikertelen volt a pályagenerálás, mert nem voltak megfelelőek a paraméterek.");
        }

    }

    /**
     * Serializáljuk a space-t.
     * @param path fájl neve amibe serializálunk
     */
    public static void serialize(String path){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(space);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserializálunk egy spacet.
     * @param path Fájl neve ahonnan deserializálunk.
     * @throws IOException Ezt dobjuk, ha nem volt ilyen nevű fájl
     */
    public static void deserialize(String path) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            space = (Space) in.readObject();
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException c) {
            System.out.println("Space class not found");
            c.printStackTrace();
        }
    }

    /**
     * Act parancs feldolgozására szolgáló metódus.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void act(String[] cmd){
        if(cmd.length == 0){
            if(currentActor == space.getActors().size()){
                currentActor = 0;
            }
            space.getActors().get(currentActor).act();
            currentActor++;
            return;
        }else if(cmd.length == 1){
            try{
                int idx = Integer.parseInt(cmd[0]);
                if(idx < space.getActors().size() && idx >= 0){
                    space.getActors().get(idx).act();
                    return;
                }
            }catch(NumberFormatException e){
                System.out.println("Nem létezik actor ezzel az indexxel.");
                return;
            }
        }
        System.out.println("Nem létezik actor ezzel az indexxel.");
    }

    public static void countdown(String[] cmd){
        if(cmd.length == 0){
            space.handleCountDown();
            return;
        }
        if(cmd.length == 1){
            try{
                int idx = Integer.parseInt(cmd[0]);
                if(idx < space.getAsteroids().size() && idx >= 0){
                    space.getAsteroids().get(idx).handleCountDown();
                    return;
                }
            }catch(NumberFormatException e){
                System.out.println("Nem létezik az indexnek megfelelő aszteroida");
            }
        }
        System.out.println("Nem létezik az indexnek megfelelő aszteroida");
    }

    /**
     * Build metódus, építést hajtja vége.
     * @param actor Paraméterként megkapott string, mely alapján dönti el a program az építendő objektumot.
     * @param index Paraméterként kapott string, annak az actornak az indexe van benne, melyre meghívódik az építés.
     * @throws NotEnoughMaterialException Ezt dobjuk, ha nincs elég nyersanyag.
     */
    public static void build(String actor, String index) throws NotEnoughMaterialException {
        if(currentActor == space.getActors().size()){
            currentActor = 0;
        }
        else{
            try{
                currentActor = Integer.parseInt(index);
            }catch(NumberFormatException e){
                System.out.println("Nem létezik az indexnek megfelelő actor.");
            }
        }
        if(actor.equals("Robot")) {
                space.getActors().get(currentActor).getMaterials().buildRobot();
        }
        if(actor.equals("TpGate")) {
            space.getActors().get(currentActor).getMaterials().buildRobot();
        }
        if(actor.equals("Base")) {
            if (space.getActors().get(currentActor).getMaterials().canBuildBase()) {
                //na itt mi van?
            }
        }
        currentActor++;
        return;
    }


    /**
     * PutBack metódus, nyersanyag visszahelyezésre szolgál.
     * @param index Paraméterként kapott string, annak az actornak az indexe van benne, melyre meghívódik a PutBack.
     * @param materialName String, mely a visszahelyezni kívánt nyersanyag nevét tartalmazza.
     */
    public static void putBack(String index, String materialName){
        if(currentActor == space.getActors().size()){
            currentActor = 0;
        }
        else{
            try{
                currentActor = Integer.parseInt(index);
            }catch(NumberFormatException e){
                System.out.println("Nem létezik az indexnek megfelelő actor.");
            }
        }
        if(materialName.equals("Uran")) {
            int idx = space.getActors().get(currentActor).getMaterials().getUran();
            if(idx != -1)
                space.getActors().get(currentActor).putMaterialBack(space.getActors().get(currentActor).getMaterials().getMaterials().get(idx));
        }
        if(materialName.equals("Ice")) {
            int idx = space.getActors().get(currentActor).getMaterials().getIce();
            if(idx != -1)
                space.getActors().get(currentActor).putMaterialBack(space.getActors().get(currentActor).getMaterials().getMaterials().get(idx));
        }
        if(materialName.equals("Coal")) {
            int idx = space.getActors().get(currentActor).getMaterials().getCoal();
            if(idx != -1)
                space.getActors().get(currentActor).putMaterialBack(space.getActors().get(currentActor).getMaterials().getMaterials().get(idx));
        }
        if(materialName.equals("Iron")) {
            int idx = space.getActors().get(currentActor).getMaterials().getIron();
            if(idx != -1)
                space.getActors().get(currentActor).putMaterialBack(space.getActors().get(currentActor).getMaterials().getMaterials().get(idx));
        }
        else {
            System.out.println("Nincs ilyen nyersanyag a játékban.");
        }
        currentActor++;
        return;
    }


    /**
     * Metódus teleportkapu lerakására.
     * @param index Paraméterként kapott string, annak az actornak az indexe van benne, melyre meghívódik az építés.
     */
    public static void putTpGate(String index) {
        if(currentActor == space.getActors().size()){
            currentActor = 0;
        }
        else{
            try{
                currentActor = Integer.parseInt(index);
            }catch(NumberFormatException e){
                System.out.println("Nem létezik az indexnek megfelelő actor.");
            }
        }
        space.getActors().get(currentActor).putTpGateDown();
        currentActor++;
        return;
    }


    /**
     * Lementi a pálya aktuális állását.
     * @param name Ezen a néven menti el a fájlt.
     */
    public static void save(String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(name + ".txt");
        ObjectOutputStream out  = new ObjectOutputStream(fos);
        out.writeObject(space);
        out.close();
        return;
    }


    /**
     * Kiírja a kimenetre a space tulajdonságait.
     */
    public static void spaceStatus(){
        System.out.println("Space");
        System.out.println(space.getAliveSettlerCnt());
        System.out.println(space.getTurnsTillSunStorm());
        System.out.println(space.getSunStormFreq());
        System.out.println(space.isGameOver());
        System.out.println(space.asteroidCount());
        System.out.println(space.actorCount());
    }

    /**
     * Kiírja a kimenetre egy aszteroida tulajdonságait.
     */
    public static void asteroidStatus(int index){
        System.out.println("Asteroid");
        System.out.println(index);
        System.out.println(space.getAsteroids().get(index).getTurnsTillCloseToSun());
        System.out.println(space.getAsteroids().get(index).getCloseToSunFreq());
        System.out.println(space.getAsteroids().get(index).getLayer());
        System.out.println(space.getAsteroids().get(index).getCoreMaterial().getType());
        System.out.println(space.getAsteroids().get(index).neighborCount());
        for(int i = 0; i < space.getAsteroids().get(index).neighborCount(); i++) {
          //  if(space.getAsteroids().get(index).getNeighbours().get(i).//itt tipuslekerdezes lenne)
        }
        System.out.println(space.getAsteroids().get(index).actorsOnSurfaceCount());
        for(int i = 0; i < space.getAsteroids().get(index).actorsOnSurfaceCount(); i++) {
            System.out.println(space.getAsteroids().get(index).getActorsOnSurface().get(i));
        }
    }


    /**
     * Kiírja a kimenetre a Settler tulajdonságait.
     */
    public static void settlerStatus(Settler s){
        System.out.println("Settler");
        //sok kerdes, tipuslekerdezessel kapcs + indexekkel, meg kene beszelni
    }



    /**
     * Kiírja a kimenbetre a megadott objektum vagy objektumok állapotát.
     * @param object Ezen paraméterrel meghatározható a kiíratni kívánt objektum.
     */
    public static void status(String object){
        if(object.equals(null)) {
            spaceStatus();
        }
        return;
    }
}
