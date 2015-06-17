/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author janaj4926
 */
// make sure you rename this class if you are doing a copy/paste
public class VideoGame extends JComponent implements KeyListener {

    //name of video game: Block warriors
    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // One finger death punch
    boolean left = false, right = false, lReleased = true, rReleased = true;
    //stuff
    Rectangle floor = new Rectangle(0, 500, 2000, 30);
    //sunset
    BufferedImage sunset = ImageHelper.loadImage("minecraftsunset.png");
    //enemy
    //left
    int ex1 = 0;
    int ex2 = -150;
    int ex3 = -650;
    int ex4 = -1000;
    int ex5 = -1500;
    //right
    int ex6 = 1950;
    int ex7 = 2100;
    int ex8 = 2600;
    int ex9 = 2950;
    int ex10 = 3450;
    int[] enemyx = {ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10};
    //enemy guys
    //left side
    Rectangle block1 = new Rectangle(enemyx[0], 450, 50, 50);
    Rectangle block2 = new Rectangle(enemyx[1], 450, 50, 50);
    Rectangle block3 = new Rectangle(enemyx[2], 450, 50, 50);
    Rectangle block4 = new Rectangle(enemyx[3], 450, 50, 50);
    Rectangle block5 = new Rectangle(enemyx[4], 450, 50, 50);
    //right side
    Rectangle block6 = new Rectangle(enemyx[5], 450, 50, 50);
    Rectangle block7 = new Rectangle(enemyx[6], 450, 50, 50);
    Rectangle block8 = new Rectangle(enemyx[7], 450, 50, 50);
    Rectangle block9 = new Rectangle(enemyx[8], 450, 50, 50);
    Rectangle block10 = new Rectangle(enemyx[9], 450, 50, 50);
    //player
    Rectangle hero = new Rectangle(950, 450, 50, 50);
    //movement
    //enemy
    int speed = 3;
    //camera
    int camx = 0;
    //score
    int score = 0;
    //life
    int life = 10;
    //miss
    boolean miss = false;
    long missTime = 0;
    //end screen
    int t1 = -500;
    int t2 = -500;
    boolean end = false;
    int endx = -1000;
    int endy = -1000;
    int endw = 1000;
    int endh = 600;
    
    int nx1 = - 1000;
    int nx2 = + 2000;
    
    int ax1 = -1000;
    int ax2 = -1000;
    int ax3 = 2000;
    int ax4 = 2000;
    
    //end screen slash x values
    int gamex1 = -90;
    int gamex2 = -195;
    int gamex3 = -190;
    int gamex4 = -550;
    int gamex5 = -540;
    int gamex6 = -800;
    int gamex7 = -720;
    int gamex8 = -710;
    int gamex9 = -370;
    int gamex10 = -400;
    int gamex11 = -200;
    int gamex12 = -240;
    //int[] gamex = {90,195,190,550,540,800, 720,710,370,400,200,240}; 
    int[] gamex = {gamex1, gamex2, gamex3, gamex4, gamex5, gamex6, gamex7, gamex8, gamex9, gamex10, gamex11, gamex12};
    //end screen slash y values
    int overy1 = -60;
    int overy2 = -80;
    int overy3 = -55;
    int overy4 = -280;
    int overy5 = -250;
    int overy6 = -440;
    int overy7 = -420;
    int overy8 = -410;
    int overy9 = -300;
    int overy10 = -290;
    int overy11 = -200;
    int overy12 = -195;
    //int[] overy = {60,80,55,280,250,440, 420,410,300,290,200,195};
    int[] overy = {overy1, overy2, overy3, overy4, overy5, overy6, overy7, overy8, overy9, overy10, overy11, overy12};
    //face polygons
    int[] ninjax = {block4.x - camx, block4.x + 30 - camx, block4.x + block4.width - camx, block4.x + block4.width - camx, block4.x - camx, block4.x - camx, block4.x + 5 - camx, block4.x - camx};
    int[] ninjay = {block4.y, block4.y + 8, block4.y + 2, block4.y + 15, block4.y + 35, block4.y + 14, block4.y + 12, block4.y + 11};
    int[] mustx = {block5.x + 15 - camx, block5.x + 10 - camx, block5.x + 20 - camx, block5.x + 35 - camx, block5.x + 40 - camx, block5.x + 30 - camx};
    int[] musty = {block5.y + 35, block5.y + 45, block5.y + 40, block5.y + 35, block5.y + 45, block5.y + 40};
    
    int[] hairx = {hero.x - camx, hero.x - 7 - camx, hero.x - 4 - camx, hero.x + 33 - camx, hero.x + 38 - camx, hero.x + 48 - camx, hero.x + 53 - camx, hero.x + 58 - camx, hero.x + 58 - camx, hero.x + 53 - camx, hero.x + 58 - camx, hero.x + 33 - camx, hero.x + 36 - camx, hero.x + 13 - camx};
    int[] hairy = {hero.y + 21, hero.y + 13, hero.y - 8, hero.y - 9, hero.y - 5, hero.y - 7, hero.y - 6, hero.y - 4, hero.y + 17, hero.y + 20, hero.y + 16, hero.y + 19, hero.y + 22, hero.y + 17};
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        //Colours

        Color sky = new Color(45, 54, 117);

        //sky
        g.setColor(sky);
        g.fillRect(0, 0, 2000, 600);
        g.drawImage(sunset, 0 - camx, 0, 2000, 600, null);
        //score
        g.setColor(Color.WHITE);
        g.drawString("ENEMY'S TAKEN DOWN: " + score, WIDTH / 2 - 60, 50);
        g.drawString("hp: " + life, WIDTH / 2, 100);


        //block1
        //body
        Color cat = new Color(222, 115, 115);
        g.setColor(cat);
        g.fillRect(block1.x - camx, block1.y, block1.width, block1.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block1.x - camx, block1.y, block1.width, block1.height);
        //face
        g.drawArc(block1.x + 18 - camx, block1.y + 25, 10, 10, 180, 180);
        g.drawArc(block1.x + 28 - camx, block1.y + 25, 10, 10, 180, 180);
        g.fillOval(block1.x + 5 - camx, block1.y + 10, 15, 20);
        g.fillOval(block1.x + 35 - camx, block1.y + 10, 15, 20);
        g.drawArc(block1.x + 5 - camx, block1.y + 35, 5, 10, 180, 180);

        //block2
        //body
        g.setColor(Color.GREEN);
        g.fillRect(block2.x - camx, block2.y, block2.width, block2.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block2.x - camx, block2.y, block2.width, block2.height);
        //face
        g.fillRect(block2.x + 15 - camx, block2.y + 10, 10, 20);
        g.fillRect(block2.x + 35 - camx, block2.y + 10, 10, 20);
        g.fillRect(block2.x + 24 - camx, block2.y + 32, 13, 8);
        g.fillRect(block2.x + 19 - camx, block2.y + 37, 8, 10);
        g.fillRect(block2.x + 32 - camx, block2.y + 37, 8, 10);

        //block3
        //horns behind head
        Color horns = new Color(209, 171, 67);
        g.setColor(horns);
        g.fillRect(block3.x + 30 - camx, block3.y - 5, 30, 15);
        g.setColor(Color.BLACK);
        g.drawRect(block3.x + 30 - camx, block3.y - 5, 30, 15);
        g.setColor(horns);
        g.fillRect(block3.x + 42 - camx, block3.y + 10, 10, 15);
        g.setColor(Color.BLACK);
        g.drawRect(block3.x + 42 - camx, block3.y + 10, 10, 15);
        g.setColor(horns);
        g.fillRect(block3.x + 50 - camx, block3.y + 23, 10, 5);
        g.setColor(Color.BLACK);
        g.drawRect(block3.x + 50 - camx, block3.y + 23, 10, 5);
        //body
        Color skin = new Color(214, 176, 126);
        g.setColor(skin);
        g.fillRect(block3.x - camx, block3.y, block3.width, block3.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block3.x - camx, block3.y, block3.width, block3.height);
        //face
        g.setColor(Color.BLACK);
        g.fillArc(block3.x + 20 - camx, block3.y + 30, 15, 15, 0, 360);
        Color helm = new Color(120, 116, 105);
        g.setColor(helm);
        g.fillRect(block3.x - camx, block3.y, 50, 30);
        g.setColor(Color.BLACK);
        g.fillRect(block3.x + 10 - camx, block3.y + 15, 15, 5);
        g.fillRect(block3.x + 30 - camx, block3.y + 15, 15, 5);
        g.setColor(horns);
        g.fillRect(block3.x - 20 - camx, block3.y - 5, 30, 15);
        g.setColor(Color.BLACK);
        g.drawRect(block3.x - 20 - camx, block3.y - 5, 30, 15);
        g.setColor(horns);
        g.fillRect(block3.x - 22 - camx, block3.y + 5, 10, 15);
        g.setColor(Color.BLACK);
        g.drawRect(block3.x - 22 - camx, block3.y + 5, 10, 15);
        g.setColor(horns);
        g.fillRect(block3.x - 17 - camx, block3.y + 18, 10, 5);
        g.setColor(Color.BLACK);
        g.drawRect(block3.x - 17 - camx, block3.y + 18, 10, 5);

        //block4
        //body
        g.setColor(skin);
        g.fillRect(block4.x - camx, block4.y, block4.width, block4.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block4.x - camx, block4.y, block4.width, block4.height);
        //face
        Color eye = new Color(219, 203, 94);
        g.setColor(eye);
        g.fillRect(block4.x + 30 - camx, block4.y + 10, 15, 15);
        g.setColor(Color.BLACK);
        g.fillRect(block4.x + 38 - camx, block4.y + 15, 5, 10);
        g.drawRect(block4.x + 30 - camx, block4.y + 10, 15, 15);
        g.setColor(Color.WHITE);
        g.fillPolygon(ninjax, ninjay, 8);
        g.setColor(Color.BLACK);
        g.drawPolygon(ninjax, ninjay, 8);
        g.setColor(Color.BLACK);
        g.drawLine(block4.x + block4.width - camx, block4.y + 10, block4.x - camx, block4.y + 22);
        g.drawLine(block4.x + 5 - camx, block4.y + 12, block4.x + 25 - camx, block4.y + 16);
        g.drawLine(block4.x + 30 - camx, block4.y + 8, block4.x + 45 - camx, block4.y + 12);

        //block5
        //body
        g.setColor(skin);
        g.fillRect(block5.x - camx, block5.y, block5.width, block5.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block5.x - camx, block5.y, block5.width, block5.height);
        //face
        Color hat = new Color(224, 165, 0);
        g.setColor(hat);
        g.fillRect(block5.x - 5 - camx, block5.y - 5, 60, 30);
        g.setColor(Color.BLACK);
        g.drawRect(block5.x - 5 - camx, block5.y - 5, 60, 30);
        g.setColor(hat);
        g.fillRect(block5.x - 10 - camx, block5.y + 20, 70, 10);
        g.setColor(Color.BLACK);
        g.drawRect(block5.x - 10 - camx, block5.y + 20, 70, 10);
        g.setColor(hat);
        g.fillRect(block5.x + 20 - camx, block5.y - 10, 10, 30);
        g.setColor(Color.BLACK);
        g.drawRect(block5.x + 20 - camx, block5.y - 10, 10, 30);
        ///work on mustache
        Color mustache = new Color(69, 53, 3);
        g.setColor(mustache);
        g.fillPolygon(mustx, musty, 6);

        //block6
        //body
        Color orc = new Color(24, 69, 3);
        g.setColor(orc);
        g.fillRect(block6.x - camx, block6.y, block6.width, block6.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block6.x - camx, block6.y, block6.width, block6.height);
        //face
        g.setColor(Color.BLACK);
        g.fillRect(block6.x + 5 - camx, block6.y + 10, 15, 15);
        g.fillRect(block6.x + 30 - camx, block6.y + 10, 10, 15);
        g.drawLine(block6.x - camx, block6.y + 40, block6.x + block6.width - camx, block6.y + 40);
        g.setColor(Color.WHITE);
        g.fillRect(block6.x + 5 - camx, block6.y + 35, 10, 5);
        g.fillRect(block6.x + 30 - camx, block6.y + 30, 10, 10);

        //block7
        //body
        g.setColor(Color.BLACK);
        g.fillRect(block7.x - camx, block7.y, block7.width, block7.height);
        //face
        g.setColor(Color.RED);
        g.fillRect(block7.x + 11 - camx, block7.y + 12, 10, 20);
        g.fillRect(block7.x + 26 - camx, block7.y + 12, 10, 20);
        g.fillRect(block7.x + 3 - camx, block7.y + 10, 5, 10);
        g.fillRect(block7.x + 38 - camx, block7.y + 10, 5, 10);
        g.fillRect(block7.x + 13 - camx, block7.y + 5, 5, 5);
        g.fillRect(block7.x + 28 - camx, block7.y + 5, 5, 5);
        g.fillRect(block7.x + 8 - camx, block7.y + 3, 2, 2);
        g.fillRect(block7.x + 36 - camx, block7.y + 3, 2, 2);
        Color spider = new Color(69, 64, 64);
        g.setColor(spider);
        g.fillRect(block7.x + 3 - camx, block7.y + 35, 10, 12);
        g.fillRect(block7.x + 33 - camx, block7.y + 35, 10, 12);
        Color teeth = new Color(186, 186, 186);
        g.setColor(teeth);
        g.fillRect(block7.x + 13 - camx, block7.y + 43, 8, 4);
        g.fillRect(block7.x + 25 - camx, block7.y + 43, 8, 4);

        //block8
        //body
        g.setColor(cat);
        g.fillRoundRect(block8.x - camx, block8.y, block8.width, block8.height, 10, 10);
        Color zombie = new Color(103, 133, 81);
        g.setColor(zombie);
        g.fillRect(block8.x - camx, block8.y + 15, block8.width, block8.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRoundRect(block8.x - camx, block8.y, block8.width, block8.height, 10, 10);
        g.drawRect(block8.x - camx, block8.y + 15, block8.width, block8.height);
        //face
        g.fillRect(block8.x + 5 - camx, block8.y + 17, 15, 10);
        g.fillRect(block8.x + 25 - camx, block8.y + 17, 15, 10);
        Color beard = new Color(71, 60, 49);
        g.setColor(beard);
        g.fillRect(block8.x - camx, block8.y + 45, 50, 5);
        g.fillRect(block8.x + 13 - camx, block8.y + 35, 5, 10);
        g.fillRect(block8.x + 28 - camx, block8.y + 35, 5, 10);
        g.fillRect(block8.x + 18 - camx, block8.y + 30, 10, 5);
        g.setColor(Color.BLACK);
        g.drawArc(block8.x + 5 - camx, block8.y + 3, 10, 10, 100, 70);
        g.drawArc(block8.x + 30 - camx, block8.y + 5, 15, 15, 10, 70);
        g.drawArc(block8.x + 15 - camx, block8.y + 4, 20, 20, 120, 40);
        g.drawArc(block8.x + 25 - camx, block8.y + 4, 10, 10, 80, 20);

        //block9
        //body
        g.setColor(skin);
        g.fillRect(block9.x - camx, block9.y, block9.width, block9.height);
        //outline
        g.setColor(Color.BLACK);
        g.drawRect(block9.x - camx, block9.y, block9.width, block9.height);
        //face
        g.fillRect(block9.x - camx, block9.y, 50, 15);
        g.setColor(Color.WHITE);
        g.fillRect(block9.x + 30 - camx, block9.y - 5, 15, 5);
        g.setColor(Color.BLACK);
        g.drawRect(block9.x + 30 - camx, block9.y - 5, 15, 5);
        g.fillRect(block9.x + 37 - camx, block9.y - 15, 5, 7);
        g.fillRect(block9.x + 5 - camx, block9.y + 20, 15, 8);
        g.fillRect(block9.x + 25 - camx, block9.y + 20, 15, 8);
        g.drawLine(block9.x + 10 - camx, block9.y + 33, block9.x + 30 - camx, block9.y + 33);
        g.setColor(Color.RED);
        g.fillRect(block9.x - camx, block9.y + 37, 50, 7);
        g.setColor(Color.BLACK);
        g.drawRect(block9.x - camx, block9.y + 37, 50, 7);
        g.setColor(Color.RED);
        g.fillRect(block9.x + 18 - camx, block9.y + 37, 7, 13);
        g.setColor(Color.BLACK);
        g.drawRect(block9.x + 18 - camx, block9.y + 37, 7, 13);

        //block10
        //body
        g.setColor(Color.WHITE);
        g.fillRect(block10.x - camx, block10.y, block10.width, block10.height);
        //outline
        g.setColor(Color.black);
        g.drawRect(block10.x - camx, block10.y, block10.width, block10.height);
        //face
        g.fillRect(block10.x - camx, block10.y + 25, 20, 25);
        g.fillRect(block10.x + 30 - camx, block10.y + 25, 20, 25);
        g.drawLine(block10.x - camx, block10.y + 25, block10.x + 50 - camx, block10.y + 25);
        g.setColor(Color.RED);
        g.fillRect(block10.x + 20 - camx, block10.y + 25, 10, 5);
        g.setColor(Color.black);
        g.drawRect(block10.x + 20 - camx, block10.y + 25, 10, 5);
        g.setColor(Color.RED);
        g.fillRect(block10.x + 23 - camx, block10.y + 30, 4, 5);
        g.setColor(Color.black);
        g.drawRect(block10.x + 23 - camx, block10.y + 30, 4, 5);
        g.setColor(Color.RED);
        g.fillRect(block10.x + 21 - camx, block10.y + 35, 7, 15);
        g.setColor(Color.black);
        g.drawRect(block10.x + 21 - camx, block10.y + 35, 7, 15);
        g.drawLine(block10.x + 5 - camx, block10.y + 10, block10.x + 10 - camx, block10.y + 20);
        g.drawLine(block10.x + 45 - camx, block10.y + 10, block10.x + 40 - camx, block10.y + 20);

        //hero
        g.setColor(skin);
        g.fillRect(hero.x - camx, hero.y, hero.width, hero.height);

        //outlines
        g.setColor(Color.BLACK);
        g.drawRect(hero.x - camx, hero.y, hero.width, hero.height);

        g.setColor(skin);
        g.fillRoundRect(hero.x - 5 - camx, hero.y + 15, 30, 20, 5, 5);
        g.fillRoundRect(hero.x + 25 - camx, hero.y + 15, 30, 20, 5, 5);

        g.fillRoundRect(hero.x + 10 - camx, hero.y + 35, 15, 5, 2, 2);
        g.fillRoundRect(hero.x + 15 - camx, hero.y + 40, 10, 5, 2, 2);
        g.fillRoundRect(hero.x + 20 - camx, hero.y + 45, 5, 5, 2, 2);

        g.fillRoundRect(hero.x + 25 - camx, hero.y + 35, 15, 5, 2, 2);
        g.fillRoundRect(hero.x + 25 - camx, hero.y + 40, 10, 5, 2, 2);
        g.fillRoundRect(hero.x + 25 - camx, hero.y + 45, 5, 5, 2, 2);

        g.fillRoundRect(hero.x - 2 - camx, hero.y + 40, 5, 10, 2, 2);
        g.fillRoundRect(hero.x + 47 - camx, hero.y + 40, 5, 10, 2, 2);

        g.setColor(Color.BLACK);
        g.drawRoundRect(hero.x - 5 - camx, hero.y + 15, 30, 20, 5, 5);
        g.drawRoundRect(hero.x + 25 - camx, hero.y + 15, 30, 20, 5, 5);

        g.drawRoundRect(hero.x + 10 - camx, hero.y + 35, 15, 5, 2, 2);
        g.drawRoundRect(hero.x + 15 - camx, hero.y + 40, 10, 5, 2, 2);
        g.drawRoundRect(hero.x + 20 - camx, hero.y + 45, 5, 5, 2, 2);

        g.drawRoundRect(hero.x + 25 - camx, hero.y + 35, 15, 5, 2, 2);
        g.drawRoundRect(hero.x + 25 - camx, hero.y + 40, 10, 5, 2, 2);
        g.drawRoundRect(hero.x + 25 - camx, hero.y + 45, 5, 5, 2, 2);

        g.drawRoundRect(hero.x - 2 - camx, hero.y + 40, 5, 10, 2, 2);
        g.drawRoundRect(hero.x + 47 - camx, hero.y + 40, 5, 10, 2, 2);

        //hair
        Color hair = new Color(71,45,0);
        Color hairoutline = new Color(41,26,0);
        g.setColor(hair);
        g.fillPolygon(hairx, hairy, 14);
        g.setColor(hairoutline);
        g.drawPolygon(hairx, hairy, 14);

        //nipples
        Color nips = new Color (235,145,181);
        g.setColor(nips);
        g.fillArc(hero.x - 5 - camx, hero.y + 30, 5, 5, 0, 360);
        g.setColor(Color.BLACK);
        g.drawArc(hero.x - 4 - camx, hero.y + 29, 5, 5, 0, 360);
        g.drawArc(hero.x - 2 - camx, hero.y + 32, 2, 2, 0, 360);
        g.setColor(nips);
        g.fillArc(hero.x + 49 - camx, hero.y + 29, 5, 5, 0, 360);
        g.setColor(Color.BLACK);
        g.drawArc(hero.x + 50 - camx, hero.y + 29, 5, 5, 0, 360);
        g.drawArc(hero.x + 52 - camx, hero.y + 32, 2, 2, 0, 360);
        
        g.setColor(Color.black);
        //left target (fist)
        g.drawLine(hero.x - 145 - camx, 490, hero.x - 120 - camx, 490);
        g.drawLine(hero.x - 150 - camx, 488, hero.x - 150 - camx, 460);
        g.drawLine(hero.x - 145 - camx, 490, hero.x - 150 - camx, 488);
        g.drawLine(hero.x - 150 - camx, 460, hero.x - 140 - camx, 450);
        g.drawLine(hero.x - 140 - camx, 450, hero.x - 100 - camx, 455);
        g.drawLine(hero.x - 100 - camx, 455, hero.x - 50 - camx, 455);
        g.drawLine(hero.x - 50 - camx, 455, hero.x - 90 - camx, 470);
        g.drawLine(hero.x - 85 - camx, 468, hero.x - 70 - camx, 474);
        g.drawLine(hero.x - 70 - camx, 474, hero.x - 100 - camx, 485);
        g.drawLine(hero.x - 98 - camx, 484, hero.x - 90 - camx, 487);
        g.drawLine(hero.x - 90 - camx, 487, hero.x - 120 - camx, 490);
        g.drawLine(hero.x - 100 - camx, 455, hero.x - 90 - camx, 460);
        g.drawLine(hero.x - 90 - camx, 460, hero.x - 100 - camx, 475);
        g.drawLine(hero.x - 100 - camx, 475, hero.x - 120 - camx, 490);
        g.drawLine(hero.x - 140 - camx, 490, hero.x - 140 - camx, 480);
        g.drawLine(hero.x - 140 - camx, 480, hero.x - 130 - camx, 480);
        g.drawLine(hero.x - 130 - camx, 480, hero.x - 125 - camx, 470);
        g.drawLine(hero.x - 125 - camx, 470, hero.x - 125 - camx, 465);
        g.drawLine(hero.x - 127 - camx, 472, hero.x - 135 - camx, 465);
        g.drawLine(hero.x - 134 - camx, 467, hero.x - 136 - camx, 480);
        g.drawLine(hero.x - 132 - camx, 475, hero.x - 135 - camx, 475);
        g.drawLine(hero.x - 133 - camx, 478, hero.x - 129 - camx, 470);


        //right target (fist)
        g.drawLine(hero.x + 51 + 110 - camx, 490, hero.x + 51 + 145 - camx, 490);
        g.drawLine(hero.x + 51 + 150 - camx, 488, hero.x + 51 + 150 - camx, 460);
        g.drawLine(hero.x + 51 + 145 - camx, 490, hero.x + 51 + 150 - camx, 488);
        g.drawLine(hero.x + 51 + 150 - camx, 460, hero.x + 51 + 140 - camx, 450);
        g.drawLine(hero.x + 51 + 140 - camx, 450, hero.x + 51 + 100 - camx, 455);
        g.drawLine(hero.x + 51 + 100 - camx, 455, hero.x + 51 + 50 - camx, 455);
        g.drawLine(hero.x + 51 + 50 - camx, 455, hero.x + 51 + 90 - camx, 470);
        g.drawLine(hero.x + 51 + 85 - camx, 468, hero.x + 51 + 70 - camx, 474);
        g.drawLine(hero.x + 51 + 70 - camx, 474, hero.x + 51 + 100 - camx, 485);
        g.drawLine(hero.x + 51 + 98 - camx, 484, hero.x + 51 + 90 - camx, 487);
        g.drawLine(hero.x + 51 + 90 - camx, 487, hero.x + 51 + 120 - camx, 490);
        g.drawLine(hero.x + 51 + 100 - camx, 455, hero.x + 51 + 90 - camx, 460);
        g.drawLine(hero.x + 51 + 90 - camx, 460, hero.x + 51 + 100 - camx, 475);
        g.drawLine(hero.x + 51 + 100 - camx, 475, hero.x + 51 + 120 - camx, 490);
        g.drawLine(hero.x + 51 + 140 - camx, 490, hero.x + 51 + 140 - camx, 480);
        g.drawLine(hero.x + 51 + 140 - camx, 480, hero.x + 51 + 130 - camx, 480);
        g.drawLine(hero.x + 51 + 130 - camx, 480, hero.x + 51 + 125 - camx, 470);
        g.drawLine(hero.x + 51 + 125 - camx, 470, hero.x + 51 + 125 - camx, 465);
        g.drawLine(hero.x + 51 + 127 - camx, 472, hero.x + 51 + 135 - camx, 465);
        g.drawLine(hero.x + 51 + 134 - camx, 467, hero.x + 51 + 136 - camx, 480);
        g.drawLine(hero.x + 51 + 132 - camx, 475, hero.x + 51 + 135 - camx, 475);
        g.drawLine(hero.x + 51 + 133 - camx, 478, hero.x + 51 + 129 - camx, 470);




        //floor
        Color bridge = new Color(56, 26, 7);
        g.setColor(bridge);
        g.fillRect(floor.x - camx, floor.y, floor.width, floor.height);

        //end screen
        Color endscreen = new Color(82, 11, 11);
        g.setColor(skin);
        g.fillRect(endx, endy, endw, endh);

        g.setColor(endscreen);
        g.fillPolygon(gamex, overy, 12);

        g.setColor(Color.BLACK);
        g.drawRoundRect(nx1, -200, 1000, 500, 50, 50);
        g.drawRoundRect(nx2, -200, 1000, 500, 50, 50);
        
        g.drawRoundRect(ax1, 300, 400, 150, 10, 10);
        g.drawRoundRect(ax2, 300, 400, 150, 10, 10);
        g.drawRoundRect(ax3, 450, 300, 150, 10, 10);
        g.drawRoundRect(ax4, 450, 300, 150, 10, 10);
        
        
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", t1, 290);
        g.drawString("" + score, t2, 300);


        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            //if player misses/ keeps time
            if (miss) {
                if (System.currentTimeMillis() - missTime > 400) {
                    miss = false;

                }
            }

            //regular game play
            if (!miss) {
                //movement
                //hero fighting
                if (right && rReleased) {
                    rReleased = false;
                    hero.x += 150;

                    //right side collisions
                    if (block6.x < hero.x + hero.width & block6.x > hero.x - 150) {
                        score++;
                        block6.x = 1950;
                    }
                    if (block7.x < hero.x + hero.width & block7.x > hero.x - 150) {
                        score++;
                        block7.x = 2100;
                    }
                    if (block8.x < hero.x + hero.width & block8.x > hero.x - 150) {
                        score++;
                        block8.x = 2250;
                    }
                    if (block9.x < hero.x + hero.width & block9.x > hero.x - 150) {
                        score++;
                        block9.x = 2400;
                    }
                    if (block10.x < hero.x + hero.width & block10.x > hero.x - 150) {
                        score++;
                        block10.x = 2550;
                    }

                    //if hero misses
                    if (block6.x > hero.x + hero.width & block7.x > hero.x + hero.width & block8.x > hero.x + hero.width & block9.x > hero.x + hero.width & block10.x > hero.x + hero.width) {
                        miss = true;
                        missTime = System.currentTimeMillis();
                    }

                    //edge of screen
                    if (hero.x >= 1850 - hero.width) {
                        hero.x = 1850 - hero.width;
                    }


                    //left side
                } else if (left && lReleased) {
                    lReleased = false;
                    hero.x -= 150;

                    //left side collisions
                    if (block1.x + block1.width > hero.x & block1.x + block1.width < hero.x + 150) {
                        score++;
                        block1.x = -50;

                    }

                    if (block2.x + block2.width > hero.x & block2.x + block2.width < hero.x + 150) {
                        score++;
                        block2.x = -200;
                    }

                    if (block3.x + block3.width > hero.x & block3.x + block3.width < hero.x + 150) {
                        score++;
                        block3.x = -350;
                    }

                    if (block4.x + block4.width > hero.x & block4.x + block4.width < hero.x + 150) {
                        score++;
                        block4.x = -500;
                    }

                    if (block5.x + block5.width > hero.x & block5.x + block5.width < hero.x + 150) {
                        score++;
                        block5.x = -650;
                    }


                    //if hero misses
                    if (block1.x + block1.width < hero.x & block2.x + block2.width < hero.x & block3.x + block3.width < hero.x & block4.x + block4.width < hero.x & block5.x + block5.width < hero.x) {
                        miss = true;
                        missTime = System.currentTimeMillis();
                    }

                    //edge of screen
                    if (hero.x <= 150) {
                        hero.x = 150;
                    }
                }
            }

            //camera
            if (hero.x < WIDTH / 2) {
                camx = 0;
            } else {
                camx = hero.x - WIDTH / 2;
            }
            if (hero.x >= 1500) {
                camx = 1000;
            }



            //enemy movments
            //from left
            //1
            if (block1.x < 2000) {
                block1.x += speed;
                if (block1.x + block1.width >= hero.x & block1.x + block1.width <= hero.x + hero.width) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block1.x = hero.x - 200;
                    hero.x += 10;
                }
            } else if (block1.x >= 2000) {
                block1.x = -700;
            }

            //2
            if (block2.x < 2000) {
                block2.x += speed;
                if (block2.x + block2.width >= hero.x & block2.x + block2.width <= hero.x + hero.width) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block2.x = hero.x - 200;
                    hero.x += 10;
                }
            } else if (block2.x >= 2000) {
                block2.x = -1000;
            }

            //3
            if (block3.x < 2000) {
                block3.x += speed;
                if (block3.x + block3.width >= hero.x & block3.x + block3.width <= hero.x + hero.width) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block3.x = hero.x - 200;
                    hero.x += 10;
                }
            } else if (block3.x >= 2000) {
                block3.x = -600;
            }

            //4
            if (block4.x < 2000) {
                block4.x += speed;
                if (block4.x + block4.width >= hero.x & block4.x + block4.width <= hero.x + hero.width) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block4.x = hero.x - 200;
                    hero.x += 10;
                }
            } else if (block4.x >= 2000) {
                block4.x = -50;
            }
            //5
            if (block5.x < 2000) {
                block5.x += speed;
                if (block5.x + block5.width >= hero.x & block5.x + block5.width <= hero.x + hero.width) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block5.x = hero.x - 200;
                    hero.x += 10;
                }
            } else if (block5.x >= 2000) {
                block5.x = -10;
            }

            //from right
            //6
            if (block6.x > 0) {
                block6.x -= speed;
                if (block6.x <= hero.x + hero.width & block6.x >= hero.x) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block6.x = hero.x + hero.width + 200;
                    hero.x -= 10;
                }
            } else if (block6.x <= 0) {
                block6.x = 2500;
            }

            //7
            if (block7.x > 0) {
                block7.x -= speed;
                if (block7.x <= hero.x + hero.width & block7.x >= hero.x) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block7.x = hero.x + hero.width + 200;
                    hero.x -= 10;
                }
            } else if (block7.x <= 0) {
                block7.x = 2500;
            }

            //8
            if (block8.x > 0) {
                block8.x -= speed;
                if (block8.x <= hero.x + hero.width & block8.x >= hero.x) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block8.x = hero.x + hero.width + 200;
                    hero.x -= 10;
                }
            } else if (block8.x <= 0) {
                block8.x = 2900;
            }

            //9
            if (block9.x > 0) {
                block9.x -= speed;
                if (block9.x <= hero.x + hero.width & block9.x >= hero.x) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block9.x = hero.x + hero.width + 200;
                    hero.x -= 10;
                }
            } else if (block9.x <= 0) {
                block9.x = 3000;
            }
            //10
            if (block10.x > 0) {
                block10.x -= speed;
                if (block10.x <= hero.x + hero.width & block10.x >= hero.x) {
                    life--;
                    //end game
                    if (life <= 0) {
                        done = true;
                        end = true;
                    }
                    block10.x = hero.x + hero.width + 200;
                    hero.x -= 10;
                }
            } else if (block10.x <= 0) {
                block10.x = 3500;
            }
            if (score == 10 | score == 20 | score == 30 | score == 40 | score == 50 | score == 60 | score == 70 | score == 80 | score == 90){
                life = life + 5;
                score ++;
                
            }
            if (score >= 150){
                    end = true;
                    done = true;
                }

            if (end == true) {
                endx = WIDTH - WIDTH;
                endy = HEIGHT - HEIGHT;

                t1 = WIDTH / 2 - 30;
                t2 = WIDTH / 2 - 5;
                
                nx1 = WIDTH - WIDTH - 500;
                nx2 = WIDTH/2;
                
                ax1 = WIDTH/2 - 400;
                ax2 = WIDTH/2;
                ax3 = WIDTH/2 - 300;
                ax4 = WIDTH/2;
                
                //x
                gamex1 = 90;
                gamex2 = 195;
                gamex3 = 190;
                gamex4 = 550;
                gamex5 = 540;
                gamex6 = 800;

                gamex7 = 720;
                gamex8 = 710;
                gamex9 = 370;
                gamex10 = 400;
                gamex11 = 200;
                gamex12 = 240;

                //y
                overy1 = 60;
                overy2 = 80;
                overy3 = 55;
                overy4 = 280;
                overy5 = 250;
                overy6 = 440;

                overy7 = 420;
                overy8 = 410;
                overy9 = 300;
                overy10 = 290;
                overy11 = 200;
                overy12 = 195;

            }

            //enemy spawn
            //left side of screen
            enemyx[0] = ex1;
            enemyx[1] = ex2;
            enemyx[2] = ex3;
            enemyx[3] = ex4;
            enemyx[4] = ex5;


            //left side of screen
            enemyx[5] = ex6;
            enemyx[6] = ex7;
            enemyx[7] = ex8;
            enemyx[8] = ex9;
            enemyx[9] = ex10;

            //END SCREEN
            gamex[0] = gamex1;
            gamex[1] = gamex2;
            gamex[2] = gamex3;
            gamex[3] = gamex4;
            gamex[4] = gamex5;
            gamex[5] = gamex6;
            gamex[6] = gamex7;
            gamex[7] = gamex8;
            gamex[8] = gamex9;
            gamex[9] = gamex10;
            gamex[10] = gamex11;
            gamex[11] = gamex12;

            overy[0] = overy1;
            overy[1] = overy2;
            overy[2] = overy3;
            overy[3] = overy4;
            overy[4] = overy5;
            overy[5] = overy6;
            overy[6] = overy7;
            overy[7] = overy8;
            overy[8] = overy9;
            overy[9] = overy10;
            overy[10] = overy11;
            overy[11] = overy12;

            //face 

            //ninja
            //int[x]
            ninjax[0] = block4.x - camx;
            ninjax[1] = block4.x + 30 - camx;
            ninjax[2] = block4.x + block4.width - camx;
            ninjax[3] = block4.x + block4.width - camx;
            ninjax[4] = block4.x - camx;
            ninjax[5] = block4.x - camx;
            ninjax[6] = block4.x + 5 - camx;
            ninjax[7] = block4.x - camx;
            //int[y]
            ninjay[0] = block4.y;
            ninjay[1] = block4.y + 8;
            ninjay[2] = block4.y + 2;
            ninjay[3] = block4.y + 15;
            ninjay[4] = block4.y + 35;
            ninjay[5] = block4.y + 14;
            ninjay[6] = block4.y + 12;
            ninjay[7] = block4.y + 11;

            //worker
            //int [x]
            mustx[0] = block5.x + 15 - camx;
            mustx[1] = block5.x + 10 - camx;
            mustx[2] = block5.x + 20 - camx;
            mustx[3] = block5.x + 30 - camx;
            mustx[4] = block5.x + 40 - camx;
            mustx[5] = block5.x + 35 - camx;
            //int[y]
            musty[0] = block5.y + 30;
            musty[1] = block5.y + 45;
            musty[2] = block5.y + 40;
            musty[3] = block5.y + 40;
            musty[4] = block5.y + 45;
            musty[5] = block5.y + 30;

            hairx[0] = hero.x - camx;
            hairx[1] = hero.x - 9 - camx;
            hairx[2] = hero.x - 6 - camx;
            hairx[3] = hero.x + 20 - camx;
            hairx[4] = hero.x + 38 - camx;
            hairx[5] = hero.x + 48 - camx;
            hairx[6] = hero.x + 53 - camx;
            hairx[7] = hero.x + 58 - camx;
            hairx[8] = hero.x + 60 - camx;
            hairx[9] = hero.x + 53 - camx;
            hairx[10] = hero.x + 38 - camx;
            hairx[11] = hero.x + 33 - camx;
            hairx[12] = hero.x + 36 - camx;
            hairx[13] = hero.x + 13 - camx;

            hairy[0] = hero.y + 21;
            hairy[1] = hero.y + 13;
            hairy[2] = hero.y - 8;
            hairy[3] = hero.y - 12;
            hairy[4] = hero.y - 9;
            hairy[5] = hero.y - 11;
            hairy[6] = hero.y - 10;
            hairy[7] = hero.y - 5;
            hairy[8] = hero.y + 17;
            hairy[9] = hero.y + 25;
            hairy[10] = hero.y + 13;
            hairy[11] = hero.y + 16;
            hairy[12] = hero.y + 22;
            hairy[13] = hero.y + 17;

            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        VideoGame game = new VideoGame();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);

        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
            rReleased = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = false;
            lReleased = true;
        }
    }
}
