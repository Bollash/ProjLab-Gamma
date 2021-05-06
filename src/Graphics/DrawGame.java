package Graphics;


import modell.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DrawGame extends JPanel {

    private BufferedImage ast1, ast1b1, ast1b2, ast1e,
            ast2, ast2b1, ast2b2, ast2e,
            astronaut, robot, ufo, background, invbox, tpgate,
            coal, ice, iron, uran, uran2, uran3,
            coalIcon, iceIcon, ironIcon, uranIcon, uran2Icon, uran3Icon;
    private Space space;

    public DrawGame(Space spaceField) {
        space = spaceField;
        try{
            ast1 = ImageIO.read(new File("image/ast1.png"));
            ast1b1 = ImageIO.read(new File("image/ast1b1.png"));
            ast1b2 = ImageIO.read(new File("image/ast1b2.png"));
            ast1e = ImageIO.read(new File("image/ast1e.png"));
            ast2 = ImageIO.read(new File("image/ast2.png"));
            ast2b1 = ImageIO.read(new File("image/ast2b1.png"));
            ast2b2 = ImageIO.read(new File("image/ast2b2.png"));
            ast2e = ImageIO.read(new File("image/ast2e.png"));
            astronaut = ImageIO.read(new File("image/astronaut.png"));
            robot = ImageIO.read(new File("image/robot.png"));
            ufo = ImageIO.read(new File("image/ufo.png"));
            background = ImageIO.read(new File("image/background.png"));
            invbox = ImageIO.read(new File("image/invbox.png"));
            tpgate = ImageIO.read(new File("image/tpgateicon.png"));
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
        if(space.getAsteroids().indexOf(temp1) % 2 == 0) {
            if(temp1.getLayer() == 3)
                g.drawImage(ast1, 425, 75, null);
            if(temp1.getLayer() == 2)
                g.drawImage(ast1b1, 425, 75, null);
            if(temp1.getLayer() == 1)
                g.drawImage(ast1b2, 425, 75, null);
            if(temp1.getLayer() == 0)
                g.drawImage(ast1e, 425, 75, null);
        }
        else if(space.getAsteroids().indexOf(temp1) % 2 == 1) {
            if(temp1.getLayer() == 3)
                g.drawImage(ast2, 425, 75, null);
            if(temp1.getLayer() == 2)
                g.drawImage(ast2b1, 425, 75, null);
            if(temp1.getLayer() == 1)
                g.drawImage(ast2b2, 425, 75, null);
            if(temp1.getLayer() == 0)
                g.drawImage(ast2e, 425, 75, null);
        }
        //core material (if not empty)
        if(temp1.getLayer() == 0) {
            if(temp1.getCoreMaterial() != null){
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Coal))
                    g.drawImage(coalIcon, 725, 375, null);
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Iron))
                    g.drawImage(ironIcon, 725, 375, null);
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Ice))
                    g.drawImage(iceIcon, 725, 375, null);
                if(temp1.getCoreMaterial().getType().equals(MaterialType.Uran)) {
                    if(temp1.getCoreMaterial().getCounter() == 0)
                        g.drawImage(uranIcon, 725, 375, null);
                    if(temp1.getCoreMaterial().getCounter() == 1)
                        g.drawImage(uran2Icon, 725, 375, null);
                    if(temp1.getCoreMaterial().getCounter() == 2)
                        g.drawImage(uran3Icon, 725, 375, null);
                }
            }
        }
        //settler
        g.drawImage(astronaut, 650, 125, null);
        //Robot
        for(int i = 0; i < space.getActors().size(); i++){
            if(space.getActors().get(i).getType().equals("Robot")) {
                g.drawImage(robot, 750, 175, null);
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
            g.drawImage(invbox, 50 + posXchange, 725, null);
            posXchange += 150;
        }
        //inventory fill
        posXchange = 0;
        for(int i = 0; i < space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().size(); i++) {
            if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Coal) {
                g.drawImage(coalIcon, 50 + posXchange, 725, null);
                posXchange += 150;
            }
            if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Iron) {
                g.drawImage(ironIcon, 50 + posXchange, 725, null);
                posXchange += 150;
            }
            if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Ice) {
                g.drawImage(iceIcon, 50 + posXchange, 725, null);
                posXchange += 150;
            }if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getType() == MaterialType.Uran) {
                if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getCounter() == 0)
                    g.drawImage(uranIcon, 50 + posXchange, 725, null);
                else if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getCounter() == 1)
                    g.drawImage(uran2Icon, 50 + posXchange, 725, null);
                else if(space.getActors().get(space.getCurrentActor()).getMaterials().getMaterials().get(i).getCounter() == 2)
                    g.drawImage(uran3Icon, 50 + posXchange, 725, null);
                posXchange += 150;
            }
        }
        //TpGate
        g.drawImage(tpgate, 50 + posXchange, 575, null);
    }
}
