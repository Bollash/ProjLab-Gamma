package tests;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import command.Comms;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        while(true){
            String input = in.next();
            deleteFiles("outputs");
            try {
                File output = new File("outputs\\" + input + ".txt");
                Comms.cmdProg(new FileInputStream("inputs\\" + input + ".txt"), new PrintStream(output));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFiles(String path){
        File dir = new File(path);
        for(File file: Objects.requireNonNull(dir.listFiles()))
            if (!file.isDirectory())
                file.delete();
    }
}
