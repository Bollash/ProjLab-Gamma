package Graphics;

import modell.Space;
import javax.swing.*;
import java.awt.*;

public class SpaceInfo extends JFrame {
    private JFrame f = new JFrame("SpaceInfo");

    private JTextField t1 = new JTextField(20);
    private JTextField t2 = new JTextField(20);
    private JTextField t3 = new JTextField(20);
    private JTextField t4 = new JTextField(20);
    private JTextField t5 = new JTextField(20);
    private JTextField t6 = new JTextField(20);

    public SpaceInfo(Space space){
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        JLabel l0 = new JLabel("Space informations:");
        JLabel l1 = new JLabel("Number of asteroids:");
        JLabel l2 = new JLabel("Number of settlers:");
        JLabel l3 = new JLabel("Number of robots:");
        JLabel l4 = new JLabel("Number of UFOs:");
        JLabel l5 = new JLabel("Number of teleport gates:");
        JLabel l6 = new JLabel("Turns till next sunstorm:");

        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(false);

        /*t1.setText();
        t2.setText();
        t3.setText();
        t4.setText();
        t5.setText();
        t6.setText();
        t7.setText();*/

        p1.add(l0);

        p2.add(l1);
        p2.add(l2);
        p2.add(l3);
        p2.add(l4);
        p2.add(l5);
        p2.add(l6);

        p3.add(t1);
        p3.add(t2);
        p3.add(t3);
        p3.add(t4);
        p3.add(t5);
        p3.add(t6);

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.WEST);
        f.add(p3, BorderLayout.EAST);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 110);
        f.setResizable(false);
        f.pack();
        f.setVisible(true);
    }
}
