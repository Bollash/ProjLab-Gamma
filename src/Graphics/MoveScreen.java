package Graphics;

import modell.IAsteroid;
import modell.Space;
import modell.exceptions.MoveFailedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MoveScreen extends JFrame {

    private Space space;
    private List<IAsteroid> iAsteroids;
    private JComboBox<Integer> comboBox;

    public MoveScreen(Space space){
        this.space = space;
        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());
        iAsteroids = space.getActors().get(space.getCurrentActor()).getCurrentAsteroid().getNeighbours();
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
    }

    private class MoveButtonActionListener implements ActionListener{

        JFrame frame;

        public MoveButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if((int)comboBox.getSelectedItem() != -1){
                try {
                    iAsteroids.get((int)comboBox.getSelectedItem()).addActor(space.getActors().get(space.getCurrentActor()));
                } catch (MoveFailedException moveFailedException) {
                    moveFailedException.printStackTrace();
                }
            }
            frame.dispose();
            new GameScreen(space);
        }
    }

    private class BackButtonActionListener implements ActionListener {

        JFrame frame;

        public BackButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new GameScreen(space);
        }
    }
}
