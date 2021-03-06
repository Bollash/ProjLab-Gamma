package Graphics;

import modell.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * NeighbourInfo-n a kiválasztott szomszédos aszteroida információit megtekinthetjük
 */
public class NeighbourInfo extends JFrame {
    private JTextField t0 = new JTextField(4);
    private JTextField t1 = new JTextField(4);
    private JTextField t2 = new JTextField(4);
    private JTextField t3 = new JTextField(4);
    private JTextField t4 = new JTextField(4);
    private JTextField t5 = new JTextField(4);
    private JTextField t6 = new JTextField(4);
    private JTextField t7 = new JTextField(4);

    private JButton list = new JButton("List");
    private JComboBox<Integer> neighbours;
    private Asteroid ast;
    private Asteroid pickedAst;

    /**
     * NeighbourInfo konstruktora, inicializálja a NeighbourInfo ablakot
     * @param ast az aktuális aszteroida, amelyiken állunk
     */
    public NeighbourInfo(Asteroid ast){
        this.ast = ast;
        this.pickedAst = null;

        JPanel panel = new JPanel(new GridBagLayout());
        JPanel pan = new JPanel();

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
        JLabel l0 = new JLabel("Asteroid id:");
        JLabel l1 = new JLabel("Layer:");
        JLabel l2 = new JLabel("Core material:");
        JLabel l3 = new JLabel("Number of settlers on surface:");
        JLabel l4 = new JLabel("Number of robots on surface:");
        JLabel l5 = new JLabel("Number of UFOs on surface:");
        JLabel l6 = new JLabel("Turns till the asteroid gets close to sun:");
        JLabel l7 = new JLabel("Number of teleport gates on surface:");

        Integer[] neigh = new Integer[ast.neighborCount()];

        for(int i = 0; i < ast.getValidNeighbours().size(); i++) {
            neigh[i] = i;
        }

        neighbours = new JComboBox<>(neigh);

        t0.setEditable(false);
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

        p0.add(t0);
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
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        this.setTitle("Neighbour Info");
        this.setSize(370, 330);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * A List gomb hatását definiálja, vagyis ha rányomunk a list-re kilistázzuk a kiválasztott aszteroida adatait
     */
    public class ListButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int settlercnt = 0;
            int ufocnt = 0;
            int robotcnt = 0;
            int tpgatecnt = 0;
            if(neighbours.getSelectedItem() != null){
                if(ast.getValidNeighbours().get((int)neighbours.getSelectedItem()) instanceof Asteroid){
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

                t0.setText(Integer.toString(pickedAst.getSpace().getAsteroids().indexOf(ast)));
                t1.setText(Integer.toString(pickedAst.getLayer()));
                if (ast.getCoreMaterial() != null) {
                    t2.setText(pickedAst.getCoreMaterial().getType().name());
                }else{
                    t2.setText("Nothing");
                }
                t3.setText(Integer.toString(settlercnt));
                t4.setText(Integer.toString(robotcnt));
                t5.setText(Integer.toString(ufocnt));
                t6.setText(Integer.toString(pickedAst.getTurnsTillCloseToSun()));
                t7.setText(Integer.toString(tpgatecnt));
            }
        }
    }
}

