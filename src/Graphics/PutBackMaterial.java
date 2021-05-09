package Graphics;

import modell.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PutBackMaterial segítségével tudunk kiválasztani egy nyersanyagot, amelyet visszatehetünk az aszteroidára
 */
public class PutBackMaterial extends JFrame {
    private Space space;
    private JComboBox<String> comboBox;
    private Settler currentSettler;
    private GameScreen screen;

    /**
     * PutBackMaterial konstruktora, inicializáljuk a PutBackMaterial screen-t
     * @param space a játék space-e
     * @param screen a GameScreen
     */
    public PutBackMaterial(Space space, GameScreen screen) {
        this.screen = screen;
        this.space = space;
        JPanel panel = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        panel.setLayout(new FlowLayout());
        currentSettler = (Settler) space.getActors().get(space.getCurrentActor());

        List<Material> arr = currentSettler.getMaterials().getMaterials();
        if(arr.size() > 0){
            Set<String> set = new HashSet<>();
            for (Material mat : arr) {
                set.add(mat.getType().toString());
            }
            comboBox = new JComboBox<>(new DefaultComboBoxModel<>(set.toArray(new String[set.size()])));
        }else{
            comboBox = new JComboBox<>(new String[]{"Nothing"});
        }

        JButton putBackButton = new JButton("Put Back");
        putBackButton.addActionListener(new PutBackButtonActionListener(this));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonActionListener(this));

        panel.add(comboBox);
        panel.add(putBackButton);
        panel.add(backButton);
        p2.add(panel);

        this.setTitle("Move");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(p2);
        this.setSize(new Dimension(300, 100));
        this.setVisible(true);
    }

    /**
     * A PutBack gomb hatását definiálja, vagyis ha rányomunk a PutBack-re a kiválasztott nyersanyagot visszatesszük az aszteroidára
     */
    private class PutBackButtonActionListener implements ActionListener {

        JFrame frame;

        public PutBackButtonActionListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(comboBox.getSelectedItem() != null){
                String pick = (String) comboBox.getSelectedItem();
                int idx = 0;
                switch (pick){
                    case "Nothing":
                        return;
                    case "Coal":
                        idx = space.getActors().get(space.getCurrentActor()).getMaterials().getCoal();
                        break;
                    case "Ice":
                        idx = space.getActors().get(space.getCurrentActor()).getMaterials().getIce();
                        break;
                    case "Iron":
                        idx = space.getActors().get(space.getCurrentActor()).getMaterials().getIron();
                        break;
                    case "Uran":
                        idx = space.getActors().get(space.getCurrentActor()).getMaterials().getUran();
                        break;
                }
                if(idx != -1) {
                    Material mat = space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(idx);
                    space.getActors().get(space.getCurrentActor()).putMaterialBack(mat);
                }else{
                    System.out.println("No material");
                }

                frame.dispose();
                screen.actOne();
                screen.repaint();
                screen.setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }

    /**
     * A Back gomb hatását definiálja, vagyis ha rányomunk a back-re visszaugrunk a GameScreen-re
     */
    private class BackButtonActionListener implements ActionListener {

        JFrame frame;

        public BackButtonActionListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            screen.setVisible(true);
        }

    }
}
