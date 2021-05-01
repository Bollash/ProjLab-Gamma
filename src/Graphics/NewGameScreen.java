package Graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NewGameScreen extends JFrame {

    private JTextField settlerCnt;
    private JTextField ufoCnt;
    private JTextField sameAstCnt;
    private JTextField solarWindFreq;

    public NewGameScreen(){
        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());

        JLabel set = new JLabel("Enter the number of players:");
        settlerCnt = new JTextField(4);

        JLabel ufo = new JLabel("Enter the number of UFOs:");
        ufoCnt = new JTextField(4);

        JLabel astCnt = new JLabel("Enter how much asteroid will be holding a certain type of material");
        sameAstCnt = new JTextField(4);

        JLabel solarWind  = new JLabel("Enter the solar wind frequency");
        solarWindFreq = new JTextField(4);

        JButton startGame = new JButton("Start Game");

        JButton back = new JButton("Back");

        JPanel p3_1 = new JPanel(new FlowLayout());
        p3_1.add(set);
        p3_1.add(settlerCnt);

        JPanel p3_2 = new JPanel(new FlowLayout());
        p3_2.add(ufo);
        p3_2.add(ufoCnt);

        JPanel p3_3 = new JPanel(new FlowLayout());
        p3_3.add(astCnt);
        p3_3.add(sameAstCnt);

        JPanel p3_4 = new JPanel(new FlowLayout());
        p3_4.add(solarWind);
        p3_4.add(solarWindFreq);

        JPanel p3_5 = new JPanel(new FlowLayout());
        p3_5.add(startGame);
        p3_5.add(back);

        p2.setLayout(new BorderLayout());
        p2.add(p3_1);
        p2.add(p3_2);
        p2.add(p3_3);
        p2.add(p3_4);
        p2.add(p3_5);

        p2.add(panel);

        this.setTitle("New Game");
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.add(p2);
        this.pack();
        this.setVisible(true);
    }
}
