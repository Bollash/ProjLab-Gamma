package Graphics;

import modell.*;

import javax.swing.*;
import java.awt.*;

public class AstInfo extends JFrame {

    /**
     * Asteroidifo grafikus megjelenítése
     * @param ast Az aszteroid amiről információkat adjuk
     */
    public AstInfo(Asteroid ast){
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel p = new JPanel();

        JPanel p0 = new JPanel(new FlowLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p4 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());
        JPanel p7 = new JPanel(new FlowLayout());

        JLabel l0 = new JLabel("Asteroid id:");
        JLabel l1 = new JLabel("Layer:");
        JLabel l2 = new JLabel("Core material:");
        JLabel l3 = new JLabel("Number of settlers on surface:");
        JLabel l4 = new JLabel("Number of robots on surface:");
        JLabel l5 = new JLabel("Number of UFOs on surface:");
        JLabel l6 = new JLabel("Turns till the asteroid gets close to sun:");
        JLabel l7 = new JLabel("Number of teleport gates on surface:");


        JTextField t0 = new JTextField(4);
        t0.setEditable(false);
        JTextField t1 = new JTextField(4);
        t1.setEditable(false);
        JTextField t2 = new JTextField(4);
        t2.setEditable(false);
        JTextField t3 = new JTextField(4);
        t3.setEditable(false);
        JTextField t4 = new JTextField(4);
        t4.setEditable(false);
        JTextField t5 = new JTextField(4);
        t5.setEditable(false);
        JTextField t6 = new JTextField(4);
        t6.setEditable(false);
        JTextField t7 = new JTextField(4);
        t7.setEditable(false);

        int settlercnt = 0;
        int ufocnt = 0;
        int robotcnt = 0;
        int tpgatecnt = 0;
        for (Actor a: ast.getActorsOnSurface()) {
            if(a instanceof Settler)
                settlercnt += 1;
            if(a instanceof modell.Robot)
                robotcnt += 1;
            if(a instanceof Ufo)
                ufocnt += 1;
            if(a instanceof TpGate)
                tpgatecnt += 1;
        }

        t0.setText(Integer.toString(ast.getSpace().getAsteroids().indexOf(ast)));
        t1.setText(Integer.toString(ast.getLayer()));
        if (ast.getCoreMaterial() != null) {
            t2.setText(ast.getCoreMaterial().getType().name());
        }else{
            t2.setText("Nothing");
        }

        t3.setText(Integer.toString(settlercnt));
        t4.setText(Integer.toString(robotcnt));
        t5.setText(Integer.toString(ufocnt));
        t6.setText(Integer.toString(ast.getTurnsTillCloseToSun()));
        t7.setText(Integer.toString(tpgatecnt));

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

        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.add(p0);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.add(p6);
        p.add(p7);

        panel.add(p);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        this.setTitle("Asteroid Info");
        this.setSize(350, 285);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
