package Graphics;

import javax.swing.*;

public class GameOverScreen extends JFrame {
    private JFrame f = new JFrame("SpaceInfo");

    public GameOverScreen(String message){
        JPanel p = new JPanel();

        JLabel l = new JLabel(message);

        p.add(l);

        f.add(p);



        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 110);
        f.setResizable(false);
        f.pack();
        f.setVisible(true);
    }
}
