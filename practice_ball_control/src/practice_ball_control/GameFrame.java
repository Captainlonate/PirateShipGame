package practice_ball_control;

import java.awt.*;       // Old library classes, you still need them
import javax.swing.*;    // New library classes, Swing extends AWT


/**
 * A GameFrame is the "outer shell" / JFrame that contains the game panel.
 * This JFrame is what actually runs the infinite while loop to catalize
 * the painting / pausing / moving of the game. It is the "animation" , so to 
 * speak. The GameFrame  controls the game's window size, title, and other such
 * features.
 * @author Sarat
 */
public class GameFrame extends JFrame {
        
    // Width and Height of the window
    private static final int WINDOW_WIDTH = 1440;
    private static final int WINDOW_HEIGHT = 800;
    
    // boolean
    boolean endTheGame = true;
    
    // The JPanel extension that harbors all of the action
    private GamePanel gamePanel;
    
        
    /**
     * Primary GameFrame constructor.
     * PRECONDITION: No precondition
     * POSTCONDITION: A new GameFrame will be created
     */
    public GameFrame() {
        // set the title on the frame
        super("Boat Game!!!");  
        
        // Builds the Frame and adds the Game Panel to it
        buildFrame();
        
        // infinite animation loop, program halts when window is closed.
        while (true) {
            pause(); 
            gamePanel.move();
            gamePanel.repaint();
            if (endTheGame) {
                if (gamePanel.getGameWon() == true) {
                    this.dispose();
                    new WinScreen();
                    endTheGame = false;
                } else if (gamePanel.getGameLost() == true) {
                    this.dispose();
                    new GameOverScreen();
                    endTheGame = false;
                }
            }
            
            
        }
    }
    
    /**
     * Function that builds the main window. It initializes many housekeeping
     * features, then creates and adds the game panel to the JFrame.
     * Finally it sets the visibility of the JFrame to true.
     */
    public void buildFrame(){
        // Some basic housekeeping
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLayout( new BorderLayout() );
        
        // I do not want the screen to be resized.
        setResizable(false);
        
        // builds and adds the gamepanel
        gamePanel = new GamePanel();
        add(gamePanel);
        addKeyListener(gamePanel);
        center(this);
        
        // allows the JFrame to be visible
        setVisible(true);
    }
    
    /**
     * Function that pauses the currently running gui thread for a temporary 
     * period. The purpose of doing this is to "slow" down the game.
     * Without pause, a movement of the ship would essentially make it 
     * move so fast you couldn't control it.
     */
    public static void pause()
    {
        try {
            Thread.sleep(20); // pause for 20 ms
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    /**
     * Function that centers the JFrame on the screen
     * @param frame the current JFrame object that is to be centered
     */
    public static void center(JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        
        int w = frame.getWidth();
        int h = frame.getHeight();
        
        int x = center.x - w/2, y = center.y - h/2;
        frame.setBounds(x, y, w, h);
        frame.validate();
    }
    
}
