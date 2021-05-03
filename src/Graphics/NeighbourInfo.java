package Graphics;

import modell.Asteroid;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NeighbourInfo extends JFrame {
    private JFrame f = new JFrame("NeighbourInfo");

    private JTextField t1 = new JTextField(20);
    private JTextField t2 = new JTextField(20);
    private JTextField t3 = new JTextField(20);
    private JTextField t4 = new JTextField(20);
    private JTextField t5 = new JTextField(20);
    private JTextField t6 = new JTextField(20);
    private JTextField t7 = new JTextField(20);

    private JButton list = new JButton("List");
    private JComboBox neighbours = new JComboBox();

    public NeighbourInfo(Asteroid ast){
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        JLabel l = new JLabel("Choose a neighbour:");
        JLabel l0 = new JLabel("Asteroid informations:");
        JLabel l1 = new JLabel("Layer:");
        JLabel l2 = new JLabel("Core material:");
        JLabel l3 = new JLabel("Number of settlers on surface:");
        JLabel l4 = new JLabel("Number of robots on surface:");
        JLabel l5 = new JLabel("Number of UFOs on surface:");
        JLabel l6 = new JLabel("Turns till the asteroid gets close to sun:");
        JLabel l7 = new JLabel("Number of teleport gates on surface:");

        Object obj[] = new Object[ast.neighborCount()];

        for(int i = 0; i < 26; i++) {
            obj[i] = /*"Neighbour" + (char)*/ ast.getNeighbours().get(i);
        }

        neighbours = new JComboBox(obj);

        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(false);
        t7.setEditable(false);

        list.setActionCommand("List");
        ActionListener al = new ListButtonActionListener();
        list.addActionListener(al);

        p1.add(l);
        p1.add(list);
        p1.add(neighbours);
        p1.add(l0);

        p2.add(l1);
        p2.add(l2);
        p2.add(l3);
        p2.add(l4);
        p2.add(l5);
        p2.add(l6);
        p2.add(l7);

        p3.add(t1);
        p3.add(t2);
        p3.add(t3);
        p3.add(t4);
        p3.add(t5);
        p3.add(t6);
        p3.add(t7);

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.WEST);
        f.add(p3, BorderLayout.EAST);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 110);
        f.setResizable(false);
        f.pack();
        f.setVisible(true);
    }

    public class ListButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("List")) {
            /*t1.setText();
            t2.setText();
            t3.setText();
            t4.setText();
            t5.setText();
            t6.setText();
            t7.setText();*/
            }
        }
    }
}

