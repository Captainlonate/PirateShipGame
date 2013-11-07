
package practice_ball_control;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * A TypeOneEnemy is similar to a pirate Ship (class Ship), but it is not
 * player controlled. TypeOneEnemy objects are enemy ships that move around the
 * game panel in various ways. They have a scene where they talk to each
 * other, then they begin an "attack phase" where they move and can be shot.
 * @author Sarat
 */
public class TypeOneEnemy {
    // Steps to blow up
    private int steps = 0;
    private boolean sunk = false;
    // the enemies priority
    private int priority;
    // x and y coordinates where the ship is located
    private int xCoord = 725, yCoord = 425;
    // boundaries
    private int topBoundary = 64, bottomBoundary = 235;
    private int leftBoundary = 492, rightBoundary = 860;
    // Have they reached their final spot
    boolean done = false, offScreen = false;
    // Height and width of the ship
    private int Height, Width;
    // Random number generator
    Random rnd = new Random();
    //Ship related images
    private ImageIcon blowup1 = new ImageIcon("img/blownup1.png");
    private ImageIcon blowup2 = new ImageIcon("img/blownup3.png");    
    private ImageIcon image = new ImageIcon("img/enemyLeft.png");
    private ImageIcon leftBoat = new ImageIcon("img/enemyLeft.png");
    private ImageIcon rightBoat = new ImageIcon("img/enemyRight.png");
    private ImageIcon upBoat = new ImageIcon("img/enemyUp.png");
    private ImageIcon downBoat = new ImageIcon("img/enemyDown.png");
    private ImageIcon boatDownLeft = new ImageIcon("venemyDownLeft.png");
    private ImageIcon boatUpLeft = new ImageIcon("img/enemyUpLeft.png");
    private ImageIcon boatRightDown = new ImageIcon("img/enemyDownRight.png");
    private ImageIcon boatRightUp = new ImageIcon("img/enemyUpRight.png");
    private Image boat = image.getImage();
    private char orientation;
    // The speed that the ship moves
    private int speed = 3;
    
    
    
    /**
     * The primary constructor for a TypeOneEnemy. It sets the enemy's sequence, 
     * which determines if this particular object is one of the initial 3 enemies
     * that are created. These initial 3 are special because they each have
     * a unique starting position, and they parttake in a scene where the 
     * three "talk" to each other using dialog boxes. After these three are 
     * killed or leave the screen, all remaining typeoneenemies are created
     * with a sequence of 4, meaning they just move about the screen in an 
     * "attacking" motion.
     * @param sequence int sequence will become this object's priority variable
     */
    public TypeOneEnemy(int sequence){
        // Is this enemy one of the initial 3?
        this.priority = sequence;
        
        // Determine the enemy's initial position
        setLocationFromPriority();
        
        // Determine the enemy's size
        setHeight(image.getIconHeight());
        setWidth(image.getIconWidth());
    }
    
    /**
     * Function that controls changing the object's x and y coordinates, 
     * depending on what the object's priority is currently set to.
     * If the object is among the initial 3, a particular movement sequence
     * is called, but if the object is not among the initial 3, then
     * it moves in an "attacking" motion.
     * PRECONDITION: Object has been successfully created
     * POSTCONDITION: x and y coordinates will change
     */
    public void move(){ 
        // Is this enemy one of the inital 3
        switch(priority){
            case 1:
            case 2:
            case 3:{
                initialEnemyMove();
                break;
            }
            case 4:{
                attackingMove();
                break;
            }
        }
    }
    
    
    /**
     * Function that controls how the ship moves if the attackingMove phase is
     * initiated.
     * POSTCONDITION: the objects xcoordinate is lessened
     */
    public void attackingMove(){ 
        
        if ((getxCoord() - speed < rightBoundary && getxCoord() - speed > leftBoundary)
                && (getyCoord() < bottomBoundary && getyCoord() > topBoundary)) {
            if(getyCoord() < 117){
                setImageIcon(upBoat);
                setyCoord(getyCoord() - (speed));
            }
            else if(getyCoord() >= 117){
                setImageIcon(downBoat);
                setyCoord(getyCoord() + (speed));
            }
            
        } else {
            setImageIcon(leftBoat);
            setxCoord(getxCoord() - speed);
        }

    }
        
    /**
     * Function that checks to see if the object is out of sight ( has moved off
     * off the screen).
     * @return returns boolean offScreen, true if the ship is no longer in sight
     */
    public boolean getOffScreen(){
        
        if(getxCoord() < -200){
            offScreen = true;
        }
        return offScreen;
        
    }
    
    
    /*
     * Function that allows a boat to blow up in three steps. For each step,
     * the image of the boat is changed to a "blowing up" and "blowing up more"
     * image.
     * Finally on the last step, a boolean of "sunk" is set to true
     * so that an external function can check if(sunk) and remove the 
     * boat if true.
     */
    public void sinkItself(){
        
        switch(steps){
            
            case 0:{
                setImageIcon(blowup1);
                steps++;
                break;
            }
            case 1:{
                setImageIcon(blowup2);
                steps++;
                break;
            }
            case 2:{
                sunk = true;
            }
            
        }// end switch
        
    }
    
    /**
     * Function that returns true if the boat is officially sunk
     * @return boolean sunk
     */
    public boolean getSunk(){
        return this.sunk;
    }
    
    /**
     * Function that returns this object's done variable
     * @return returns boolean done
     */
    public boolean getDone(){
        return this.done;
    }
    
    /**
     * Function that handles the updating of this object's x and y coordinates
     * based on what priority the enemy ship is. ( ie is it among the initial
     * three enemies that have the special sequence? )
     */
    public void initialEnemyMove(){
        // Is this enemy one of the initial 3
        switch(priority){
            case 1:{
                if(getxCoord()-speed > 1250){
                    setxCoord(getxCoord() - speed);
                }
                else{
                    done = true;
                }
                
                break;
            }
            case 2:{
                if(getxCoord()-speed > 1150){
                    setxCoord(getxCoord() - speed);
                }
                else{
                    done = true;
                }
                break;
            }
            case 3:{
                if(getxCoord()-speed > 1050){
                    setxCoord(getxCoord() - speed);
                }
                else{
                    done = true;
                }
                break;
            }            
        }
    }
    
    /**
     * 
     * @param priority 
     */
    public void setPriority(int priority){
        this.priority = priority;
    }
            
    /**
     * 
     * @param done 
     */
    public void setDone(boolean done){
        this.done = done;
    }
    
    /**
     * 
     */
    public void setLocationFromPriority(){
        
        switch(priority){
            case 1:{
                setxCoord(1600);
                setyCoord(40);
                break;
            }
            case 2:{
                setxCoord(1640);
                setyCoord(90);
                break;
            }
            case 3:{
                setxCoord(1680);
                setyCoord(140);
                break;
            }
            case 4:{
                setxCoord(1680);
                int y = rnd.nextInt(660) + 20;
                setyCoord(y);
                break;
            }
                case 5:{
                setxCoord(580);                
                setyCoord(600);
                break;
            }
        }
        
    }
    
    public void paintComponent(Graphics g){
        // Tell the enemy ship to draw itself
        g.drawImage(getImage(), getxCoord(), getyCoord(), null);
    }
    
    public int getHeight() {
        return Height;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getWidth() {
        return Width;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
    public void setImage(Image image) {
        this.boat = image;
    }
    
    public Image getImage(){
        boat = image.getImage();
        return boat;
    }
    
    public ImageIcon getImageIcon() {
        return image;
    }
    
    public char getOrientation(){
        return this.orientation;
    }
    
    public void setImageIcon(ImageIcon image) {
        this.image = image;
    }
}
