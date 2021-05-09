package Graphics;


import modell.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class DrawGame extends JPanel {

    private BufferedImage ast1, ast1b1, ast1b2, ast1e,
            ast2, ast2b1, ast2b2, ast2e,
            ast3, ast3b1, ast3b2, ast3e,
            ast4, ast4b1, ast4b2, ast4e,
            astronaut, astronaut2, astronaut3, astronaut4,
            robot, ufo, background, invbox, tpgate3, tpgate2, tpgate1, tpgate0,
            coal, ice, iron, uran, uran2, uran3,
            coalIcon, iceIcon, ironIcon, uranIcon, uran2Icon, uran3Icon;

    private Space space;

    private int astNum;

    public DrawGame(Space spaceField) {
        space = spaceField;
        astNum = 0;
        try{
            ast1 = ImageIO.read(new File("image/ast1.png"));
            ast1b1 = ImageIO.read(new File("image/ast1b1.png"));
            ast1b2 = ImageIO.read(new File("image/ast1b2.png"));
            ast1e = ImageIO.read(new File("image/ast1e.png"));
            ast2 = ImageIO.read(new File("image/ast2.png"));
            ast2b1 = ImageIO.read(new File("image/ast2b1.png"));
            ast2b2 = ImageIO.read(new File("image/ast2b2.png"));
            ast2e = ImageIO.read(new File("image/ast2e.png"));
            ast3 = ImageIO.read(new File("image/ast3.png"));
            ast3b1 = ImageIO.read(new File("image/ast3b1.png"));
            ast3b2 = ImageIO.read(new File("image/ast3b2.png"));
            ast3e = ImageIO.read(new File("image/ast3e.png"));
            ast4 = ImageIO.read(new File("image/ast4.png"));
            ast4b1 = ImageIO.read(new File("image/ast4b1.png"));
            ast4b2 = ImageIO.read(new File("image/ast4b2.png"));
            ast4e = ImageIO.read(new File("image/ast4e.png"));
            astronaut = ImageIO.read(new File("image/astronaut.png"));
            astronaut2 = ImageIO.read(new File("image/astronaut2.png"));
            astronaut3 = ImageIO.read(new File("image/astronaut3.png"));
            astronaut4 = ImageIO.read(new File("image/astronaut4.png"));
            robot = ImageIO.read(new File("image/robot.png"));
            ufo = ImageIO.read(new File("image/ufo.png"));
            background = ImageIO.read(new File("image/background.png"));
            invbox = ImageIO.read(new File("image/invbox.png"));
            tpgate3 = ImageIO.read(new File("image/tpgateicon3.png"));
            tpgate2 = ImageIO.read(new File("image/tpgateicon2.png"));
            tpgate1 = ImageIO.read(new File("image/tpgateicon1.png"));
            tpgate0 = ImageIO.read(new File("image/tpgateicon0.png"));
            coal = ImageIO.read(new File("image/coal.png"));
            ice = ImageIO.read(new File("image/ice.png"));
            iron = ImageIO.read(new File("image/iron.png"));
            uran = ImageIO.read(new File("image/uran.png"));
            uran2 = ImageIO.read(new File("image/uran2.png"));
            uran3 = ImageIO.read(new File("image/uran3.png"));
            coalIcon = ImageIO.read(new File("image/coalIcon.png"));
            iceIcon = ImageIO.read(new File("image/iceIcon.png"));
            ironIcon = ImageIO.read(new File("image/ironIcon.png"));
            uranIcon = ImageIO.read(new File("image/uranIcon.png"));
            uran2Icon = ImageIO.read(new File("image/uran2Icon.png"));
            uran3Icon = ImageIO.read(new File("image/uran3Icon.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void paintComponent(Graphics g){
        drawGame(g);
    }


    public void drawGame(Graphics g) {
        //Background
        g.drawImage(background, 0, 0, null);
        //Asteroid
        Asteroid temp1 = space.getActors().get(space.getCurrentActor()).getCurrentAsteroid();
        if(space.getAsteroids().indexOf(temp1) % 4 == 0) {
            if(temp1.getLayer() == 3)
                g.drawImage(ast1, 425, 75, null);
            if(temp1.getLayer() == 2)
                g.drawImage(ast1b1, 425, 75, null);
            if(temp1.getLayer() == 1)
                g.drawImage(ast1b2, 425, 75, null);
            if(temp1.getLayer() == 0)
                g.drawImage(ast1e, 425, 75, null);
        }
        else if(space.getAsteroids().indexOf(temp1) % 4 == 1) {
            if(temp1.getLayer() == 3)
                g.drawImage(ast2, 425, 75, null);
            if(temp1.getLayer() == 2)
                g.drawImage(ast2b1, 425, 75, null);
            if(temp1.getLayer() == 1)
                g.drawImage(ast2b2, 425, 75, null);
            if(temp1.getLayer() == 0)
                g.drawImage(ast2e, 425, 75, null);
        }
        else if(space.getAsteroids().indexOf(temp1) % 4 == 2) {
            if(temp1.getLayer() == 3)
                g.drawImage(ast3, 425, 75, null);
            if(temp1.getLayer() == 2)
                g.drawImage(ast3b1, 425, 75, null);
            if(temp1.getLayer() == 1)
                g.drawImage(ast3b2, 425, 75, null);
            if(temp1.getLayer() == 0)
                g.drawImage(ast3e, 425, 75, null);
        }
        else if(space.getAsteroids().indexOf(temp1) % 4 == 3) {
            if(temp1.getLayer() == 3)
                g.drawImage(ast4, 425, 75, null);
            if(temp1.getLayer() == 2)
                g.drawImage(ast4b1, 425, 75, null);
            if(temp1.getLayer() == 1)
                g.drawImage(ast4b2, 425, 75, null);
            if(temp1.getLayer() == 0)
                g.drawImage(ast4e, 425, 75, null);
        }
        //core material (if not empty)
        if(temp1.getLayer() == 0) {
            if(temp1.getCoreMaterial() != null){
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Coal))
                    g.drawImage(coal, 725, 400, null);
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Iron))
                    g.drawImage(iron, 725, 400, null);
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Ice))
                    g.drawImage(ice, 725, 400, null);
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Uran)) {
                    if(temp1.getCoreMaterial().getCounter() == 0)
                        g.drawImage(uran, 725, 400, null);
                    if(temp1.getCoreMaterial().getCounter() == 1)
                        g.drawImage(uran2, 725, 400, null);
                    if(temp1.getCoreMaterial().getCounter() == 2)
                        g.drawImage(uran3, 725, 400, null);
                }
            }
        }
        //settler
        Random r = new Random();
        int randInt = r.nextInt(4);
        switch(randInt) {
            case 0: g.drawImage(astronaut, 700, 125, null); break;
            case 1: g.drawImage(astronaut2, 700, 125, null); break;
            case 2: g.drawImage(astronaut3, 700, 125, null); break;
            case 3: g.drawImage(astronaut4, 700, 125, null); break;
        }
        //Robot
        for(int i = 0; i < space.getActors().get(space.getCurrentActor()).getCurrentAsteroid().getActorsOnSurface().size(); i++){
            if(space.getActors().get(space.getCurrentActor()).getCurrentAsteroid().getActorsOnSurface().get(i).getType().equals("Robot")) {
                g.drawImage(robot, 850, 175, null);
                break;
            }
        }
        //Ufo
        for(int i = 0; i < space.getActors().get(space.getCurrentActor()).getCurrentAsteroid().getActorsOnSurface().size(); i++){
            if(space.getActors().get(space.getCurrentActor()).getCurrentAsteroid().getActorsOnSurface().get(i).getType().equals("Ufo") && space.getActors().get(i).getType().equals("Ufo")) {
                g.drawImage(ufo, 550, 175, null);
                break;
            }
        }
        //inventory slots
        int posXchange = 0;
        for(int i = 0; i < 10; i++) {
            g.drawImage(invbox, 300 + posXchange, 750, null);
            posXchange += 100;
        }
        //inventory fill
        posXchange = 0;
        for(int i = 0; i < space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().size(); i++) {
            if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Coal) {
                g.drawImage(coalIcon, 300 + posXchange, 757, null);
                posXchange += 100;
            }
            if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Iron) {
                g.drawImage(ironIcon, 310 + posXchange, 770, null);
                posXchange += 100;
            }
            if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Ice) {
                g.drawImage(iceIcon, 300 + posXchange, 757, null);
                posXchange += 100;
            }if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Uran) {
                if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getCounter() == 0)
                    g.drawImage(uranIcon, 300 + posXchange, 757, null);
                else if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getCounter() == 1)
                    g.drawImage(uran2Icon, 300 + posXchange, 757, null);
                else if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getCounter() == 2)
                    g.drawImage(uran3Icon, 300 + posXchange, 757, null);
                posXchange += 100;
            }
        }
        //TpGate
        if(((Settler)space.getActors().get(space.getCurrentActor())).getTpGates().size() == 3) {
            g.drawImage(tpgate3, 0 , 600, null);
        }
        else if(((Settler)space.getActors().get(space.getCurrentActor())).getTpGates().size() == 2) {
            g.drawImage(tpgate2, 0 , 600, null);
        }
        else if(((Settler)space.getActors().get(space.getCurrentActor())).getTpGates().size() == 1) {
            g.drawImage(tpgate1, 0 , 600, null);
        }
        else if(((Settler)space.getActors().get(space.getCurrentActor())).getTpGates().size() == 0) {
            g.drawImage(tpgate0, 0 , 600, null);
        }
    }
}
