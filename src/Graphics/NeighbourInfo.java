package Graphics;

import modell.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NeighbourInfo extends JFrame {
    private JTextField t1 = new JTextField(4);
    private JTextField t2 = new JTextField(4);
    private JTextField t3 = new JTextField(4);
    private JTextField t4 = new JTextField(4);
    private JTextField t5 = new JTextField(4);
    private JTextField t6 = new JTextField(4);
    private JTextField t7 = new JTextField(4);

    private JButton list = new JButton("List");
    private JComboBox<Integer> neighbours = new JComboBox<>();
    private Asteroid ast;
    private Asteroid pickedAst;

    public NeighbourInfo(Asteroid ast){
        this.ast = ast;
        this.pickedAst = null;

        JPanel panel = new JPanel();
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());

        JPanel p = new JPanel(new FlowLayout());
        JPanel p0 = new JPanel(new FlowLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p4 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());
        JPanel p7 = new JPanel(new FlowLayout());

        JLabel l = new JLabel("Choose a neighbour:");
        JLabel l0 = new JLabel("Asteroid informations:");
        JLabel l1 = new JLabel("Layer:");
        JLabel l2 = new JLabel("Core material:");
        JLabel l3 = new JLabel("Number of settlers on surface:");
        JLabel l4 = new JLabel("Number of robots on surface:");
        JLabel l5 = new JLabel("Number of UFOs on surface:");
        JLabel l6 = new JLabel("Turns till the asteroid gets close to sun:");
        JLabel l7 = new JLabel("Number of teleport gates on surface:");

        Integer[] neigh = new Integer[ast.neighborCount()];

        for(int i = 0; i < ast.neighborCount(); i++) {
            neigh[i] = i;
        }

        neighbours = new JComboBox(neigh);

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

        p.add(l);
        p.add(neighbours);
        p.add(list);

        p0.add(l0);

        p1.add(l1);
        p2.add(l2);
        p3.add(l3);
        p4.add(l4);
        p5.add(l5);
        p6.add(l6);
        p7.add(l7);

        p1.add(t1);
        p2.add(t2);
        p3.add(t3);
        p4.add(t4);
        p5.add(t5);
        p6.add(t6);
        p7.add(t7);

        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        pan.add(p);
        pan.add(p0);
        pan.add(p1);
        pan.add(p2);
        pan.add(p3);
        pan.add(p4);
        pan.add(p5);
        pan.add(p6);
        pan.add(p7);

        panel.add(pan);

        this.add(panel);

        this.setTitle("Neighbour Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 330);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public class ListButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("List")) {
                Integer settlercnt = 0;
                Integer ufocnt = 0;
                Integer robotcnt = 0;
                Integer tpgatecnt = 0;
                //TODO: Remélhetőléeg a getneighbour nem tér vissza olyan tpgate-el aminek a párja nincs lent
                if(ast.getNeighbours().get((int)neighbours.getSelectedItem()) instanceof Asteroid){
                    pickedAst = (Asteroid)ast.getNeighbours().get((int)neighbours.getSelectedItem());
                }else{
                    pickedAst = ((TpGate)ast.getNeighbours().get((int)neighbours.getSelectedItem())).getLinkedTpGate().getCurrentAsteroid();
                }
                for (Actor a: pickedAst.getActorsOnSurface()) {
                    if(a instanceof Settler)
                        settlercnt += 1;
                    if(a instanceof modell.Robot)
                        robotcnt += 1;
                    if(a instanceof Ufo)
                        ufocnt += 1;
                    if(a instanceof TpGate)
                        tpgatecnt += 1;
                }

                t1.setText(Integer.toString(pickedAst.getLayer()));
                t2.setText(pickedAst.getCoreMaterial().getType().name());
                t3.setText(settlercnt.toString());
                t4.setText(robotcnt.toString());
                t5.setText(ufocnt.toString());
                t6.setText(Integer.toString(pickedAst.getCloseToSunFreq()));
                t7.setText(tpgatecnt.toString());
            }
        }
    }
}

