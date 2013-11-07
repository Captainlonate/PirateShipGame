
package practice_ball_control;

import java.awt.*;
import java.util.Random;
import javax.swing.*;


public class SharkEnemy {
        
    // x and y coordinates where the ship is located
    private int xCoord = -100, yCoord = 250, centerX = 0, centerY = 0, 
                health = 5, steps = 0;
    // Have they reached their final spot
    boolean done = false, offScreen = false, dead = false, shouldRemove = false,
            inPlace = false;
    // Height and width of the ship
    private int Height, Width;
    // Random number generator
    Random rnd = new Random();
    //Ship related images
    private ImageIcon blowup1 = new ImageIcon("img/blownup1.png");
    private ImageIcon blowup2 = new ImageIcon("img/blownup3.png");
    private ImageIcon image = new ImageIcon("img/fin1.png");  
    private ImageIcon healthBar = new ImageIcon("img/fullhealth.png");
    private ImageIcon nohealth = new ImageIcon("img/nohealth.png");
    private ImageIcon onehealth = new ImageIcon("img/onehealth.png");
    private ImageIcon twohealth = new ImageIcon("img/twohealth.png");
    private ImageIcon threehealth = new ImageIcon("img/threehealth.png");
    private ImageIcon fourhealth = new ImageIcon("img/fourhealth.png");
    private ImageIcon fullhealth = new ImageIcon("img/fullhealth.png");
    private ImageIcon finImage1 = new ImageIcon("img/fin1.png");
    private ImageIcon finImage2 = new ImageIcon("img/fin2.png");
    private ImageIcon finImage1R = new ImageIcon("img/fin1R.png");
    private Image sharkImage = image.getImage();
    private Image healthImage = healthBar.getImage();
    private char orientation;
    // The speed that the ship moves
    private int speed = 1;
    
    
    
    /**
     * The primary constructor for a TypeOneEnemy. It sets the enemy's sequence, 
     * which determines if this particular object is one of the initial 3 enemies
     * that are created. These initial 3 are special because they each have
     * a unique starting position, and they parttake in a scene where the 
     * three "talk" to each other using dialog boxes. After these three are 
     * killed or leave the screen, all remaining typetwoenemies are created
     * with a sequence of 4, meaning they just move about the screen in an 
     * "attacking" motion.
     * @param sequence int sequence will become this object's priority variable
     */
    public SharkEnemy(){
        // Determine the enemy's size
        setHeight(image.getIconHeight());
        setWidth(image.getIconWidth());
    }
    
    /**
     * Function that handles all of the drawing of a shark object and it's
     * corresponding health bar.
     * @param g Graphics object that performs the drawing
     */
    public void paintComponent(Graphics g){
        // Tell the shark to draw itself
        g.drawImage(getImage(), getxCoord(), getyCoord(), null);
        g.drawImage(getHealthImage(), getxCoord(), getyCoord()-20, null);        
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
    public void move(int shipx, int shipy){ 
        if(getxCoord() < shipx+30){
            this.setImageIcon(finImage1R);
            setxCoord(getxCoord() + speed);
        }
        if(getxCoord() > shipx+30){
            this.setImageIcon(finImage1);
            setxCoord(getxCoord() - speed);
        }
        if(getyCoord() < shipy+80){
            setyCoord(getyCoord() + speed);
        }
        if(getyCoord() > shipy+80){
            setyCoord(getyCoord() - speed);
        }
    }
             
    /**
     * Function that brings the shark onto the panel
     */
    public void initialSharkMove(){
        this.setImageIcon(finImage1R);
        if (getxCoord() + (speed+2) < 90) {
            setxCoord(getxCoord() + (speed+2));
        } else {
            inPlace = true;
        }

    }
    
    /**
     * Function that returns this object's done variable
     * @return returns boolean done
     */
    public boolean getDone(){
        return this.done;
    }
    
    public boolean getInPlace(){
        return this.inPlace;
    }
    
    /**
     * Function that sets the dead boolean variable to a parameter
     * @param dead soon to be shark object's dead variable
     */
    public void setDead(boolean dead){
        this.dead = dead;
    }
    
    /**
     * Function that reduces the sharks health by 1 and updates the health
     * bar image
     */
    public void updateHealth(){
        takeDamage();
        
        switch (health) {
            case 0: {
                setHealthIcon(nohealth);
                break;
            }
            case 1: {
                setHealthIcon(onehealth);
                break;
            }
            case 2: {
                setHealthIcon(twohealth);
                break;
            }
            case 3: {
                setHealthIcon(threehealth);
                break;
            }
            case 4: {
                setHealthIcon(fourhealth);
                break;
            }
            case 5: {
                setHealthIcon(fullhealth);
                break;
            }
        }
        
        if(health == 0){
            setDead(true);
        }
        
    }
    
    /**
     * function that returns true when it's time to remove the shark from the
     * panel
     * @return boolean that is true when all steps have been completed
     */
    public boolean getShouldRemove(){
        
        switch(steps){
            case 0:{
                System.out.println("step 0");
                setImageIcon(blowup1);
                steps++;
                break;
            }
            case 1:{
                System.out.println("step 1");
                setImageIcon(blowup2);
                steps++;
                break;
            }
            case 2:{
                System.out.println("time to remove");
                shouldRemove = true;
            }
        }
        return shouldRemove;
    }
    
    /**
     * Function that decrements the shark's health variable 
     */
    public void takeDamage(){
        health--;
    }
    
    /**
     * function that returns the value of the boolean variable "dead"
     * @return returns shark object's dead variable
     */
    public boolean getDead(){
        return dead;
    }
       
      /**
    * Function that returns the center x of the missile
    * PRECONDITION: No precondition
    * @return returns int center X
    */
   public int getCenterx(){
       centerX = ( getxCoord() + (this.getWidth()/2) );
       return this.centerX;
   }
   
   /**
    * Function that returns the center y of the missiles
    * PRECONDITION: No precondition
    * @return returns int cent Y
    */
   public int getCentery(){
       centerY = ( getyCoord() + (this.getHeight()/2) );
       return this.centerY;
   }
    
    /**
     * sets done to parameter done
     * @param done boolean that is soon to be this object's done variable
     */
    public void setDone(boolean done){
        this.done = done;
    }
        
    public void setHealthIcon(ImageIcon icon){
        this.healthBar = icon;
    }
    
    public Image getHealthImage(){
        healthImage = healthBar.getImage();        
        return healthImage;        
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
        this.sharkImage = image;
    }
    
    public Image getImage(){
        sharkImage = image.getImage();
        
        return sharkImage;
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
