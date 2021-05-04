package Graphics;

import modell.Space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveGameScreen extends JFrame {
    private Space space;

    private JTextField name;

    public SaveGameScreen(Space space){
        this.space = space;

        JPanel panel = new JPanel();

        name = new JTextField(15);

        JButton save = new JButton("Save");
        save.addActionListener(new SaveButtonActionListener(this));

        panel.add(name);
        panel.add(save);

        this.add(panel);

        this.setTitle("Save Game");
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(300, 80));
        this.setResizable(false);
        this.setVisible(true);
    }

    private class SaveButtonActionListener implements ActionListener{

        JFrame frame;

        public SaveButtonActionListener(JFrame frame){
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String filename = System.getProperty("user.dir") + "\\SaveGames\\" + name.getText() + ".sav";
            File f = new File(filename);
            if(!f.exists()){
                try {
                    FileOutputStream fileOut =
                            new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(space);
                    out.close();
                    fileOut.close();
                    frame.dispose();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        }
    }
}
