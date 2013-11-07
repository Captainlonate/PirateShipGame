
package practice_ball_control;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * A Cloud object is exactly what it says. It create an image of a cloud,
 * and floats itself around the screen. It is not intended to be targeted
 * or event handled with one exception. If it floats off the screen, it should
 * be recreated.
 * @author Sarat
 */
public class Cloud {
    // x and y coordinates of the cloud
    private int xCoord = 1800, yCoord = 0;
    // speed that the cloud floats
    private int speed = 0;
    // height and width of the image
    private int Height, Width;    
    // boleans
    boolean done = false;
    // Images
    
    private ImageIcon image = new ImageIcon("img/cloud1.png");
    private ImageIcon smallCloud = new ImageIcon("img/cloud2.png");    
    
    Image cld1 = image.getImage();
        
    /**
     * The primary constructor for clouds
     * PRECONDITION: No precondition
     * POSTCONDITION: Initializes x and y coords, width and height
     */
    public Cloud(){   
        // Randomize speed and y coordinate
        randomCoords();
        
        setHeight(image.getIconHeight());
        setWidth(image.getIconWidth());
    }
    
     /**
     * Function that handles the drawing of clouds
     * PRECONDITION: No precondition
     * @param g graphics object that handles drawing
     */
    public void paintComponent (Graphics g){   
        // This is where the ship draws itself
        g.drawImage(getImage(), getxCoord(), getyCoord(), null);
    } 
    
    /**
     * Function that initializes clouds x and y coords randomly
     * PRECONDITION: No precondition
     */
    public void randomCoords(){
        Random rnd = new Random();
        // Find a random starting y coordinate
        int x = rnd.nextInt(660);
        x += 20;
        this.yCoord = x;
        
        // Find a random speed
        x = rnd.nextInt(5);
        x += 1;
        this.speed = x;
        
        // randomize which image to use
        x = rnd.nextInt(2);        
        if(x == 1){
            setImageIcon(image);
        }
        else if(x == 0){
            setImageIcon(smallCloud);
        }
    }
    
    /**
    * Function that returns whether or not the cloud has reached max length
    * PRECONDITION: No precondition
    * @return returns boolean done
    */
    public boolean getDone(){
        if(getxCoord() < -200){
            this.done = true;
        }
        return done;
    }
    
    /**
     * Function that changes the x Coordinate of a cloud with getxCoord-speed
     */
    public void move(){
        setxCoord(getxCoord()-speed);
    }
    
    
    /**
     * Function that returns this cloud's height variable
     * PRECONDITION: No precondition
     * @return returns integer height
     */
    public int getHeight() {
        return Height;
    }
    
    /**
     * Function that sets this clouds height variable to parameter's height
     * PRECONDITION: No precondition
     * @param Height integer height that is soon to be clouds height
     */
    public void setHeight(int Height) {
        this.Height = Height;
    }

    /**
     * Function that returns this clouds speed variable
     * PRECONDITION: No precondition
     * @return returns integer speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Function that sets this clouds speed variable to parameter's speed
     * PRECONDITION: No precondition
     * @param speed integer speed that is soon to be clouds speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * Function that returns this clouds width variable
     * PRECONDITION: No precondition
     * @return returns clouds integer width variable
     */
    public int getWidth() {
        return Width;
    }

    /**
     * Function that sets this clouds width variable to parameter's width
     * PRECONDITION: No precondition
     * @param Width integer width that is soon to be clouds width
     */
    public void setWidth(int Width) {
        this.Width = Width;
    }
        
    /**
     * Function that returns this clouds xCoord variable
     * PRECONDITION: No precondition
     * @return returns clouds xCoord width variable
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Function that sets clouds xCoord variable to parameter's xCoord var
     * PRECONDITION: No precondition
     * @param xCoord integer xCoord that is soon to be clouds xCoord var
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Function that returns clouds yCoord variable
     * PRECONDITION: No precondition
     * @return returns integer yCoord
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Function that sets clouds yCoord variable to parameter yCoord
     * PRECONDITION: No precondition
     * @param yCoord integer yCoord that is soon to be clouds yCoord
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    
    /**
     * Function that sets clouds image variable to parameter image
     * PRECONDITION: No precondition
     * @param image Image image that is soon to be clouds clouds
     */
    public void setImage(Image image) {
        this.cld1 = image;
    }
    
    /**
     * Function that returns clouds clouds variable
     * PRECONDITION: No precondition
     * @return returns Image clouds
     */
    public Image getImage(){
        cld1 = image.getImage();
        return cld1;
    }
    
    /**
     * Function that returns clouds ImageIcon image variable
     * PRECONDITION: No precondition
     * @return returns ImageIcon image
     */
    public ImageIcon getImageIcon() {
        return image;
    }
    
    /**
     * Function that sets clouds image variable to parameter's image
     * PRECONDITION: No precondition
     * @param image ImageIcon image is soon to be clouds image
     */
    public void setImageIcon(ImageIcon image) {
        this.image = image;
    }
}
