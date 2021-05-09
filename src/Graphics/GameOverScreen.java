package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameOverScreen extends JFrame {

    /**
     * Játék végét jelző ablak
     * @param message Ezt a szöveget írjuk a képernyőre
     */
    public GameOverScreen(String message){
        super("Game over");
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());

        JLabel l = new JLabel(message);
        l.setFont(new Font("arial", Font.BOLD, 20));
        p.add(l);
        p2.add(p);
        p2.setBackground(new Color(147, 153, 208));
        p.setBackground(new Color(147, 153, 208));
        this.add(p2);
        this.setName("Game Over");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
