
package practice_ball_control;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A cannonBall is what shoots out of the player's pirateShip (Class Ship)
 * It moves, splashes and changes it's image.
 * @author Sarat
 */
public class CannonBall {
    
    // x and y coordinate where the ship is located
    private int xCoord = 0, yCoord = 0;
    private int startx = 0, starty = 0;
    private int centerX = 0, centerY = 0;
    // Height and width of the ship
    private int Height, Width;
    // The speed at which the ball travels
    private int speed = 3;
    // Orientation of the cannon
    private char orientation;
    // Distance
    private double distance = 0;
    private double maxDistance = 15.0;
    private boolean done = false;
    // Images
    private ImageIcon image = new ImageIcon("img/cannonbal.png");
    private ImageIcon splash = new ImageIcon("img/acannonbalSplash.png");
    Image cannonball = image.getImage();
    Image cannonSplash = splash.getImage();
    
    
    
    /**
     * The primary constructor for cannonballs
     * PRECONDITION: No precondition
     * POSTCONDITION: initializes startx, starty, xCoord, yCoord, height and width
     * @param shipx int x coordinate of the ship
     * @param shipy int y coordinate of the ship
     * @param orientation char direction ball will move
     */
    public CannonBall(int shipx, int shipy, char orientation){
        this.startx = shipx;
        this.starty = shipy;
        this.xCoord = shipx;
        this.yCoord = shipy;
        this.orientation = orientation;
        setHeight(image.getIconHeight());
        setWidth(image.getIconWidth());
    }
    
    /**
     * Function that handles the drawing of cannonballs
     * PRECONDITION: No precondition
     * @param g Graphics object that handles drawing
     */
   public void paintComponent (Graphics g){   
        // This is where the ship draws itself
        g.drawImage(getImage(), getxCoord(), getyCoord(), null);
    }        

   /**
    * Function that returns whether or not the cannonball has reached max length
    * PRECONDITION: No precondition
    * @return returns boolean done
    */
   public boolean getDone(){
       return this.done;
   }
      
   /**
    * Function that returns the center x of the cannonball
    * PRECONDITION: No precondition
    * @return returns int center X
    */
   public int getCenterx(){
       centerX = ( getxCoord() + (this.getWidth()/2) );
       return this.centerX;
   }
   
   /**
    * Function that returns the center y of the cannonball
    * PRECONDITION: No precondition
    * @return returns int cent Y
    */
   public int getCentery(){
       centerY = ( getyCoord() + (this.getHeight()/2) );
       return this.centerY;
   }
   
   /**
    * sets the cannonballs done booelan variable to paramter boolean
    * PRECONDITION: No precondition
    * @param done boolean variable to be set to global
    */
   public void setDone(boolean done){
       this.done = done;
   }
   
   /**
    * Function that handles the changes in x and y coordinates of cannonball
    * PRECONDITION: No precondition
    * POST CONDITION: getxCoord() and getyCoord() will be changed as well as
    *                 setImageIcon()
    */
    public void move(){
        switch(orientation){
            case 'l':{
                // cannonball moves left
                if( !(computeDistance() > maxDistance) ){
                    setxCoord(getxCoord() - speed);
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
            case 'r':{
                // cannonball moves right
                if( !(computeDistance() > maxDistance) ){
                    setxCoord(getxCoord() + speed);
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
            case 'u':{
                // cannonball moves up
                if( !(computeDistance() > maxDistance) ){
                    setyCoord(getyCoord() - speed);
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
            case 'd': {
                // cannonball moves down
                if( !(computeDistance() > maxDistance) ){
                    setyCoord(getyCoord() + speed);
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
            case 'y': {
                // cannonball moves up and left
                if( !(computeDistance() > maxDistance-5) ){
                    setyCoord(getyCoord() - (speed/2));
                    setxCoord(getxCoord() - (speed/2));
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                    break;
            }
            case 'k': {
                // cannonball moves down and right
                if( !(computeDistance() > maxDistance) ){
                    setyCoord(getyCoord() + (speed/2));
                    setxCoord(getxCoord() + (speed/2));
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
            case 't': {
                // cannonball moves up and right
                if( !(computeDistance() > maxDistance-5) ){
                    setyCoord(getyCoord() - (speed/2));
                    setxCoord(getxCoord() + (speed/2));
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
            case 'e': {
                // cannonball move down and left
                if( !(computeDistance() > maxDistance) ){
                    setyCoord(getyCoord() + (speed/2));
                    setxCoord(getxCoord() - (speed/2));
                }
                else{
                    done = true;
                    setImageIcon(splash);
                }
                break;
            }
        }  
    }
        
    /**
     * Uses the distance formula to compute the absolute distance of the 
     * cannonball from where it began ( which is on the side of the ship )
     * PRECONDITION: No precondition
     * @return returns integer distance - absolute distance from start point
     */
    public double computeDistance(){        
        distance = ( (getxCoord() - this.startx)^2 + (getyCoord() - this.starty)^2 );
        distance = Math.abs(distance);
        distance = Math.sqrt(distance);
        return distance;
    }
    
    /**
     * Function that returns this cannonball's height variable
     * PRECONDITION: No precondition
     * @return returns integer height
     */
    public int getHeight() {
        return Height;
    }

    /**
     * Function that sets this cannonball's orientation variable to paramter's
     * PRECONDITION: No precondition
     * @param orient character orient is the soon to be cannonball's orientation
     */
    public void setOrientation(char orient){
        this.orientation = orient;
    }
    
    /**
     * Function that returns this cannonball's orientation variable
     * PRECONDITION: No precondition
     * @return returns char orientation
     */
    public char getOrientation(){
        return this.orientation;
    }
    
    /**
     * Function that sets this cannonball's height variable to parameter's height
     * PRECONDITION: No precondition
     * @param Height integer height that is soon to be cannonball's height
     */
    public void setHeight(int Height) {
        this.Height = Height;
    }

    /**
     * Function that returns this cannonball's speed variable
     * PRECONDITION: No precondition
     * @return returns integer speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Function that sets this cannonball's speed variable to parameter's speed
     * PRECONDITION: No precondition
     * @param speed integer speed that is soon to be cannonball's speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * Function that returns this cannonball's width variable
     * PRECONDITION: No precondition
     * @return returns cannonball's integer width variable
     */
    public int getWidth() {
        return Width;
    }

    /**
     * Function that sets this cannonball's width variable to parameter's width
     * PRECONDITION: No precondition
     * @param Width integer width that is soon to be cannonball's width
     */
    public void setWidth(int Width) {
        this.Width = Width;
    }

    /**
     * Function that returns this cannonball's xCoord variable
     * PRECONDITION: No precondition
     * @return returns cannonball's xCoord width variable
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Function that sets cannonball's xCoord variable to parameter's xCoord var
     * PRECONDITION: No precondition
     * @param xCoord integer xCoord that is soon to be cannonball's xCoord var
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Function that returns cannonball's yCoord variable
     * PRECONDITION: No precondition
     * @return returns integer yCoord
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Function that sets cannonball's yCoord variable to parameter yCoord
     * PRECONDITION: No precondition
     * @param yCoord integer yCoord that is soon to be cannonball's yCoord
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
    /**
     * Function that sets cannonballs image variable to paramter image
     * PRECONDITION: No precondition
     * @param image Image image that is soon to be cannonballs cannonball
     */
    public void setImage(Image image) {
        this.cannonball = image;
    }
    
    /**
     * Function that returns cannonballs cannonball variable
     * PRECONDITION: No precondition
     * @return retruns Image cannonball
     */
    public Image getImage(){
        cannonball = image.getImage();
        return cannonball;
    }
    
    /**
     * Function that returns cannonball's ImageIcon image variable
     * PRECONDITION: No precondition
     * @return returns ImageIcon image
     */
    public ImageIcon getImageIcon() {
        return image;
    }
    
    /**
     * Function that sets cannonballs image variable to paramter's image
     * PRECONDITION: No precondition
     * @param image ImageIcon image is soon to be cannonballs image
     */
    public void setImageIcon(ImageIcon image) {
        this.image = image;
    }
    
    
}
