/**
 * This is the property of Nathaniel Lough
 * This assignment was written for CS 241 - Project 3
 * It was written on and around 5/18/2012
 */
package practice_ball_control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * An InstructionScreen is a JFrame extension that contains the 
 * control panel. This JFrame is what provides the "outer shell" to the 
 * controls / instructions display. It controls the splash screen's 
 * size, title and other such features. 
 * @author Sarat
 */
public class InstructionScreen extends JFrame{
    // Local variables
    private static final int WINDOW_WIDTH = 600, WINDOW_HEIGHT=800;
    // Panels
    ControlPanel mainPanel;        
    JButton button223232;
    /**
     * The primary constructor for an InstructionScreen
     * PRECONDITION: No precondition
     * POSTCONDITION: A new InstructionScreen will be created
     */
    public InstructionScreen(){
        Sounds backgroundMusic = new Sounds();
        backgroundMusic.music();
        
        // build the primary JFrame
        buildFrame();
        
        // Build the main panel
        buildMainPanel();
        
        // add the main panel to the Frame
        add(mainPanel);
        
        // allow the Frame to be displayed
        setVisible(true);
        
        // Didn't want to use window listener
        while(true){
            // If the user has clicked "Ready" button, then blow away the frame
            // and spawn the GameFrame
            if(mainPanel.getFinished()){
                this.dispose();
                new GameFrame();
            }
            /**
             * This thread.sleep is necessary. If not present it will not
             * be able to recognize when mainpanel.getFinished is true.
             */
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }// end catch
        }// end while
    }// end constructor
    
    /**
     * Function that builds the InstructionScreen Frame. It handles basic
     * housekeeping procedures and get's the JFrame underway.
     */
    public void buildFrame(){
        // Some basic housekeeping
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Gameplay basics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // I do not want this window to be resized
        setResizable(false);
    }// end buildFrame
    
    /**
     * Function that builds the instructions screen (ControlPanel)
     * and adds this instructions prompt to the JFrame.
     */
    public void buildMainPanel(){
        mainPanel = new ControlPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
    }// end buildMainPanel
    
    
    
    
    /**
     * This is the MAIN METHOD that runs the entire program.
     * It's only task is to create a new Instructions prompt,
     * from that point on, everything else is chained together.
     * @param args A string array of command line arguments 
     *    There should not be any such command line arguments for this program
     */
    public static void main(String[] args){
        // Start the program by creating an Instructions prompt
        new InstructionScreen();        
    }// end main
}// end class
