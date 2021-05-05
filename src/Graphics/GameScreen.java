package Graphics;

import modell.Space;

import javax.swing.*;

public class GameScreen extends JFrame {

    Space space;
    int currentActor;
    JComboBox<String> info;
    JComboBox<String> act;
    JLabel infoLabel;
    boolean settlerActing; //??
    String[] infoCB = {"Space","Settler","Asteroid","Neighbour"};
    String[] actCB = {"Build Teleport Gate","Build Robot","Build Base","Drill","Mine","Move","Put Material Back","Put Teleport gate down"};






    public GameScreen(Space spaceField) {
        space = spaceField;
        currentActor = 0;
        info = new JComboBox<>(infoCB);
        act =  new JComboBox<>(actCB);
        infoLabel = new JLabel();
        settlerActing = false;

    }
}
