package Graphics;

import modell.*;

import javax.swing.*;
import java.awt.*;

/**
 * SpaceInfo segítségével tudjuk megnézni a space-ünk információit
 */
public class SpaceInfo extends JFrame {
    private JTextField t1 = new JTextField(4);
    private JTextField t2 = new JTextField(4);
    private JTextField t3 = new JTextField(4);
    private JTextField t4 = new JTextField(4);
    private JTextField t5 = new JTextField(4);
    private JTextField t6 = new JTextField(4);

    /**
     * SpaceInfo grafikus megjelenítése
     * @param space a játék space-e, aminek az infóit nézzük
     */
    public SpaceInfo(Space space){
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel p = new JPanel();

        JPanel p0 = new JPanel(new FlowLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p4 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());

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

        int settlercnt = 0;
        int ufocnt = 0;
        int robotcnt = 0;
        int tpgatecnt = 0;
        for (Actor a: space.getActors()) {
            if(a instanceof Settler)
                settlercnt += 1;
            if(a instanceof modell.Robot)
                robotcnt += 1;
            if(a instanceof Ufo)
                ufocnt += 1;
            if(a instanceof TpGate)
                tpgatecnt += 1;
        }

        t1.setText(Integer.toString(space.getAsteroids().size()));
        t2.setText(Integer.toString(settlercnt));
        t3.setText(Integer.toString(robotcnt));
        t4.setText(Integer.toString(ufocnt));
        t5.setText(Integer.toString(tpgatecnt));
        t6.setText(Integer.toString(space.getTurnsTillSunStorm()));

        p0.add(l0);

        p1.add(l1);
        p2.add(l2);
        p3.add(l3);
        p4.add(l4);
        p5.add(l5);
        p6.add(l6);

        p1.add(t1);
        p2.add(t2);
        p3.add(t3);
        p4.add(t4);
        p5.add(t5);
        p6.add(t6);

        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.add(p0);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.add(p6);

        panel.add(p);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        this.setTitle("Space Info");
        this.setSize(350, 285);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
