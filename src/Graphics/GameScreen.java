package Graphics;


import modell.*;

import javax.swing.*;


public class GameScreen extends JFrame {

    Space space;
    int currentActor;
    JComboBox<String> info;
    JComboBox<String> act;
    JLabel infoLabel;
    DrawGame gameDraw;
    boolean settlerActing; //??
    String[] infoCB = {"Space","Settler","Asteroid","Neighbour"};
    String[] actCB = {"Build Teleport Gate","Build Robot","Build Base","Drill","Mine","Move","Put Material Back","Put Teleport gate down"};


    public GameScreen(Space spaceField) {
        super("SpaceGame");
        space = spaceField;
        currentActor = 0;
        info = new JComboBox<>(infoCB);
        act =  new JComboBox<>(actCB);
        infoLabel = new JLabel();
        settlerActing = false;
        createDefaultSettings();
        gameDraw = new DrawGame(space);
        add(gameDraw);
        gameDraw.repaint();
        setVisible(true);
        gameDraw.repaint();
    }

    public void createDefaultSettings() {
        //Setting the default look.
        setResizable(false);
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


}
