
package practice_ball_control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 * A ControlPanel is an extension of a JPanel that contains the prompt at
 * the beginning of the game. This prompt instructs the user on how to move,
 * fire, and what their goals should be. It provides ane enhanced mouse
 * cursor image, and provides several buttons for navigation.
 * @author Sarat
 */
public class ControlPanel extends JPanel {
    // booleans
    boolean firstPage = true, secondPage = false, thirdPage = false,
            finished = false;
    // Buttons
    JButton navigate, ready;
    // Local variables
    private int width = 0, height = 0;
    // Images
    ImageIcon nextIcon = new ImageIcon("img/next.png");
    ImageIcon backIcon = new ImageIcon("img/back.png");
    ImageIcon readyIcon = new ImageIcon("img/ready.png");
    ImageIcon backgroundImage2 = new ImageIcon("img/background1.png");
    ImageIcon backgroundImage = new ImageIcon("img/SSvStoryv1.png");
    ImageIcon backgroundImage3 = new ImageIcon("img/SSvInstv1.png");
    ImageIcon storyIcon = new ImageIcon("img/SSvStoryv1.png");
    Image background = backgroundImage.getImage();
    Image background2 = backgroundImage2.getImage();
    Image background3 = backgroundImage3.getImage();
        
    // Changes the appearance of the mouse cursor
    Image parrotCursor = new ImageIcon("img/pirateParrot.png").getImage();
    final Point hotspot = new Point(0, 0);
    final String name = "My Cursor";
    
    
    
    /**
     * The primary constructor for an ControlsPanel
     * PRECONDITION: No precondition
     * POSTCONDITION: A new ControlPanel object will be created.
     */
    public ControlPanel(int width, int height){
        // official width of the panel, for later use
        this.width = width;
        this.height = height;
        
        // Build the controls and instructions panel
        buildPanel();
    }
    
    /**
     * Function that builds the ControlPanel. It modifies the
     * background picture, adds buttons, changes the mouse cursor image,
     * and such.
     */    
    public void buildPanel(){
        
        // necessary for button.setBounds
        setLayout(null);
        
        // changes the appearance of the mouse cursor
        setCursor(getToolkit().createCustomCursor(parrotCursor, hotspot, name));
        
        // build the navigate button ( the right one )
        navigate = new JButton();
        navigate.setActionCommand("next");
        navigate.addActionListener(new buttonListener());
        navigate.setBackground(Color.decode("#CCB489"));
        navigate.setBorder(null);
        navigate.setBorderPainted(false);
        navigate.setContentAreaFilled(false);
        navigate.setFocusable(false);
        navigate.setIcon(nextIcon);
        navigate.setBounds(width-150, height-110, 120, 50);
        
        
        // build the ready button ( the left button )
        ready = new JButton();
        ready.setActionCommand("ready");
        ready.addActionListener( new buttonListener() );
        ready.setBackground(Color.decode("#CCB489"));
        ready.setBorder(null);
        ready.setFocusable(false);
        ready.setContentAreaFilled(false);
        ready.setBorderPainted(false);
        ready.setIcon(readyIcon);
        ready.setBounds(50, height-110, 120, 50);
        ready.setVisible(false);
        
        // add the buttons to the panel
        this.add(navigate);
        this.add(ready);
    }
    
    /**
     * Function that handles the drawing of the background images,
     * which depends on if the user is looking at the "movement" screen, 
     * or if they are looking at the "instructions" screen.
     * @param g Graphics object that handles drawing
     */
    public void paintComponent(Graphics g){
        // Draw the background image on the panel
        if(firstPage){
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        else if(secondPage){
            g.drawImage(background2, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        else if(thirdPage){
            g.drawImage(background3, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        repaint();
    }

    /**
     * Function that returns whether or not the user has clicked the ready button
     * PRECONDITION: No Precondition.
     * POSTCONDITION: No post condition.
     * @return returns object's boolean finish variable
     */
    public boolean getFinished(){        
        return this.finished;
    }
    
    /**
     * Function that sets whether or not the user has clicked the ready button
     * @param finished boolean finished that will become object's finished variable
     */
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    
    /**
     * Class that implements ActionListener interface. The purpose of this 
     * class is to handle what happens when the user clicks any of the three
     * buttons on the Instructions splash screen
     */
    public class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(e.getActionCommand().equals("next")){
                navigate.setActionCommand("forward");
                navigate.setIcon(nextIcon);
                ready.setActionCommand("backfirst");
                ready.setIcon(backIcon);
                ready.setVisible(true);
                firstPage = false;
                secondPage = true;
            }
            else if(e.getActionCommand().equals("backfirst")){                
                navigate.setActionCommand("next");
                navigate.setIcon(nextIcon);
                ready.setVisible(false);
                firstPage = true;
                secondPage = false;
            }
            else if(e.getActionCommand().equals("backsecond")){                
                navigate.setActionCommand("forward");
                navigate.setIcon(nextIcon);
                ready.setActionCommand("backfirst");
                thirdPage = false;
                secondPage = true;
            }
            else if(e.getActionCommand().equals("forward")){  
                navigate.setActionCommand("ready");
                navigate.setIcon(readyIcon);
                ready.setIcon(backIcon);
                ready.setActionCommand("backsecond");
                secondPage = false;
                thirdPage = true;
            }
            else if(e.getActionCommand().equals("ready")){  
                setFinished(true);
            }
        }// end action performed        
    }// end buttonListener    
    
}
