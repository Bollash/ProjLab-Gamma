package Graphics;

import modell.Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A NewGameScreen-en választhatjuk ki, hogy a játék hogyan inicializálódjon, vagyis miből mennyit szeretnénk
 */
public class NewGameScreen extends JFrame {

    private JTextField settlerCnt;
    private JTextField ufoCnt;
    private JTextField sameAstCnt;
    private JTextField solarWindFreq;

    /**
     * NewGameScreen konstruktora, inicializáljuk a NewGameScreen-t
     */
    public NewGameScreen(){
        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());

        JLabel set = new JLabel("Enter the number of players:");
        settlerCnt = new JTextField(4);
        settlerCnt.setText("10");

        JLabel ufo = new JLabel("Enter the number of UFOs:");
        ufoCnt = new JTextField(4);
        ufoCnt.setText("3");

        JLabel astCnt = new JLabel("Enter how much asteroid will be holding a certain type of material");
        sameAstCnt = new JTextField(4);
        sameAstCnt.setText("10");

        JLabel solarWind  = new JLabel("Enter the solar wind frequency");
        solarWindFreq = new JTextField(4);
        solarWindFreq.setText("15");

        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(new StartGameButtonActionListener(this));

        JButton back = new JButton("Back");
        back.addActionListener(new BackButtonActionListener(this));

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

        p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
        p2.add(p3_1);
        p2.add(p3_2);
        p2.add(p3_3);
        p2.add(p3_4);
        p2.add(p3_5);

        panel.add(p2);

        this.setTitle("New Game");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            new MainMenuScreen();
        }
    }

    /**
     * A Star gomb hatását definiálja, vagyis ha rányomunk a start-ra elindul a játék, és a megadott értékekkel inicializálja azt
     */
    private class StartGameButtonActionListener implements ActionListener{

        JFrame frame;

        public StartGameButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Space space = new Space(Integer.parseInt(settlerCnt.getText()), Integer.parseInt(ufoCnt.getText()), Integer.parseInt(sameAstCnt.getText()), Integer.parseInt(solarWindFreq.getText()));
            GameScreen screen = new GameScreen(space);
            screen.actOne();
            screen.repaint();
            frame.dispose();
        }
    }
}
