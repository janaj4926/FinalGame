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
import static java.awt.image.ImageObserver.ERROR;

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
    long desiredFPS = 50;
    long desiredTime = (1000) / desiredFPS;
    // One finger death punch
    boolean left = false, right = false, lReleased = true, rReleased = true;
    
    
    //stuff
    Rectangle floor = new Rectangle(0, 500, 2000, 100);
    //enemy
    //enemy x
    //left
    //group 1
    int ex1 = 0;
    int ex2 = 0;
    //group 2
    int ex3 = 0;
    int ex4 = 0;
    //group 3
    int ex5 = 0;
    
    //right
    //group 4
    int ex6 = 1950;
    int ex7 = 1950;
    //group 5
    int ex8 = 1950;
    int ex9 = 1950;
    //group 6
    int ex10 = 1950;
    
    int [] enemyx = {ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10};
    
    //enemy y
    
    //enemy guys
    Rectangle block1 = new Rectangle(enemyx[0], 450, 50, 50);
    Rectangle block2 = new Rectangle(enemyx[1], 450, 50, 50);
    Rectangle block3 = new Rectangle(enemyx[2], 450, 50, 50);
    Rectangle block4 = new Rectangle(enemyx[3], 450, 50, 50);
    Rectangle block5 = new Rectangle(enemyx[4], 450, 50, 50);
    
    Rectangle block6 = new Rectangle(enemyx[5], 450, 50, 50);
    Rectangle block7 = new Rectangle(enemyx[6], 450, 50, 50);
    Rectangle block8 = new Rectangle(enemyx[7], 450, 50, 50);
    Rectangle block9 = new Rectangle(enemyx[8], 450, 50, 50);
    Rectangle block10 = new Rectangle(enemyx[9], 450, 50, 50);
    //player
    Rectangle hero = new Rectangle(875, 450, 50, 50);
    //movement
    //enemy
    int speed = 5;
    //camera
    int camx = 0;
    
    //score
    int score = 0;
    //life
    int life = 5;
    
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
        
        //score
        g.setColor(Color.WHITE);
        g.drawString("ENEMY'S TAKEN DOWN: " + score ,WIDTH/2 - 60 ,50 );
        g.drawString("hp: " + life ,WIDTH/2, 100 );

        //evil
        g.setColor(Color.RED);
        g.fillRect(block1.x - camx, block1.y, block1.width, block1.height);
        g.fillRect(block2.x - camx, block2.y, block2.width, block2.height);
        g.fillRect(block3.x - camx, block3.y, block3.width, block3.height);
        g.fillRect(block4.x - camx, block4.y, block4.width, block4.height);
        g.fillRect(block5.x - camx, block5.y, block5.width, block5.height);
        g.fillRect(block6.x - camx, block6.y, block6.width, block6.height);
        g.fillRect(block7.x - camx, block7.y, block7.width, block7.height);
        g.fillRect(block8.x - camx, block8.y, block8.width, block8.height);
        g.fillRect(block9.x - camx, block9.y, block9.width, block9.height);
        g.fillRect(block10.x - camx, block10.y, block10.width, block10.height);

        //hero
        g.setColor(Color.BLUE);
        g.fillRect(hero.x - camx, hero.y, hero.width, hero.height);

        g.setColor(Color.white);
        //left target (fist)
        g.drawLine(hero.x - 150 - camx, 490, hero.x - 110 - camx, 490);
        g.drawLine(hero.x - 150 - camx, 490, hero.x - 150 - camx, 460);
        g.drawLine(hero.x - 150 - camx, 460, hero.x - 120 - camx, 450);

        //right target (fist)
        g.drawLine(hero.x + 51 + 110 - camx, 490, hero.x + 51 + 150 - camx, 490);
        g.drawLine(hero.x + 51 + 150 - camx, 490, hero.x + 51 + 150 - camx, 460);
        g.drawLine(hero.x + 51 + 150 - camx, 460, hero.x + 51 + 120 - camx, 450);

        //floor
        g.setColor(Color.BLACK);
        g.fillRect(floor.x - camx, floor.y, floor.width, floor.height);

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

            //movement
            //hero
            if (right && rReleased) {
                rReleased = false;
                hero.x += 150;

                //right side collisions
                if (block1.x < hero.x + hero.width & block1.x > hero.x - 150) {
                    score++;
                    block1.y = 440;
                }
                if (block2.x < hero.x + hero.width & block2.x > hero.x - 150) {
                    score++;
                    block2.y = 440;
                }
                if (block3.x < hero.x + hero.width & block3.x > hero.x - 150) {
                    score++;
                    block3.y = 440;
                }
                if (block4.x < hero.x + hero.width & block4.x > hero.x - 150) {
                    score++;
                    block4.y = 440;
                }
                if (block5.x < hero.x + hero.width & block5.x > hero.x - 150) {
                    score++;
                    block5.y = 440;
                }
                if (block6.x < hero.x + hero.width & block6.x > hero.x - 150) {
                    score++;
                    block6.y = 440;
                }
                if (block7.x < hero.x + hero.width & block7.x > hero.x - 150) {
                    score++;
                    block7.y = 440;
                }
                if (block8.x < hero.x + hero.width & block8.x > hero.x - 150) {
                    score++;
                    block8.y = 440;
                }
                if (block9.x < hero.x + hero.width & block9.x > hero.x - 150) {
                    score++;
                    block9.y = 440;
                }
                if (block10.x < hero.x + hero.width & block10.x > hero.x - 150) {
                    score++;
                    block10.y = 440;
                }
                
                //edge of screen
                if (hero.x >= 2000 - hero.width) {
                    hero.x = 2000 - hero.width;
                }
                
                
                
            } else if (left && lReleased) {
                lReleased = false;
                hero.x -= 150;
                
                //left side collisions
                if (block1.x + block1.width > hero.x & block1.x + block1.width < hero.x + 150) {
                    score++;
                    block1.y = 440;
                }
                if (block2.x + block2.width > hero.x & block2.x + block2.width < hero.x + 150) {
                    score++;
                    block2.y = 440;
                }
                if (block3.x + block3.width > hero.x & block3.x + block3.width < hero.x + 150) {
                    score++;
                    block3.y = 440;
                }
                if (block4.x + block4.width > hero.x & block4.x + block4.width < hero.x + 150) {
                    score++;
                    block4.y = 440;
                }
                if (block5.x + block5.width > hero.x & block5.x + block5.width < hero.x + 150) {
                    score++;
                    block5.y = 440;
                }
                if (block6.x + block6.width > hero.x & block6.x + block6.width < hero.x + 150) {
                    score++;
                    block6.y = 440;
                }
                if (block7.x + block7.width > hero.x & block7.x + block7.width < hero.x + 150) {
                    score++;
                    block7.y = 440;
                }
                if (block8.x + block8.width > hero.x & block8.x + block8.width < hero.x + 150) {
                    score++;
                    block8.y = 440;
                }
                if (block9.x + block9.width > hero.x & block9.x + block9.width < hero.x + 150) {
                    score++;
                    block9.y = 440;
                }
                if (block10.x + block10.width > hero.x & block10.x + block10.width < hero.x + 150) {
                    score++;
                    block10.y = 440;
                }
                
                //edge of screen
                if (hero.x <= 0) {
                    hero.x = 0;
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
            if (block1.x < 2000 & block1.x >= 0){
                block1.x += speed;
                if (block1.x + block1.width == hero.x){
                    life--;
                    block1.x = hero.x - 150;
                    hero.x += 5;
                }
            }
            //2
            if (block2.x < 2000 & block2.x >= 0){
                block2.x += speed;
                if (block2.x + block2.width == hero.x){
                    life--;
                    block2.x = hero.x - 150;
                    hero.x += 5;
                }
            }
            //3
            if (block3.x < 2000 & block3.x >= 0){
                block3.x += speed;
                if (block3.x + block3.width == hero.x){
                    life--;
                    block3.x = hero.x - 150;
                    hero.x += 5;
                }
            }
            //4
            if (block4.x < 2000 & block4.x >= 0){
                block4.x += speed;
                if (block4.x + block4.width == hero.x){
                    life--;
                    block4.x = hero.x - 150;
                    hero.x += 5;
                }
            }
            //5
            if (block5.x < 2000 & block5.x >= 0){
                block5.x += speed;
                if (block5.x + block5.width == hero.x){
                    life--;
                    block5.x = hero.x - 150;
                    hero.x += 5;
                }
            }
            
            //from right
            //6
            if (block6.x < 2000 & block6.x >= 0){
                block6.x -= speed;
                if (block6.x == hero.x + hero.width){
                    life--;
                    block6.x = hero.x + hero.width + 150;
                    hero.x -= 5;
                }
            }
            //7
            if (block7.x < 2000 & block7.x >= 0){
                block7.x -= speed;
                if (block7.x == hero.x + hero.width){
                    life--;
                    block7.x = hero.x + hero.width + 150;
                    hero.x -= 5;
                }
            }
            //8
            if (block8.x < 2000 & block8.x >= 0){
                block8.x -= speed;
                if (block8.x == hero.x + hero.width){
                    life--;
                    block8.x = hero.x + hero.width + 150;
                    hero.x -= 5;
                }
            }
            //9
            if (block9.x < 2000 & block9.x >= 0){
                block9.x -= speed;
                if (block9.x == hero.x + hero.width){
                    life--;
                    block9.x = hero.x + hero.width + 150;
                    hero.x -= 5;
                }
            }
            //10
            if (block10.x < 2000 & block10.x >= 0){
                block10.x -= speed;
                if (block10.x == hero.x + hero.width){
                    life--;
                    block10.x = hero.x + hero.width + 150;
                    hero.x -= 5;
                }
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
