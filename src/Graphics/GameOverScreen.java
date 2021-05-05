package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOverScreen extends JFrame {

    public GameOverScreen(String message){
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());

        JLabel l = new JLabel(message);
        l.setFont(new Font("arial", Font.BOLD, 20));
        p.add(l);
        p2.add(p);

        this.add(p2);
        this.setName("Game Over");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        this.setResizable(false);
        this.setVisible(true);
    }
}
