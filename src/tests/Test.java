package tests;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import command.Comms;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if(n == 0){
            gen();
            eval();
        }else if(n > 0 && n <= 45){
            deleteFiles("outputs");
            runtest(n);
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            if(compareTextFiles("outputs\\" + n + ".txt", "expected_outputs\\" + n + ".txt")) {
                System.out.println(n + ". test passed");
            }
            else{
                System.out.println(n + ". test NOT passed");
            }
        }
    }

    public static void deleteFiles(String path){
        File dir = new File(path);
        for(File file: Objects.requireNonNull(dir.listFiles()))
            if (!file.isDirectory())
                file.delete();
    }

    public static void runtest(int num){
        try {
            File output = new File("outputs\\" + num + ".txt");
            Comms.cmdProg(new FileInputStream("inputs\\" + num + ".txt"), new PrintStream(output));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testeval() throws IOException {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        File dir = new File("outputs");
        for(int i = 1; i <= Objects.requireNonNull(dir.listFiles()).length; i++){
            if(compareTextFiles("outputs\\" + i + ".txt", "expected_outputs\\" + i + ".txt")){
                System.out.println(i + ". test passed");
            }else{
                System.out.println(i + ". test NOT passed");
            }
        }
    }

    static boolean compareTextFiles ( String file1, String file2) throws FileNotFoundException, IOException{
        BufferedReader r1 = new BufferedReader(new FileReader(file1));
        BufferedReader r2 = new BufferedReader(new FileReader(file2));
        int c1=0, c2=0;
        while(true){
            c1 = r1.read();
            c2 = r2.read();
            if(c1==-1 && c2==-1)
                return true;
            else if(c2 == -1 || c1 != c2){
                return false;
            }
        }
    }
    public static void gen(){
        deleteFiles("outputs");
        for(int i = 1; i < 46; i++){
            runtest(i);
        }
    }
    public static void eval(){
        try {
            testeval();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
