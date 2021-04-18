package command;

import modell.*;
import modell.Space;
import modell.exceptions.CantBeMinedException;
import modell.exceptions.MoveFailedException;
import modell.exceptions.NoDrillableNeighbourException;
import modell.exceptions.NotEnoughMaterialException;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args){
        cmdProg(System.in, System.out);
    }

    private static int seed;
    private static Space space;
    private static int currentActor;

    /**
     * Parancs kezelő program
     * @param source System.in vagy new FileInputStream(new File(filename))
     */
    public static void cmdProg(InputStream source, PrintStream out){
        System.setOut(out);
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
                case "Mine" -> func = Main::mine;
                case "NeighbourCnt" -> func = Main::neighbourCnt;
                case "Move" -> func = Main::move;
                case "Drill" -> func = Main::drill;
                case "Build" -> func = Main::build;
                case "Putback" -> func = Main::putBack;
                case "PutTpGate" -> func = Main::putTpGate;
                case "Save" -> func = Main::save;
                case "Neighbour" -> func = Main::neighbour;
                case "Mod" -> func = Main::mod;
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
     * A paraméterként kapott számú aktort bányászásra bírjuk, ha tud olyat.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void mine(String[] cmd) {
        if(cmd.length == 0) {
            if (currentActor == space.getActors().size()) {
                currentActor = 0;
            }
            try {
                ((iMine) space.getActors().get(currentActor)).mine();
                currentActor++;
                return;
            } catch (CantBeMinedException e) {
                System.out.println("Az aktor nem képes bányászni.");
                return;
            }
        } else if(cmd.length == 1) {
            try {
                int idx = Integer.parseInt(cmd[0]);
                if(idx < space.getActors().size() && idx >= 0) {
                    ((iMine) space.getActors().get(idx)).mine();
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Nem létezik ilyen indexű actor.");
                return;
            } catch (CantBeMinedException e) {
                System.out.println("Az aktor nem képes bányászni.");
                return;
            }
        }
        System.out.println("Nem létezik ilyen indexű actor.");
    }

    /**
     * A paraméteként kapott aszteroidának/actor aszteroidájának szomszédos aszteroidáinak száma.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void neighbourCnt(String[] cmd){
        if(cmd.length == 2) {
            if (cmd[0].equals("Asteroid")) {
                try {
                    int idx = Integer.parseInt(cmd[1]);
                    System.out.println(space.getAsteroids().get(idx).getNeighbours().size());
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Nem létezik ilyen indexű asteroid.");
                    return;
                }
            }
            if (cmd[0].equals("Actor")) {
                try {
                    int idx = Integer.parseInt(cmd[1]);
                    System.out.println(space.getActors().get(idx).getCurrentAsteroid().getNeighbours().size());
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Nem létezik ilyen indexű actor.");
                    return;
                }
            }
        }
        System.out.println("Nem létezik ilyen indexű actor.");
    }

    /**
     * A kapott aszteroidára mozog az actor.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void move(String[] cmd) {
        if (cmd.length == 1) {
            if (currentActor == space.getActors().size()) {
                currentActor = 0;
            }
            try {
                int astidx = Integer.parseInt(cmd[0]);
                space.getActors().get(currentActor).move(space.getAsteroids().get(astidx));
                currentActor++;
                return;
            } catch (MoveFailedException e) {
                System.out.println("Nem sikerült a mozgás, mert nem létezik ilyen indexű aszteroida!");
                return;
            }
        }
        else if(cmd.length == 2){
            try {
                int astidx = Integer.parseInt(cmd[1]);
                int idx = Integer.parseInt(cmd[0]);
                space.getActors().get(idx).move(space.getAsteroids().get(astidx));
                return;
            } catch (MoveFailedException e) {
                System.out.println("Nem sikerült a mozgás, mert nem létezik ilyen indexű aszteroida!");
                return;
            } catch (NumberFormatException e){
                System.out.println("Nem létezik ilyen indexű actor!");
            }
        }
        System.out.println("Nem létezik ilyen indexű actor/asteroid!");
    }

    /**
     * A paraméterként kapott actor fúr, ha képes rá.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void drill(String[] cmd){
        if(cmd.length == 0) {
            if (currentActor == space.getActors().size()) {
                currentActor = 0;
            }
            ((iDrill) space.getActors().get(currentActor)).drill();
            currentActor++;
            return;
        } else if(cmd.length == 1) {
            try {
                int idx = Integer.parseInt(cmd[0]);
                if(idx < space.getActors().size() && idx >= 0) {
                    ((iDrill) space.getActors().get(idx)).drill();
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Nem létezik ilyen indexű actor.");
                return;
            }
        }
        System.out.println("Nem létezik ilyen indexű actor.");
    }

    /**
     * Build metódus, építést hajtja vége.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void build(String[] cmd) {
        if (cmd.length == 1 || cmd.length == 2) {
            if (cmd.length == 2) {
                try {
                    int curr = Integer.parseInt(cmd[1]);
                    if (curr >= 0 && curr < space.getActors().size()) {
                        currentActor = curr;
                    } else {
                        System.out.println("Nem létezik ilyen indexű actor.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Nem létezik ilyen indexű actor.");
                    return;
                }
                if (currentActor == space.getActors().size()) {
                    currentActor = 0;
                } else {
                    try {
                        Settler s = (Settler) space.getActors().get(currentActor);
                        switch (cmd[0]) {
                            case "Robot" -> s.craftRobot();
                            case "TpGate" -> s.craftGates();
                            case "Base" -> s.buildBase();
                        }
                    } catch (ClassCastException ex) {
                        System.out.println("Az actor nem tud építeni, mert nem telepes.");
                    }
                    currentActor++;
                }
            }
        }
    }


    /**
     * PutBack metódus, nyersanyag visszahelyezésre szolgál.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void putBack(String[] cmd){
        if(cmd.length == 1 || cmd.length == 2){
            if(cmd.length == 2){
                try{
                    int curr = Integer.parseInt(cmd[1]);
                    if(curr >= 0 && curr < space.getActors().size()){
                        currentActor = curr;
                    } else {
                        System.out.println("Nem létezik ilyen indexű actor.");
                        return;
                    }
                }catch(NumberFormatException ex){
                    System.out.println("Nem létezik ilyen indexű actor.");
                    return;
                }

            } else{
                if(currentActor == space.getActors().size()){
                    currentActor = 0;
                }
            }
            try{
                Settler s = (Settler)space.getActors().get(currentActor);
                int idx = -1;
                switch (cmd[0]){
                    case "Uran" -> idx = s.getMaterials().getUran();
                    case "Ice" -> idx = s.getMaterials().getIce();
                    case "Coal" -> idx = s.getMaterials().getCoal();
                    case "Iron" -> idx = s.getMaterials().getIron();
                }
                if(idx != -1){
                    s.putMaterialBack(s.getMaterials().getMaterials().get(idx));
                }else{
                    System.out.println("Nincs a telepesnél ilyen nyersanyag");
                }
            }catch(ClassCastException ex){
                System.out.println("Az actor nem tud nyersanyagot visszatenni, mert nem telepes.");
            }
            currentActor++;
        }
        else{
            System.out.println("Nem létezik ilyen indexű actor.");
        }
    }


    /**
     * Metódus teleportkapu lerakására.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void putTpGate(String[] cmd) {
        if(cmd.length == 0){
            if(currentActor == space.getActors().size()){
                currentActor = 0;
            }
            space.getActors().get(currentActor).putTpGateDown();
            currentActor++;
            return;
        }
        if(cmd.length == 1) {
            try {
                int idx = Integer.parseInt(cmd[0]);
                Settler s = (Settler)space.getActors().get(idx);
                s.putTpGateDown();
                return;
            } catch (ClassCastException ex) {
                System.out.println("Az actor nem tud teleportkaput letenni, mert nem telepes.");
            } catch (NumberFormatException e){
                System.out.println( "Nem létezik ilyen indexű actor.");
            }
        }
    }


    /**
     * Lementi a pálya aktuális állását.
     */
    public static void save(String[] cmd){
        serialize(cmd[0]);
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
            if(space.getAsteroids().get(index).getNeighbours().get(i).getClass().toString().equals("TpGate")) {
                System.out.println("tp " + space.getActors().indexOf(space.getAsteroids().get(index).getNeighbours().get(i)));
            }
            if(space.getAsteroids().get(index).getNeighbours().get(i).getClass().toString().equals("Asteroid")) {
                System.out.println("ast " + space.getAsteroids().indexOf(space.getAsteroids().get(index).getNeighbours().get(i)));
            }
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


    /**
     *
     */
    public static void createSpace(String[] cmd){
        space = new Space(0,0,10);
        System.out.println("Space létrehozva");

    }

    public static void add(String [] cmd){
        Supplier<Actor> actorSupplier = null;
        switch (cmd[0]){
            case "Asteroid"->{
                Material mat;
                switch (cmd[1]) {
                    case "Uran" -> mat = new Uran();
                    case "Ice" -> mat = new Ice();
                    case "Coal" -> mat = new Coal();
                    case "Iron" -> mat = new Iron();
                    default -> {
                        System.out.println("Nem sikerült az aszteroida hozzáadás, mert nem létező nyersanyagot adtunk meg.");
                        return;
                    }
                }
                space.addAsteroid(new Asteroid(3, 3, 3, mat));
                System.out.println("A nyersanyaggal teli aszteroida hozzá lett adva a space-hez.");
                return;
            }

            case "Settler"-> actorSupplier = Settler::new;

            case "Robot"-> actorSupplier = Robot::new;

            case "Ufo"-> actorSupplier = Ufo::new;

            case "TpGate"->{
                if(cmd.length == 5){
                    TpGate tp1 = new TpGate();
                    TpGate tp2 = new TpGate();
                    tp2.setLinkedTpGate(tp1);
                    tp1.setLinkedTpGate(tp2);
                    if(cmd[1].equals("Asteroid")){
                        int idx1 = Integer.parseInt(cmd[2]);
                        Asteroid a1;
                        if(idx1 >= 0 && idx1 < space.getAsteroids().size()){
                            a1 = space.getAsteroids().get(idx1);
                        } else {
                            System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                            return;
                        }
                        if(cmd[3].equals("Asteroid")){
                            int idx2 = Integer.parseInt(cmd[4]);
                            Asteroid a2;
                            if(idx2 >= 0 && idx2 < space.getAsteroids().size()){
                                a2 = space.getAsteroids().get(idx2);
                                a1.addActor(tp1);
                                a1.addNeighbour(tp1);
                                a2.addActor(tp2);
                                a2.addNeighbour(tp2);
                            } else {
                                System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                                return;
                            }
                        }
                        else if(cmd[3].equals("Settler")){
                            try{
                                int idx2 = Integer.parseInt(cmd[4]);
                                if(idx2 >= 0 && idx2 < space.getActors().size()){
                                    Settler s2 = (Settler)space.getActors().get(idx2);
                                    a1.addActor(tp1);
                                    a1.addNeighbour(tp1);
                                    s2.addTpGate(tp2);
                                    tp2.setInSettler(s2);
                                } else {
                                    System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                                    return;
                                }
                            }catch(NumberFormatException ex){
                                System.out.println("Nem létezik ilyen indexű actor.");
                                return;
                            }
                        }
                        System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                        return;
                    }
                    else if(cmd[1].equals("Settler")){
                        try{
                            int idx1 = Integer.parseInt(cmd[2]);
                            Settler s1;
                            if(idx1 >= 0 && idx1 < space.getActors().size()){
                                s1 = (Settler)space.getActors().get(idx1);
                            } else {
                                System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                                return;
                            }
                            if(cmd[3].equals("Asteroid")){
                                try{
                                    int idx2 = Integer.parseInt(cmd[4]);
                                    if(idx2 >= 0 && idx2< space.getAsteroids().size()){
                                        Asteroid a2 = space.getAsteroids().get(idx2);
                                        if(s1.getTpGates().size() < 3){
                                            s1.addTpGate(tp1);
                                            tp1.setInSettler(s1);
                                            a2.addNeighbour(tp2);
                                            a2.addActor(tp2);
                                        }
                                        else{
                                            System.out.println("Nincs hely a telepesnél a teleportkapunak.");
                                            return;
                                        }
                                    } else {
                                        System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                                        return;
                                    }
                                }catch(NumberFormatException ex){
                                    System.out.println("Nem létezik ilyen indexű actor.");
                                    return;
                                }
                            }else if(cmd[3].equals("Settler")){
                                try{
                                    int idx2 = Integer.parseInt(cmd[4]);
                                    if(idx2 >= 0 && idx2< space.getActors().size()){
                                        Settler s2 = (Settler)space.getActors().get(idx2);
                                        if(s1.getTpGates().size() < 3 && s2.getTpGates().size() < 3){
                                            s1.addTpGate(tp1);
                                            tp1.setInSettler(s1);
                                            s2.addTpGate(tp2);
                                            tp2.setInSettler(s2);
                                        }
                                        else{
                                            System.out.println("Nincs hely a telepesnél a teleportkapunak.");
                                            return;
                                        }
                                    } else {
                                        System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                                        return;
                                    }
                                }catch(NumberFormatException ex){
                                    System.out.println("Nem létezik ilyen indexű actor.");
                                    return;
                                }
                            }
                        }catch(NumberFormatException ex){
                            System.out.println("Nem létezik ilyen indexű actor.");
                            return;
                        }

                        System.out.println("Hibás index miatt nem sikerült a teleportkapu hozzáadás.");
                        return;
                    }
                    space.addActor(tp1);
                    space.addActor(tp2);
                    System.out.println("Hozzáadtuk a teleportkapukat a célpontokhoz.");
                }
            }

            case "Material"->{
                if(cmd.length == 3){
                    switch(cmd[2]){
                        case "Iron"-> space.getActors().get(Integer.parseInt(cmd[1])).getMaterials().addMaterial(new Iron());
                        case "Coal"-> space.getActors().get(Integer.parseInt(cmd[1])).getMaterials().addMaterial(new Coal());
                        case "Uran"-> space.getActors().get(Integer.parseInt(cmd[1])).getMaterials().addMaterial(new Uran());
                        case "Ice"-> space.getActors().get(Integer.parseInt(cmd[1])).getMaterials().addMaterial(new Ice());
                    }
                    System.out.println("Sikeresen betettük a nyersanyagot a telepesbe");
                }
                else{
                    System.out.println("Nem létező indexű telepest adtunk meg.");
                }
            }
        }
        if(actorSupplier != null){
            int astidx = Integer.parseInt(cmd[1]);
            if(astidx< 0 || astidx>= space.getAsteroids().size()){
                System.out.println("Nem létező indexű aszteroidát adtunk meg.");
                return;
            }
            Actor actor = actorSupplier.get();
            space.addActor(actor);
            space.getAsteroids().get(astidx).addActor(actor);
        }

    }


    /**
     * 2 IAsteroid-ot szomszédossá tesz.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void neighbour(String[] cmd){
        if(cmd.length == 2){
            if(cmd[0].equals(cmd[1])){
                System.out.println("Nem sikerült a szomszéddátétel, mert a két aszteroida megegyezik.");
                return;
            }
            try {
                int idx1 = Integer.parseInt(cmd[0]);
                int idx2 = Integer.parseInt(cmd[1]);
                space.getAsteroids().get(idx1).addNeighbour(space.getAsteroids().get(idx2));
                space.getAsteroids().get(idx2).addNeighbour(space.getAsteroids().get(idx1));
                return;
            } catch (NumberFormatException e) {
                System.out.println("Nem létező indexű aszteroidákat adtunk meg!");
                return;
            }
        }
        System.out.println("Nem létező indexű aszteroidákat adtunk meg!");
    }


    /**
     * Az egyik objektum egyik attribútumát átállítjuk a kívánt értékre.
     * @param cmd Parancs maradék része. Egy integer vagy egy üres integer tömb.
     */
    public static void mod(String[] cmd){
        switch (cmd[0]){
            case "Counter":
                if(cmd.length == 2){
                    int vl = Integer.parseInt(cmd[1]);
                    space.setTurnsTillSunStorm(vl);
                    return;
                }
                if(cmd.length == 3){
                    try{
                        int idx = Integer.parseInt(cmd[1]);
                        int vl = Integer.parseInt(cmd[2]);
                        space.getAsteroids().get(idx).setTurnsTillCloseToSun(vl);
                        return;
                    } catch(NumberFormatException e){
                        System.out.println("Hibás aszteroida indexet adtunk meg.");
                        return;
                    }
                }
            case "Layer":
                if(cmd.length == 3){
                    try {
                        int idx = Integer.parseInt(cmd[1]);
                        int vl = Integer.parseInt(cmd[2]);
                        space.getAsteroids().get(idx).setLayer(vl);
                        return;
                    } catch (NumberFormatException e){
                        System.out.println("Hibás aszteroida indexet adtunk meg.");
                        return;
                    }
                }
            case "Core":
                if(cmd.length == 3){
                    try {
                        int idx = Integer.parseInt(cmd[1]);
                        if(cmd[2].equals("coal")) {
                            space.getAsteroids().get(idx).setCoreMaterial(new Coal());
                        }
                        if(cmd[2].equals("ice")) {
                            space.getAsteroids().get(idx).setCoreMaterial(new Ice());
                        }
                        if(cmd[2].equals("iron")) {
                            space.getAsteroids().get(idx).setCoreMaterial(new Iron());
                        }
                        if(cmd[2].equals("uran")) {
                            space.getAsteroids().get(idx).setCoreMaterial(new Uran());
                        }
                        return;
                    } catch (NumberFormatException e){
                        System.out.println("Hibás aszteroida indexet adtunk meg.");
                        return;
                    }
                }
            case "Activated":
                if(cmd.length == 3){
                    try {
                        int idx = Integer.parseInt(cmd[1]);
                        boolean vl = Boolean.parseBoolean(cmd[2]);
                        ((TpGate)space.getActors().get(idx)).setActivated(vl);
                        return;
                    } catch (NumberFormatException e){
                        System.out.println("Hibás actor indexet adtunk meg.");
                        return;
                    }
                }
            default:
                break;
        }
    }
}
