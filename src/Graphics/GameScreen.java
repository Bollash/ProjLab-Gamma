package Graphics;


import modell.*;
import modell.exceptions.CantBeMinedException;
import modell.exceptions.LayerNot0Exception;
import modell.exceptions.SettlerActingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameScreen extends JFrame {

    Space space;
    JComboBox<String> info;
    JComboBox<String> act;
    JTextField infoLabel;
    DrawGame gameDraw;
    String[] infoCB = {"Space","Asteroid","Neighbour"};
    String[] actCB = {"Build Teleport Gate","Build Robot","Build Base","Drill","Mine","Move","Put Material Back","Put Teleport gate down"};


    public GameScreen(Space spaceField) {
        super("SpaceGame");
        space = spaceField;
        info = new JComboBox<>(infoCB);
        JButton infoButton = new JButton("Info");
        infoButton.addActionListener(new InfoButtonActionListener());

        JPanel tempPanel = new JPanel(new FlowLayout());

        act =  new JComboBox<>(actCB);
        JButton actButton = new JButton("Act");
        actButton.addActionListener(new ActButtonActionListener(this));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonActionListener());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonActionListener(this));

        JLabel id = new JLabel("Settler id:");
        infoLabel = new JTextField(3);
        infoLabel.setEditable(false);
        createDefaultSettings();

        tempPanel.add(info);
        tempPanel.add(infoButton);

        tempPanel.add(id);
        tempPanel.add(infoLabel);

        tempPanel.add(act);
        tempPanel.add(actButton);

        tempPanel.add(saveButton);
        tempPanel.add(exitButton);

        gameDraw = new DrawGame(space);
        gameDraw.add(tempPanel);
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

    public void actOne(){
        while(!space.isGameOver()){
            try {
                //új kör kezdődik
                if(space.getCurrentActor() == space.getActors().size()){
                    space.setCurrentActor(0);
                    space.handleCountDown();
                    for(Asteroid ast : space.getAsteroids()){
                        ast.handleCountDown();
                    }
                }
                if(space.isGameOver()){
                    handleGameOver();
                }
                space.getActors().get(space.getCurrentActor()).act();
            } catch (SettlerActingException e) {
                infoLabel.setText(Integer.toString(space.getCurrentActor()));
                break;
            }
        }
        if(space.isGameOver()){
            handleGameOver();
        }
    }

    private void handleGameOver() {
        if(space.getAliveSettlerCnt() >= 2){
            new GameOverScreen("Victory");
        }else{
            new GameOverScreen("Defeat");
        }
        this.dispose();
    }

    private class InfoButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch((String) info.getSelectedItem()){
                case "Space":
                    new SpaceInfo(space);
                    break;
                case "Asteroid":
                    new AstInfo(space.getActors().get(space.getCurrentActor()).getCurrentAsteroid());
                    break;
                case "Neighbour":
                    new NeighbourInfo(space.getActors().get(space.getCurrentActor()).getCurrentAsteroid());
            }
        }
    }

    private class ActButtonActionListener implements ActionListener{

        GameScreen screen;

        public ActButtonActionListener(GameScreen screen){
            this.screen = screen;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //{"Build Teleport Gate","Build Robot","Build Base","Drill","Mine","Move","Put Material Back","Put Teleport gate down"}
            switch((String) act.getSelectedItem()){
                case "Build Teleport Gate":
                    ((Settler)space.getActors().get(space.getCurrentActor())).craftGates();
                    screen.actOne();
                    screen.repaint();
                    break;
                case "Build Robot":
                    ((Settler)space.getActors().get(space.getCurrentActor())).craftRobot();
                    screen.actOne();
                    screen.repaint();
                    break;
                case "Build Base":
                    ((Settler)space.getActors().get(space.getCurrentActor())).buildBase();
                    if(space.isGameOver()){
                        handleGameOver();
                    }
                    screen.actOne();
                    screen.repaint();
                    break;
                case "Drill":
                    ((Settler)space.getActors().get(space.getCurrentActor())).drill();
                    screen.actOne();
                    screen.repaint();
                    break;
                case "Mine":
                    ((Settler)space.getActors().get(space.getCurrentActor())).mine();
                    screen.actOne();
                    screen.repaint();
                    break;
                case "Move":
                    new MoveScreen(space, screen);
                    screen.setVisible(false);
                    break;
                case "Put Material Back":
                    new PutBackMaterial(space, screen);
                    screen.setVisible(false);
                    break;
                case "Put Teleport Gate Down":
                    space.getActors().get(space.getCurrentActor()).putTpGateDown();
                    screen.actOne();
                    screen.repaint();
                    break;

            }
        }
    }

    private class SaveButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new SaveGameScreen(space);
        }
    }

    private class ExitButtonActionListener implements ActionListener{

        private GameScreen screen;

        public ExitButtonActionListener(GameScreen screen){
            this.screen = screen;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            screen.dispose();
            new MainMenuScreen();
        }
    }


}
