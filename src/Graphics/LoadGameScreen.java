package Graphics;

import modell.Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoadGameScreen extends JFrame {

    private JComboBox<String> comboBox;
    private String filepath;

    public LoadGameScreen(){
        this.filepath = System.getProperty("user.dir") + "\\SaveGames";

        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());
        File folder = new File(filepath);
        String[] listOfFiles = folder.list();
        if(listOfFiles != null){
            this.comboBox = new JComboBox<>(listOfFiles);
        }
        else{
            String[] files = {"No saved games"};
            this.comboBox = new JComboBox<>(files);
        }

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new LoadButtonActionListener(this));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonActionListener(this));

        panel.add(comboBox);
        panel.add(loadButton);
        panel.add(backButton);
        p2.add(panel);

        this.setTitle("Load Game");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(p2);
        this.setSize(new Dimension(300, 100));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class LoadButtonActionListener implements ActionListener{

        private JFrame frame;

        public LoadButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String filename = filepath + "\\" + comboBox.getSelectedItem();

            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Space space = (Space) in.readObject();
                in.close();
                fileIn.close();

                frame.dispose();
                GameScreen screen = new GameScreen(space);
                screen.actOne();
                screen.repaint();

            } catch (ClassNotFoundException c) {
                System.out.println("Space class not found");
                c.printStackTrace();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    private class BackButtonActionListener implements ActionListener{

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
}
