/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 *
 * @author janaj4926
 */

// make sure you rename this class if you are doing a copy/paste
public class pong extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    

    //paddle variables
    int p1width = 20;
    int p1height = 120;
    int p1x = 40;
    int p1y = HEIGHT/2 - p1height/2;
    
    int p2width = 20;
    int p2height = 120;
    int p2x = WIDTH - 40 - p2width;
    int p2y = HEIGHT/2 - p2height/2;
    
    
    int paddleSpeed = 10;
    
    //ball variables
    int ballwidth = 20;
    int ballheight = 20;
    int ballx = WIDTH/2 - ballwidth/2;
    int bally = HEIGHT/2 - ballheight/2;
    int balldx = 1; // x direction of ball
    int balldy = 1; // y direction of ball
    int ballspeed = 8;
    
    
    // scores
    int p1score = 0;
    int p2score = 0;
    
    //key press
    boolean p1up = false; 
    boolean p1down = false;
    boolean p2up = false;
    boolean p2down = false;
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.WHITE);
        
        //drawball
        g.fillRect(ballx, bally, ballwidth, ballheight);
        
        //draw p1
        g.fillRect(p1x, p1y, p1width, p1height);
        
        //draw p2
        g.fillRect(p2x, p2y, p2width, p2height);
        
        //draw points
        g.drawString("Player 1: " + p1score, 50, 40);
        g.drawString("Player 2: " + p2score, 120, 40);
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            //move the ball
            ballx += balldx*ballspeed;
            bally += balldy*ballspeed;
            
            //if bottom of ball hits bottom of screen
            if(bally + ballheight > HEIGHT){
                balldy = -1;
            }
            
            if(ballx + ballwidth > WIDTH){
                p1score++;
                // reset ball
                ballx = WIDTH/2 - ballwidth/2;
                bally = HEIGHT/2 - ballheight/2;
            }
            
            if(bally < 0){
                balldy = 1;
            }
            
            if(ballx  < 0){
                p2score++;
                // reset ball
                ballx = WIDTH/2 - ballwidth/2;
                bally = HEIGHT/2 - ballheight/2;
            }
            
            //score
            if(p1score == 10){
                done = true;
            }
            if(p2score == 10){
                done = true;
            }
            
            // move paddles
            if(p1up){
                p1y -= paddleSpeed;
            }else if(p1down){
                p1y += paddleSpeed;
            }
            
            if(p2up){
                p2y -= paddleSpeed;
            }else if(p2down){
                p2y += paddleSpeed;
            }
            
            // collisions
            // p1
            if(!(ballx + ballwidth < p1x || ballx > p1x + p1width || bally > p1y + p1height || bally + ballheight < p1y)){
                balldx = 1;
            }
            //p2
            if(!(ballx + ballwidth < p2x || ballx > p2x + p2width || bally > p2y + p2height || bally + ballheight < p2y)){
                balldx = -1;
            }
            
            

            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
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
        pong game = new pong();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
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
        if(key == KeyEvent.VK_W){
            p1up = true;
        }
        if(key == KeyEvent.VK_S){
            p1down = true;
        }
        
        if(key == KeyEvent.VK_UP){
            p2up = true;
        }
        if(key == KeyEvent.VK_DOWN){
            p2down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            p1up = false;
        }
        if(key == KeyEvent.VK_S){
            p1down = false;
        }
        
        if(key == KeyEvent.VK_UP){
            p2up = false;
        }
        if(key == KeyEvent.VK_DOWN){
            p2down = false;
        }
    }
}
