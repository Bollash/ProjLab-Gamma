package command;

import modell.Space;
import modell.exceptions.NoDrillableNeighbourException;

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
}
