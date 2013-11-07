
package practice_ball_control;

import javax.swing.*;
import java.awt.*;


public class Missile {
    
    
    // x and y coordinates of the cloud
    private int xCoord = 0, yCoord = 0, centerX = 0, centerY = 0;
    // speed that the cloud floats
    private int speed = 6;
    // height and width of the image
    private int Height, Width;    
    // boleans
    boolean done = false;
    // Images
    
    private ImageIcon image = new ImageIcon("img/missileR.png");
    
    Image mis = image.getImage();
    
    public Missile(){        
        setxCoord(-10);
        setyCoord(-10);
        setHeight(image.getIconHeight());
        setWidth(image.getIconWidth());
    }
    
    public void move(int enemyx, int enemyy){
        if(getxCoord() < enemyx+30){
            setxCoord(getxCoord() + speed);
        }
        if(getxCoord() > enemyx+30){
            setxCoord(getxCoord() - speed);
        }
        if(getyCoord() < enemyy+80){
            setyCoord(getyCoord() + speed);
        }
        if(getyCoord() > enemyy+80){
            setyCoord(getyCoord() - speed);
        }
        
    }
    
    
    public void paintComponent(Graphics g){
        
         g.drawImage(getImage(), getxCoord(), getyCoord(), null);
        
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
     * Function that returns this missile height variable
     * PRECONDITION: No precondition
     * @return returns integer height
     */
    public int getHeight() {
        return Height;
    }
    
    /**
     * Function that sets this missiles height variable to parameter's height
     * PRECONDITION: No precondition
     * @param Height integer height that is soon to be missiles height
     */
    public void setHeight(int Height) {
        this.Height = Height;
    }
    
    /**
     * Function that returns this missiles speed variable
     * PRECONDITION: No precondition
     * @return returns integer speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Function that sets this missiles speed variable to parameter's speed
     * PRECONDITION: No precondition
     * @param speed integer speed that is soon to be missiles speed
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
        this.mis = image;
    }
    
    /**
     * Function that returns clouds clouds variable
     * PRECONDITION: No precondition
     * @return returns Image clouds
     */
    public Image getImage(){
        mis = image.getImage();
        return mis;
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
