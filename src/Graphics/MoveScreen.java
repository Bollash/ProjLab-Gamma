package Graphics;

import modell.IAsteroid;
import modell.Space;
import modell.exceptions.MoveFailedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * MoveScreen-en választhatjuk ki, hogy melyik aszteroidára mozogjunk
 */
public class MoveScreen extends JFrame {

    private Space space;
    private List<IAsteroid> iAsteroids;
    private JComboBox<Integer> comboBox;
    private GameScreen screen;

    /**
     * MoveScreen konstruktora, MoveScreeen-t inicializálja
     * @param space a játék space
     * @param screen a GameScreen
     */
    public MoveScreen(Space space, GameScreen screen){
        this.screen = screen;
        this.space = space;
        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());
        iAsteroids = space.getActors().get(space.getCurrentActor()).getCurrentAsteroid().getValidNeighbours();
        int n = iAsteroids.size();
        Integer[] list = new Integer[n];
        if(n > 0){
            for(int i = 0; i < n; i++){
                list[i] = i;
            }
        }else{
            list = new Integer[]{-1};
        }
        comboBox = new JComboBox<>(list);

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(new MoveButtonActionListener(this));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonActionListener(this));

        panel.add(comboBox);
        panel.add(moveButton);
        panel.add(backButton);
        p2.add(panel);

        this.setTitle("Move");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(p2);
        this.setSize(new Dimension(300, 100));
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * A Move gomb hatását definiálja, vagyis ha rányomunk a move-ra mozgunk a kiválasztott aszteroidára
     */
    private class MoveButtonActionListener implements ActionListener{

        JFrame frame;

        public MoveButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if((int)comboBox.getSelectedItem() != -1){
                try {
                    space.getActors().get(space.getCurrentActor()).move(iAsteroids.get((int)comboBox.getSelectedItem()));
                } catch (MoveFailedException moveFailedException) {
                    moveFailedException.printStackTrace();
                }
            }
            frame.dispose();
            screen.actOne();
            screen.repaint();
            screen.setVisible(true);
        }
    }

    /**
     * A Back gomb hatását definiálja, vagyis ha rányomunk a back-re visszaugrunk a GameScreen-re
     */
    private class BackButtonActionListener implements ActionListener {

        JFrame frame;

        public BackButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            screen.repaint();
            screen.setVisible(true);
        }
    }
}
