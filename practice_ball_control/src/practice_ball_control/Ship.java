
package practice_ball_control;

import java.awt.*;
import javax.swing.*;
import java.util.*;



/**
 * A ship is the player controlled boat that fires cannons and drives around.
 * It has 8-way movement, and controls it's own boundaries.
 * i.e., it cannot go off the screen, or on land
 * @author Sarat
 */
public class Ship {    
    // x and y coordinates where the ship is located
    private int xCoord = 725, yCoord = 425;
    // boundaries
    private int topBoundary = 64, bottomBoundary = 235;
    private int leftBoundary = 492, rightBoundary = 860;
    // Height and width of the ship
    private int Height, Width;
    //Ship related images
    private ImageIcon image = new ImageIcon("img/shipRight.png");
    private ImageIcon leftBoat = new ImageIcon("img/shipLeft.png");
    private ImageIcon rightBoat = new ImageIcon("img/shipRight.png");
    private ImageIcon upBoat = new ImageIcon("img/shipUp.png");
    private ImageIcon downBoat = new ImageIcon("img/shipDown.png");
    private ImageIcon boatDownLeft = new ImageIcon("img/shipDownLeft.png");
    private ImageIcon boatUpLeft = new ImageIcon("img/shipUpLeft.png");
    private ImageIcon boatRightDown = new ImageIcon("img/shipRightDown.png");
    private ImageIcon boatRightUp = new ImageIcon("img/shipRightUp.png");    
    private Image boat = image.getImage();
    //private Image boat = image.getImage();
    private char orientation;
    // The speed that the ship moves
    private int speed = 13;
    
    /**
     * Primary Ship Constructor that initializes height and width
     * PRECONDITION: No precondition
     * POSTCONDITION: A new Ship object is created
     */
    public Ship(){     
        setHeight(image.getIconHeight());
        setWidth(image.getIconWidth());
    }
    
    /**
     * Function that handles the drawing of the pirate ship
     * PRECONDITION: No precondition
     * @param g graphics object that handles drawing
     */
    public void paintComponent (Graphics g){   
        // This is where the ship draws itself
        g.drawImage(getImage(), getxCoord(), getyCoord(), null);
    }        

    /**
     * Function that handles the changes in x and y coordinates of this pirate
     * ship object.
     * PRECONDITION: Boat cannon go on land or off the screen
     * @param direction char that determines which direction boat is facing
     * @param panelWidth int that is the width the of panel 
     * @param panelHeight int that is the height of the panel
     */
    public void move(char direction, int panelWidth, int panelHeight){
        this.orientation = direction;
        switch(direction){
            case 'e':{
                // if left and down
                if(((getxCoord()-speed) < 0) || (( (getyCoord()+getHeight()) + speed) > panelHeight)){
                    // Don't let them go off the screen
                }
                else if( (getxCoord()-speed < rightBoundary && getxCoord()-speed > leftBoundary) &&
                         (getyCoord()+speed > topBoundary && getyCoord()+speed < bottomBoundary)){
                    // don't let them go on land
                }
                else{
                    setxCoord(getxCoord() - (speed/2));
                    setyCoord(getyCoord() + (speed/2));
                    this.orientation = 'e';
                    setImageIcon(boatDownLeft); 
                }                               
                break;
            }
            case 'k':{
                // if right and down
                if( (((getxCoord()+getWidth())+speed) > panelWidth) || (( (getyCoord()+getHeight()) + speed) > panelHeight)){
                    // Don't let them go off the screen
                }
                else if( (getxCoord()+speed > leftBoundary && getxCoord()+speed < rightBoundary) &&
                         (getyCoord()+speed > topBoundary && getyCoord()+speed < bottomBoundary)){
                    // don't let them go on land
                }
                else{
                    setxCoord(getxCoord() + (speed/2));
                    setyCoord(getyCoord() + (speed/2));
                    this.orientation = 'k';
                    setImageIcon(boatRightDown);
                }            
                break;
            }
            case 't':{
                // if right and up
                if( (((getxCoord()+getWidth())+speed) > panelWidth) || ((getyCoord()-speed) < -5)){
                    // Don't let them go off the screen
                }
                else if( (getxCoord()+speed > leftBoundary && getxCoord()+speed < rightBoundary) &&
                         (getyCoord()-speed < bottomBoundary && getyCoord()-speed > topBoundary)){
                    // don't let them go on land
                }
                else{
                   setxCoord(getxCoord() + (speed/2));
                    setyCoord(getyCoord() - (speed/2));
                    this.orientation = 't';
                    setImageIcon(boatRightUp); 
                }                                
                break;
            }
            case 'y':{
                // if left and up
                if(((getyCoord()-speed) < -5) || ((getxCoord()-speed) < -5)){
                    // Don't let them go off the screen
                }
                else if( (getxCoord()-speed < rightBoundary && getxCoord()-speed > leftBoundary) &&
                         (getyCoord()-speed < bottomBoundary && getyCoord()-speed > topBoundary)){
                    // don't let them go on land
                }
                else{
                    setxCoord(getxCoord() - (speed/2));
                    setyCoord(getyCoord() - (speed/2));
                    this.orientation = 'y';
                    setImageIcon(boatUpLeft);
                }
                break;
            }
            case 'l':{
                if(((getxCoord()-speed) < 0) ){
                    // don't go off the screen
                }
                else if( (getxCoord()-speed < rightBoundary && getxCoord()-speed > leftBoundary) &&
                         (getyCoord() < bottomBoundary && getyCoord() > topBoundary)){
                    // don't let them go on land
                }
                else{
                    setxCoord(getxCoord() - speed);
                    this.orientation = 'l';
                    setImageIcon(leftBoat);
                }                
                break;
            }
            case 'r':{
                if((((getxCoord()+getWidth())+speed) > panelWidth)){
                    // don't go off the screen
                }
                else if( (getxCoord()+speed < rightBoundary && getxCoord()+speed > leftBoundary) &&
                         (getyCoord() < bottomBoundary && getyCoord() > topBoundary)){
                    // don't let them go on land
                }
                else{
                    setxCoord(getxCoord() + speed);
                    this.orientation = 'r';
                    setImageIcon(rightBoat);
                }
                break;
            }
            case 'u':{
                if( ((getyCoord()-speed) < -5)){
                    // don't go off the screen
                }
                else if( (getxCoord() < rightBoundary && getxCoord() > leftBoundary) &&
                         (getyCoord()-speed < bottomBoundary && getyCoord()-speed > topBoundary)){
                    // don't let them go on land
                }
                else{
                    setyCoord(getyCoord() - speed);
                    this.orientation = 'u';
                    setImageIcon(upBoat);
                }
                break;
            }
            case 'd':{
                if((( (getyCoord()+getHeight()) + speed) > panelHeight)){
                    // don't go off the screen
                }
                else if( (getxCoord() < rightBoundary && getxCoord() > leftBoundary) &&
                         (getyCoord()+speed > topBoundary && getyCoord()+speed < bottomBoundary)){
                    // don't let them go on land
                }
                else{
                    setyCoord(getyCoord() + speed);
                    this.orientation = 'd';
                    setImageIcon(downBoat);
                }
                break;
            }
        }        
    }
    
    /**
     * Function that returns this object's height variable
     * PRECONDITION: No precondition
     * @return returns object's integer height
     */
    public int getHeight() {
        return Height;
    }

    /**
     * Function that sets this object's height variable
     * PRECONDITION: No precondition
     * @param Height integer height that will be this object's height variable
     */
    public void setHeight(int Height) {
        this.Height = Height;
    }

    /**
     * Function that returns this object's speed variable
     * PRECONDITION: No precondition
     * @return returns object's integer speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Function that sets this object's speed variable
     * PRECONDITION: No precondition
     * @param speed integer speed that will be this object's speed variable
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * returns this object's width variable
     * PRECONDITION: No precondition
     * @return returns object's integer width
     */
    public int getWidth() {
        return Width;
    }

    /**
     * Function that sets this object's width variable
     * PRECONDITION: No precondition
     * @param Width integer width that will be this objects width variable
     */
    public void setWidth(int Width) {
        this.Width = Width;
    }

    /**
     * Function that returns this object's xCoord variable
     * PRECONDITION: No precondition
     * @return returns object's integer xCoord
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Function that sets this object's xCoord variable
     * PRECONDITION: No precondition
     * @param xCoord integer xCoord that will be this object's xCoord variable
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Function that returns this objects yCoord variable
     * PRECONDITION: No precondition
     * @return returns object's integer yCoord variable
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Function that sets this object's yCoord variable
     * PRECONDITION: No precondition
     * @param yCoord integer yCoord that will be this object's yCoord variable
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
    /**
     * Function that sets this object's primary image variable
     * PRECONDITION: No precondition
     * @param image Image image that will be this object's image variable
     */
    public void setImage(Image image) {
        this.boat = image;
    }
    
    /**
     * Function that returns this object's image variable 
     * PRECONDITION: No precondition
     * @return returns this object image variable 
     */
    public Image getImage(){
        boat = image.getImage();
        return boat;
    }
    
    /**
     * Function that returns this objects imageicon image variable
     * PRECONDITION: No precondition
     * @return returns this objects ImageIcon image variable
     */
    public ImageIcon getImageIcon() {
        return image;
    }
    
    /**
     * Function that returns this object's character orientation variable
     * PRECONDITION: No precondition
     * @return returns this object's char orientation variable
     */
    public char getOrientation(){
        return this.orientation;
    }
    
    /**
     * Function that sets this object's ImageIcon image variable
     * PRECONDITION: No precondition
     * @param image ImageIcon image that will be this object's image variable
     */
    public void setImageIcon(ImageIcon image) {
        this.image = image;
    }
}
