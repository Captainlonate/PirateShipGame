
package practice_ball_control;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;

/**
 * A sounds object is exactly what it says. This object handles all of the 
 * sounds that the game might feature.
 * @author Sarat
 */
public class Sounds {
    
    
    
    private FileInputStream file;
    private AudioStream sound;
    
    
    /**
     * Primary constructor for a sound object
     */
    public Sounds(){
        
    }
    
    /**
     * Function that is called when it's time to create a sound effect
     * for a cannonball.
     */
    public void shotFired(){
        try { 
            file = new FileInputStream("sound/cannonshot.wav");             
        } catch (FileNotFoundException ex) {
            System.out.println("file not found apparently");
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
                System.out.println("io exception in sound");
        }
            
        
        try {
            sound = new AudioStream(file);         
            // My sound file had like, a 1 second delay, which sounded bad
            // So these skips skip right up until the beginning of the fire
            // Also, it's necessary to have all 4, a single 380,000 won't work
            sound.skip(100000);
            sound.skip(100000);
            sound.skip(100000);
            sound.skip(80000);
        } catch (IOException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AudioPlayer.player.start(sound);
    }// end shotFired()
    
    /**
     * Function that is called when it's time to play the background music
     * for the game.
     */
    public void music(){
        
        try {      
            file = new FileInputStream("sound/dragonroost.wav"); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
        }
            
        
        try {
            sound = new AudioStream(file);  
        } catch (IOException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AudioPlayer.player.start(sound);
    }// end music()
    
}// end class sounds
