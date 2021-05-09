package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen extends JFrame {
    /**
     * Főmenü megjelenítéséért felelős ablak
     */
    public MainMenuScreen(){
        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameButtonActionListener(this));

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new LoadGameButtonActionListener(this));

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ExitButtonActionListener());

        panel.add(newGameButton);
        panel.add(loadGameButton);
        panel.add(exit);
        p2.add(panel);

        this.setTitle("Main menu");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(p2);
        this.setSize(new Dimension(300, 100));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * New Button nyomásakor lezajló művelet
     */
    private class NewGameButtonActionListener implements ActionListener {
        private JFrame frame;

        public NewGameButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new NewGameScreen();
            frame.dispose();
        }
    }

    /**
     * Save Button nyomásakor lezajló művelet
     */
    private class LoadGameButtonActionListener implements ActionListener {
        private JFrame frame;

        public LoadGameButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new LoadGameScreen();
            frame.dispose();
        }
    }

    /**
     * Exit Button nyomásakor lezajló művelet
     */
    private class ExitButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
