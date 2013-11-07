package practice_ball_control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.*;

/**
 * A GamePanel is an extension of a JPanel and implements a keylistener
 * A game panel is what holds all of the action in this game. It is what
 * puts all of the images, collisions, dialogs, music, events, and action
 * together into one game. 
 * PRECONDITION: A game panel should not be created until the instructions
 *               screen has been closed, due to the user clicking "ready"
 * POSTCONDITION: The game panel will be created, and many loops will run
 *                infinitely until the window is closed, or a win/loss 
 *                determinate has been satisfied.
 * @author Sarat
 */
public class GamePanel extends JPanel implements KeyListener{ 
    
    // BOOLEANS
    boolean left = false, right = false, up = false, down = false,
    drawBox = true, drawExclaim = false, okToStartDrawing = true,
    drawDialog1 = false, drawDialog2 = false, drawDialog3 = false,
    drawDialog4 = false, drawDialog5 = false, drawDialogs = false,
    allowedToMoveShip = true, commenceGamePlay = false, checkedAlready = true,
    fireMissile = false, cheatCodeLevel1 = false, cheatCodeLevel2 = false,
    sunkShip = false, showTip1 = false, drawTip1 = false, drawTip2 = false,
    killshark = false, checkForScore = true, moveSharksIsOkay = false,
    sharkTalk = false, oneTime = true, closed = true, open = false,
    sharkCanTakeDamage = false, startSharkDialogTimer = true,
    postSharkDialog = false, drawSharkDialog1 = false, drawSharkDialog2 = false,
    drawSharkDialog3 = false, gameLost = false, gameWon = false;;
    // INTEGERS  
    private int boxX = 50, boxY = 600, boxWidth = 150, boxHeight = 150;
    private int bgX = 0, bgY = 0, step = 0, playerScore = 0, remainingAmmo =100;
    private int tipSteps = 0, talkedTimes = 0, sharkStep = 0;
        
    // TIMER
    javax.swing.Timer shipDialogTimer, tipTimer, sharkTimer, sharkTalkTimer, 
                      sharkDialogTimer, sinkShipTimer;
        
    
    
    private ImageIcon finImage1R = new ImageIcon("img/fin1R.png");
    
    // JButtons
    JButton fireButton;
    
    // TextBox
    JTextField score, ammo;
    
    // COLORS
    Color defaultColor = new Color(51, 51, 51), redBoxColor = new Color(200, 0, 0);
    
    // ARRAYLISTS
    ArrayList<TypeOneEnemy> initials = new ArrayList<TypeOneEnemy>();  
    ArrayList<CannonBall> cannons = new ArrayList<CannonBall>();    
    ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    ArrayList<Missile> missiles = new ArrayList<Missile>();
    ArrayList<TypeOneEnemy> sinkingShips = new ArrayList<TypeOneEnemy>();
    ArrayList<SharkEnemy> sharks = new ArrayList<SharkEnemy>();
    
    // IMAGES
    private ImageIcon exclamationPoint = new ImageIcon("img/exclaim.png");
    private Image exclamation = exclamationPoint.getImage();
    
    private ImageIcon dialog1 = new ImageIcon("img/dialog1.png");
    private Image dialog1Image = dialog1.getImage();
    
    private ImageIcon dialog2 = new ImageIcon("img/dialog2.png");
    private Image dialog2Image = dialog2.getImage();
    
    private ImageIcon dialog3 = new ImageIcon("img/dialog3.png");
    private Image dialog3Image = dialog3.getImage();
    
    private ImageIcon dialog4 = new ImageIcon("img/dialog4.png");
    private Image dialog4Image = dialog4.getImage();
    
    private ImageIcon dialog5 = new ImageIcon("img/dialog5.png");
    private Image dialog5Image = dialog5.getImage();
    
    private ImageIcon tip1 = new ImageIcon("img/tip1.png");
    private Image playertip1Image = tip1.getImage();
    
    private ImageIcon tip2 = new ImageIcon("img/tip2.png");
    private Image playertip2Image = tip2.getImage();
    
    private ImageIcon sharkOpenIcon = new ImageIcon("img/sharkOpen.png");
    private ImageIcon sharkClosedIcon = new ImageIcon("img/sharkClosed.png");
    
    private ImageIcon sharkDialog1 = new ImageIcon("img/sharkDialog1.png");
    private Image sharkDialogImage1 = sharkDialog1.getImage();
    
    private ImageIcon sharkDialog2 = new ImageIcon("img/sharkDialog2.png");
    private Image sharkDialogImage2 = sharkDialog2.getImage();
    
    private ImageIcon sharkDialog3 = new ImageIcon("img/sharkDialog3.png");
    private Image sharkDialogImage3 = sharkDialog3.getImage();
    
    
    private ImageIcon skull = new ImageIcon("img/pinkbones.png");
    private Image skullncross = skull.getImage();
    
    private ImageIcon fireIcon = new ImageIcon("img/monkeyFire.png");
    private Image fireImage = fireIcon.getImage();
    
    private ImageIcon ocean = new ImageIcon("img/ocean.png");
    private Image gamebackground = ocean.getImage();
                
    // YOUR SHIP
    Ship pirateShip;
        
    
    
    /**
     * Primary Constructor for a GamePanel
     * Constructor adds the clouds, missiles, and enemies initially. It also
     * calls several other methods to handle the initial creation of 
     * panel objects, like the score and ammo textboxes, the player's pirate
     * ship, the fire button, setting the layout to null so that 
     * setBounds() can be used,etc.
     * PRECONDITION: a gamepanel no arg constructor call must have been called
     * POSTCONDITION: a gamePanel object will have been created
     */
    public GamePanel() {        
        super();
        
        // build the score box
        buildScoreAmmo();
        setLayout(null);
        
        // build the fire button
        buildButtons();
        
        // make your ship
        pirateShip = new Ship();
        
        // make all of the clouds
        for(int i=0; i<3; i++){
            clouds.add(new Cloud());
        }
        
        // make the initial 3 enemies, of types 1,2 and 3, i must start at 1
        for (int i=1; i<4; i++){
            initials.add(new TypeOneEnemy(i));
        }
                
        // add a missile to the array of missiles
        for (int i = 0; i<1; i++){
            missiles.add(new Missile());
        }
        
        for (int i = 0; i<1; i++){
            buildShark();
        }
                
        // Start the tool tips
        showTip1 = true;
        buildToolTipTimer(4);
        tipTimer.start();
        
    }
   
    /**
     * Function that adds the fire button to the panel. The fire button is a
     * JButton with an image layered over it. When pressed, an event is 
     * generated, and likely, the event will cause a cannonball to fire
     * from a particular location.
     * PRECONDITION: button must be pressed before any event is created
     * POSTCONDITION: an event is generated by pressing the button
     */
    public void buildButtons(){
        fireButton = new JButton();
        fireButton.setActionCommand("fire");
        fireButton.addActionListener(new buttonListener());
        fireButton.setBackground(Color.BLUE);
        fireButton.setContentAreaFilled(false);
        fireButton.setBorder(null);
        fireButton.setIcon(fireIcon);
        fireButton.setBorderPainted(false);
        fireButton.setFocusable(false);
        fireButton.setIcon(fireIcon);
        fireButton.setBounds(690, 697, 75, 75);
        fireButton.setVisible(true);
        this.add(fireButton);        
    }
    
    /**
     * Function that is called to repaint graphics over and over based on
     * possibly updated image locations. In a sense, this function is what
     * actually displays that an object has "moved". This object handles all 
     * painting, from painting the background image, to painting cannonballs,
     * to drawing lines from missiles to their targets.
     * @param g the graphics object that handles drawing
     */
    public void paintComponent(Graphics g) {
        // prevents image stretching
        super.paintComponent(g);
        
        // Draw the ocean background
        g.drawImage(gamebackground, bgX, bgY, 1440, 800, this);
                
        // If it is time to draw the starting red box
        if(drawBox){
            g.setColor(redBoxColor);
            g.drawRect(boxX, boxY, boxWidth, boxHeight);
            g.setColor(defaultColor);
        }
                
        // Tell the player's ship to draw itself                                         
        pirateShip.paintComponent(g);
        
        if(drawTip1){
            g.drawImage(playertip1Image, 
                        pirateShip.getxCoord()+20,
                        pirateShip.getyCoord()-55, 
                        tip1.getIconWidth(), 
                        tip1.getIconHeight(), this);
        }
        if(drawTip2){
             g.drawImage(playertip2Image, 
                        pirateShip.getxCoord()+20,
                        pirateShip.getyCoord()-55, 
                        tip2.getIconWidth(), 
                        tip2.getIconHeight(), this);
        }
       
        
        // If it is time to draw the exclamation point
        if(drawExclaim){
            g.drawImage(exclamation, 
                    pirateShip.getxCoord()+20, 
                    pirateShip.getyCoord()-55, 20, 50, this);
        }
        
        // If it is time to draw the first dialogbox
        if(drawDialog1){
            g.drawImage(dialog1Image, 
                        initials.get(2).getxCoord()-160, 
                        initials.get(2).getyCoord()-45, 
                        dialog1.getIconWidth(), 
                        dialog1.getIconHeight(), this);
        }
        // If it is time to draw the second dialogbox
        if(drawDialog2){
            g.drawImage(dialog2Image, 
                        initials.get(1).getxCoord()-155, 
                        initials.get(1).getyCoord()-35, 
                        dialog2.getIconWidth(), 
                        dialog2.getIconHeight(), this);
        }
        // If it is time to draw the third dialogbox
        if(drawDialog3){
            g.drawImage(dialog3Image, 
                        initials.get(0).getxCoord()-100, 
                        initials.get(0).getyCoord()-20, 
                        dialog3.getIconWidth(), dialog3.getIconHeight(), this);
        }
        // If it is time to draw the third dialogbox
        if(drawDialog4){
            g.drawImage(dialog4Image, 
                        initials.get(1).getxCoord()-50, 
                        initials.get(1).getyCoord()-15, 
                        dialog4.getIconWidth(), 
                        dialog4.getIconHeight(), this);
        }
        // If it is time to draw the third dialogbox
        if(drawDialog5){
            g.drawImage(dialog5Image, 
                        initials.get(2).getxCoord()-110, 
                        initials.get(2).getyCoord()-25, 
                        dialog5.getIconWidth(), 
                        dialog5.getIconHeight(), this);
        }
            
        // It is time to draw the shark's first dialog
        if(drawSharkDialog1){
            g.drawImage(sharkDialogImage1, 
                        sharks.get(0).getxCoord()+70, 
                        sharks.get(0).getyCoord()-25, 
                        sharkDialog1.getIconWidth(), 
                        sharkDialog1.getIconHeight(), this);
        }
        
        // It is time to draw the shark's second dialog
        if (drawSharkDialog2) {
            g.drawImage(sharkDialogImage2,
                        sharks.get(0).getxCoord()+90,
                        sharks.get(0).getyCoord()-85,
                        sharkDialog2.getIconWidth(),
                        sharkDialog2.getIconHeight(), this);
        }
        
        // It is time to draw the shark's third dialog
        if (drawSharkDialog3) {
            g.drawImage(sharkDialogImage3,
                        sharks.get(0).getxCoord()+90,
                        sharks.get(0).getyCoord()-110,
                        sharkDialog3.getIconWidth(),
                        sharkDialog3.getIconHeight(), this);
        }
        
        
        
        
        // Tell the initial enemies to paint themselves
        for(int i = 0; i<initials.size(); i++){            
            initials.get(i).paintComponent(g);
            repaint();
        }
        
        for(int i = 0; i<sharks.size(); i++){
            sharks.get(i).paintComponent(g);
            repaint();
        }
        
        // If there are ships in the sinking ships array
        if(sinkingShips.size() != 0){
            for(int i = 0; i<sinkingShips.size(); i++){
                sinkingShips.get(i).paintComponent(g);
            }
        }
        
        // Tell the missiles to paint themselves
        if(fireMissile){
            for(int i = 0; i<missiles.size(); i++){
                missiles.get(i).paintComponent(g);
            }
            g.setColor(new Color(230,0,0));
            g.drawLine(missiles.get(0).getxCoord()+missiles.get(0).getWidth(), 
                       missiles.get(0).getyCoord()+missiles.get(0).getHeight(),
                       initials.get(0).getxCoord()+40,
                       initials.get(0).getyCoord()+85);
            g.drawImage(skullncross, 
                        initials.get(0).getxCoord()+60, // x coord
                        initials.get(0).getyCoord()+80, // y coord
                        50, 37, this);                  // wid, height, where
        }
        
        // tell the cannons to paint themselves
        for(int i=0; i<cannons.size(); i++){
            CannonBall cannon1 = cannons.get(i);
            cannon1.paintComponent(g);   
            repaint();
        }        
        
        
        // Tell the clouds to paint themselves
        for(int i=0; i<clouds.size(); i++){
            Cloud cloud1 = clouds.get(i);
            cloud1.paintComponent(g);
            repaint();
        }
    }  
    
    /**
     * Function that is called when player presses down a keyboard key
     * PRECONDITION: player must press a keyboard key
     * POSTCONDITION: if an appropriate key, an action occurs
     * @param e event that is generated by releasing a keyboard key
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == e.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == e.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == e.VK_S) {
            down = true;
        }
    }
    
    /**
     * Function that is called when player types a key on the keyboard.
     * PRECONDITION: player must press a keyboard key
     * POSTCONDITION: if an appropriate key, an action occurs
     * @param e event that is generated by releasing a keyboard key
     */
    public void keyTyped(KeyEvent e) {
    }    
    
    /**
     * Function that is called when player releases a key from the keyboard
     * PRECONDITION: player must press then release a keyboard key
     * POSTCONDITION: if an appropriate key, an action occurs
     * @param e event that is generated by releasing a keyboard key
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == e.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == e.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == e.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == e.VK_E) {
            if(cheatCodeLevel1 && cheatCodeLevel2){
                fireMissile = true;
            }            
        }
        if (e.getKeyCode() == e.VK_N) {
            cheatCodeLevel1 = true;
        }
        if (e.getKeyCode() == e.VK_M) {
            cheatCodeLevel2 = true;
        }
        if(e.getKeyCode() == e.VK_SPACE){
            fireShot();
            switch(pirateShip.getOrientation()){                
                case 'u':
                case 'd':{
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2), pirateShip.getyCoord() + (pirateShip.getHeight()/2)+5, 'l'));
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2), pirateShip.getyCoord() + (pirateShip.getHeight()/2)-5, 'r'));
                    updateAmmo('p');
                    break;
                }
                case 'r':
                case 'l':{
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2)+5, pirateShip.getyCoord() + (pirateShip.getHeight()/2), 'u'));
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2)-5, pirateShip.getyCoord() + (pirateShip.getHeight()/2), 'd'));
                    updateAmmo('p');
                    break;
                }
                case 'y':
                case 'k':{
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2)+2, pirateShip.getyCoord() + (pirateShip.getHeight()/2)+2, 't'));
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2)-2, pirateShip.getyCoord() + (pirateShip.getHeight()/2)-2, 'e'));
                    updateAmmo('p');
                    break;
                }
                case 't':
                case 'e':{
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2)-2, pirateShip.getyCoord() + (pirateShip.getHeight()/2)+2, 'y'));
                    cannons.add(new CannonBall(pirateShip.getxCoord()+(pirateShip.getWidth()/2)+2, pirateShip.getyCoord() + (pirateShip.getHeight()/2)-2, 'k'));
                    updateAmmo('p');
                    break;
                }
            }// end switch            
        }// end if key type is spacebar
    }// end keyreleased    
    
    /**
     * Function that creates a new sound object and calls the sound object's
     * shotFired() method. This function creates a sound effect for firing
     * a cannonball
     * PRECONDITION: Player must have fired a shot from within the game
     * POSTCONDITION: A sound effect will be played
     */
    public void fireShot(){
        Sounds sound = new Sounds();
        sound.shotFired();
    }
    
    
    public void buildShark(){
        sharks.add(new SharkEnemy());
    }
    
    /**
     * Function that calls other functions to handle movements. Calls the
     * method to move the ship, enemies, missiles, clouds, checks if player 
     * ship is in a box, etc.
     */
    public void move(){
        // Moves your ship
        movePirateShip();
        
        // Checks to see if your ship is in the red box
        checkShipInBox();
        
        // Determines which phase the initial enemies are in
        if(commenceGamePlay){ 
            detectCollision();
            makeEnemiesAttack(); 
        }
        else{ 
            moveEnemiesDialogs(); 
        }
        
        // Move sharks
        if(moveSharksIsOkay){
            moveSharks();
        }
                
        // Move missiles
        moveMissiles();
        
        // Moves the clouds
        moveClouds();       
        
        // Moves the cannonballs
        moveCannonBalls();                        
    }
    
    /**
     * Function moves the shark.
     */
    public void moveSharks(){
        for(int i = 0; i<sharks.size(); i++){
            
            // make shark hunt enemies
            // if the shark is not in place
            if(sharks.get(i).getInPlace() == false){
                // then get the shark in place
                sharks.get(i).initialSharkMove();
            }
            else
            {// if the shark is in place
                if(startSharkDialogTimer){
                    postSharkDialog = true;
                    buildSharkDialogTimer(4);
                    sharkDialogTimer.start();
                    startSharkDialogTimer = false; // so timer isn't build again
                }
                
                // determine if the shark is done talking
                if(sharks.get(i).getDone() == true)
                {
                    sharks.get(i).setImageIcon(finImage1R);
                    // if the shark is done talking, make him attack                    
                    sharks.get(i).move(initials.get(0).getxCoord(), initials.get(0).getyCoord());
                }
                else
                {   // if the shark is not done talking, make him talk
                    // This if statement can only be called once
                    if (oneTime) {
                        sharkTalk = true; // allow this part of timer access
                        buildSharkTalkTimer(3);  // timer that makes the shark talk
                        sharkTalkTimer.start(); // start the talking timer
                        oneTime = false; // don't let this be called again
                    }
                }         
            }
        }// end for
    }
    
    
    public void moveMissiles(){        
        if(fireMissile){
            for(int i = 0; i<missiles.size(); i++){
                missiles.get(i).move(initials.get(0).getxCoord(), initials.get(0).getyCoord());
            }
        }        
    }
    
    
    public void buildToolTipTimer(int seconds){
        // Without the full path, an error stating ambiguity is flagged
        if(showTip1){
            tipTimer = new javax.swing.Timer(seconds * 1000, new ActionListener(){

                public void actionPerformed(ActionEvent evt) {
                    timedTipActions();
                }
            });            
        }
        
    }
    
    public void buildSinkShipTimer(int seconds) {

        sinkShipTimer = new javax.swing.Timer(seconds * 300, new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                //performTimedActions();
                sinkShipOnTimer();
            }
        });

    }
    
    public void buildSharkTalkTimer(int seconds){
        if(sharkTalk){
            sharkTalkTimer = new javax.swing.Timer(seconds * 100, new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    performTimedActions();
                }
            });
        }
    }
    
    public void buildSharkKillTimer(int seconds){
        // build a new sharktimer to perform the sequence to kill a shark
        if(killshark){
            sharkTimer = new javax.swing.Timer(seconds * 200, new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    performTimedActions();
                }
            });
        }
    }
    
    /**
     * Function that sets a timer object to a particular amount of seconds
     * Time is determined by seconds*1000
     * @param seconds number of seconds to multiply by 1000
     */
    public void buildSharkDialogTimer(int seconds) {
        // build a new shark dialog timer to post the dialog bubbles
        if (postSharkDialog) {
            sharkDialogTimer = new javax.swing.Timer(seconds * 1000, new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    showTimedSharkDialogs();
                }
            });
        }
    }
    
    public void buildShipDialogTimer(int seconds){
        shipDialogTimer = new javax.swing.Timer(seconds*1000, new ActionListener() {
            
            public void actionPerformed(ActionEvent evt) {
                performTimedActions();
                
            }
            
        }); 
    }
        
    
    /**
     * The timer object calls this function. This function handles different
     * events based on whether a set of booleans is true or false. 
     * PRECONDITION: Timer object should have been started
     * POSTCONDITION: depends on what even the timer is handling. This function
     *                handles all timer events
     */
    public void performTimedActions(){
        
        if(drawDialogs){
            step += 1;
            switch(step){
                case 1:{
                    drawDialog1 = true;
                    repaint();
                    break;
                }
                case 2:{
                    drawDialog1 = false;
                    repaint();
                    drawDialog2 = true;
                    repaint();
                    break;
                }
                case 3:{
                    drawDialog2 = false;
                    repaint();
                    drawDialog3 = true; 
                    repaint();
                    break;
                }
                case 4:{
                    drawDialog3 = false;
                    repaint();
                    drawDialog4 = true; 
                    repaint();
                    break;
                }
                case 5:{
                    drawDialog4 = false;
                    repaint();
                    drawDialog5 = true; 
                    repaint();
                    break;
                }  
                case 6:{
                    allowedToMoveShip = true;
                    commenceGamePlay = true;
                    drawDialog5 = false;
                    drawExclaim = false;
                    drawDialogs = false;
                    shipDialogTimer.stop();           
                    for(int i = 0; i<initials.size(); i++){
                        initials.get(i).setPriority(4);
                    }
                    break;
                }
            }// end switch 
        }// end if drawDialogs 
        
              
        
        
        // sequence to kill a shark
        if(killshark){
            for(int i =0; i<sharks.size(); i++){
                if(sharks.get(i).getShouldRemove() == true){
                    sharks.remove(i);
                    killshark = false;
                }
                else{
                    sharks.get(i).getShouldRemove();
                }
            }
            
            
        }
        
        // sequence to make a shark talk
        if (sharkTalk) {
            talkedTimes++;
            System.out.println(talkedTimes);
            if (talkedTimes >= 55) {
                sharkTalk = false;
                sharkTalkTimer.stop();
                sharks.get(0).setDone(true);
                sharkCanTakeDamage = true;
            } else {
                for (int i = 0; i < sharks.size(); i++) {
                    if (open) {
                        sharks.get(i).setImageIcon(sharkOpenIcon);
                        open = false;
                        closed = true;
                    } else if (closed) {
                        sharks.get(i).setImageIcon(sharkClosedIcon);
                        open = true;
                        closed = false;
                    }

                }
            }
        }
        
    } // end performTimedActions    
    
    
    public void sinkShipOnTimer(){
        // sequence to sink a ship
        if(sunkShip){
            if(sinkingShips.size() != 0){
                for(int i = 0; i<sinkingShips.size(); i++){
                    if(sinkingShips.get(i).getSunk() == true){
                        sinkingShips.remove(i);
                    }
                    else{
                        sinkingShips.get(i).sinkItself();
                    }
                    
                }
            }
            else{
                sunkShip = false;
                sinkShipTimer.stop();
            }
            
        }
    }
    
    /**
     * Timer calls this function to show the dialogs for the shark
     */
    public void showTimedSharkDialogs(){
             // sequence to make shark dialogs appear
        if (postSharkDialog) {
            System.out.println("inside postSharkDialog");
            sharkStep += 1;
            System.out.println(sharkStep);
            switch (sharkStep) {
                case 1: {
                    System.out.println("draw dialog 1");
                    drawSharkDialog1 = true;
                    repaint();
                    break;
                }
                case 2: {
                    System.out.println("draw dialog 2");
                    drawSharkDialog1 = false;
                    repaint();
                    drawSharkDialog2 = true;
                    repaint();
                    break;
                }
                case 3: {
                    System.out.println("draw dialog 3");
                    drawSharkDialog2 = false;
                    repaint();
                    drawSharkDialog3 = true;
                    repaint();
                    break;
                }
                case 4: {
                    drawSharkDialog3 = false;
                    sharkDialogTimer.stop();
                    repaint();
                    break;
                }
            }
        }
    }
    
    
    public void timedTipActions(){
        
        if(showTip1){
            tipSteps++;
            switch(tipSteps){
                case 1:{
                    drawTip1 = true;
                    break;
                }
                case 2:{
                    drawTip1 = false;
                    drawTip2 = true;
                    break;
                }
                case 5:{
                    drawTip2 = false;
                }
            }// end switch
        }// end if showTip1
        
    }
    
    /**
     * Function that checks to see if the player's ship is located within
     * the red box drawn on the bottom left of the screen
     * This function is protected by a boolean checkedAlready, so that
     * once the boat is located inside of the box, the boolean is set to false
     * from within, and the inner piece of code can never again be reached.
     * PRECONDITION: Player can only set off this piece of code once, must never
     *               have driven into the box before
     * POSTCONDITION: box will disappear and animation will begin.
     */
    public void checkShipInBox(){
        // If the pirate ship is in the red box
        if(checkedAlready)
        {
            if( (pirateShip.getxCoord() < (boxX+(boxWidth-95)) &&
                 pirateShip.getxCoord() > boxX) &&
                (pirateShip.getyCoord() < boxY+(boxHeight+20) &&
                 pirateShip.getyCoord() > boxY-40) 
              )
            {
                drawBox = false;  // undraw the red box
                drawExclaim = true; // draw the exclamation point
                checkedAlready = false; // so you can't get here ever again
                allowedToMoveShip = false; // don't let the boat move
                // Set these three to false to end all tips
                showTip1 = false;
                drawTip1 = false;
                drawTip2 = false;
                tipTimer.stop();
            }
        }// end if(checkedalready)        
    }
    
    /**
     * Causes your pirate ship to update it's x and y coordates depending
     * on what direction the player has moved it. A character indicating
     * orientation is passed to the pirate ship's move method to ensure
     * correct displacement
     * PRECONDITION: Player must have moved the ship with a directional button
     * POSTCONDITION: Player's ship will contain a different x and y coordinate
     */
    public void movePirateShip() {
        if (allowedToMoveShip) 
        {
            if (left && down) {
                pirateShip.move('e', this.getWidth(), this.getHeight());
            } else if (left && up) {
                pirateShip.move('y', this.getWidth(), this.getHeight());
            } else if (right && down) {
                pirateShip.move('k', this.getWidth(), this.getHeight());
            } else if (right && up) {
                pirateShip.move('t', this.getWidth(), this.getHeight());
            } else {
                if (left) {
                    pirateShip.move('l', this.getWidth(), this.getHeight());
                }
                if (right) {
                    pirateShip.move('r', this.getWidth(), this.getHeight());
                }
                if (down) {
                    pirateShip.move('d', this.getWidth(), this.getHeight());
                }
                if (up) {
                    pirateShip.move('u', this.getWidth(), this.getHeight());
                }
            }
        } 

    }
    
    /**
     * This function is the opposite of the other function "makeEnemiesAttack()"
     * This function causes the intial three enemies to drive to a specific
     * location, and them commence in some dialog before actually beginning 
     * the game.
     * PRECONDITION: Player must have driven into the red box
     * POSTCONDITION: A short dialog occures, and the game cmommences
     */
    public void moveEnemiesDialogs(){
        // Move enemies into place, and handle the dialogs
        // If the box isn't drawn
        if(drawBox == false){
            // start moving the initial 3 enemies
            for(int i = 0; i<initials.size(); i++){
                
                // if all of the enemies are in place
                if( initials.get(i).getDone() == true){      
                    // then start drawing dialogs
                    if(okToStartDrawing){
                        drawDialogs = true;
                        buildShipDialogTimer(4);
                        shipDialogTimer.start();
                        okToStartDrawing = false; // only do this once
                    }                    
                }
                else{
                    // If the 3 aren't in place, then move them in place
                    initials.get(i).move();
                }               
            }           
        }
    }
    
    /**
     * This function moves all of the Cloud objects, and also removes them
     * if they have floated off of the screen and out of sight.
     */
    public void moveClouds(){
        // Move the clouds
        for(int i=0; i<clouds.size(); i++){
            Cloud cld2 = clouds.get(i);
            if(cld2.getDone()){
                clouds.remove(cld2);
                clouds.add(new Cloud());
            }
            else{
                cld2.move();
            }            
        }
    }
    
    /**
     * When shots are fired, a call to this function occurs. This function
     * reduces the amount of ammo the player has before losing, and displays
     * the new amount of ammo in the top left hand box.
     */
    public void updateAmmo(char whatFiredIt){
        switch(whatFiredIt){
            case 'b': {
                if ((remainingAmmo - 1) <= 0) {
                    gameOver();
                } else {
                    remainingAmmo -= 1;
                    ammo.setText("Cannons Left: " + remainingAmmo);
                }
                break;
            }
            case 'p': {
                if ((remainingAmmo - 2) <= 0) {
                    gameOver();
                } else {
                    remainingAmmo -= 2;
                    ammo.setText("Cannons Left: " + remainingAmmo);
                }
                break;
            }
        }

        
    }
    
    /**
     * Function that forces all existing cannonballs to update it's x and y
     * coordinates so that it can be redrawn in a different position.
     * Also displays a quick splash animation right before it's destroyed
     */
    public void moveCannonBalls(){
        // Move the cannonballs
        for(int i=0; i<cannons.size(); i++){
            CannonBall cannon = cannons.get(i);
            if(cannon.getDone()){
                try {
                    // Makes the "splashed" cannonball appear longer
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                    ex.printStackTrace();
                }
                cannons.remove(cannon);
            }
            else{
                cannon.move();
            }
        }// end for
    }
    
    /**
     * Function that handles phase 2 of the initial 3 enemy ships.
     * After the initial dialog, all typeoneenemy ships beging to move in an 
     * attacking motion. Also detects if typeoneenemy ships have driven off
     * of the screen and out of sight.
     */
    public void makeEnemiesAttack(){        
        for(int i = 0; i<initials.size(); i++){
            
            if( initials.get(i).getOffScreen() == true){
                initials.remove(initials.get(i));
                initials.add(new TypeOneEnemy(4));
            }
            else{
                initials.get(i).move();
            }
            
        }        
    }
    
    /**
     * Function that builds the two text boxes in the top left hand corner
     * of the panel.
     */
    public void buildScoreAmmo(){
        score = new JTextField("Score: " + playerScore); // display text
        score.setForeground(Color.yellow); // text color
        score.setFocusable(false); // so the focus stays on panel
        score.setBounds(20, 20, 150, 30); // location and size
        score.setOpaque(false); // transparent background
        add(score); // add to the panel
        
        ammo = new JTextField("Cannons Left: " + remainingAmmo);
        ammo.setForeground(Color.yellow); // text color
        ammo.setFocusable(false); // so the focus stays on panel
        ammo.setBounds(180, 20, 150, 30); // location and size
        ammo.setOpaque(false); // transparent background
        add(ammo);
    }
    
    /**
     * Function that runs checks to determine if and what kind of overlapping
     * among objects has occurred.
     */
    public void detectCollision() {
        // CHECK COLLISIONS ON CANNONS AND SHIPS
        for(int i=0; i<cannons.size(); i++){
            CannonBall cannon = cannons.get(i);
            // compare it to each enemy
            for(int j=0; j<initials.size(); j++){
                TypeOneEnemy typeone = initials.get(j);
                // If a collision is detected
                if(   
                     ( cannon.getCenterx() > typeone.getxCoord()+10 ) && 
                     ( cannon.getCenterx() < (typeone.getxCoord()+10 + typeone.getWidth()) ) &&
                     ( cannon.getCentery() < (typeone.getyCoord() + typeone.getHeight()) ) &&
                     ( cannon.getCentery() > typeone.getyCoord() )
                  )
                {
                    // A collision was detected, so handle it                    
                    processCollision(i, j, 'c');
                }// end if
                
            }// end initials            
        }// end cannons        
        
        
        // CHECK COLLISIONS ON CANNONS AND SHARKS
        for(int i=0; i<cannons.size(); i++){
            CannonBall cannon = cannons.get(i);
            // compare it to each enemy
            for(int j=0; j<sharks.size(); j++){
                SharkEnemy shark = sharks.get(j);
                // If a collision is detected
                if(   
                     ( cannon.getCenterx() > shark.getxCoord()+10 ) && 
                     ( cannon.getCenterx() < (shark.getxCoord()+10 + shark.getWidth()) ) &&
                     ( cannon.getCentery() < (shark.getyCoord() + shark.getHeight()) ) &&
                     ( cannon.getCentery() > shark.getyCoord() )
                  )
                {
                    // A collision was detected, so handle it
                    processCollision(i, j, 'a');
                }// end if
                
            }// end initials            
        }// end cannons
        
        
        // CHECK COLLISIONS ON MISSILES AND ENEMIES
        for(int i=0; i<missiles.size(); i++){
            Missile missile = missiles.get(i);
            // compare it to each enemy
            for(int j=0; j<initials.size(); j++){
                TypeOneEnemy typeone = initials.get(j);
                // If a collision is detected
                if(   
                     ( missile.getCenterx() > typeone.getxCoord()+10 ) && 
                     ( missile.getCenterx() < (typeone.getxCoord()+10 + typeone.getWidth()) ) &&
                     ( missile.getCentery() < (typeone.getyCoord() + typeone.getHeight()) ) &&
                     ( missile.getCentery() > typeone.getyCoord() )
                  )
                {
                    // A collision was detected, so handle it
                    processCollision(i, j, 'm');
                }// end if
                
            }// end initials            
        }// end cannons
        
        
        // CHECK COLLISIONS ON SHARKS AND ENEMIES
        for(int i=0; i<sharks.size(); i++){
            SharkEnemy shark = sharks.get(i);
            // compare it to each enemy
            for(int j=0; j<initials.size(); j++){
                TypeOneEnemy typeone = initials.get(j);
                // If a collision is detected
                if(   
                     ( shark.getCenterx() > typeone.getxCoord()+10 ) && 
                     ( shark.getCenterx() < (typeone.getxCoord()+10 + typeone.getWidth()) ) &&
                     ( shark.getCentery() < (typeone.getyCoord() + typeone.getHeight()) ) &&
                     ( shark.getCentery() > typeone.getyCoord() )
                  )
                {
                    // A collision was detected, so handle it
                    processCollision(i, j, 's');
                }// end if
                
            }// end initials           
        }
        
        
        // CHECK COLLISIONS ON SHARKS AND YOU
        for(int i=0; i<sharks.size(); i++){
            SharkEnemy shark = sharks.get(i);
            
                // if my boat is facing up or down
                if(pirateShip.getOrientation() == 'u' || 
                   pirateShip.getOrientation() == 'd'){
                    
                    if(( shark.getCenterx() > pirateShip.getxCoord()-2 ) &&  // my left bounds
                     ( shark.getCenterx() < (pirateShip.getxCoord() + pirateShip.getWidth()-17) ) &&// my right bounds
                     ( shark.getCentery() < (pirateShip.getyCoord() + pirateShip.getHeight())+14 ) &&// my lower bounds
                     ( shark.getCentery() > pirateShip.getyCoord()+14 ))// my upper bounds
                   {
                       
                        gameOver();
                   }
                    
                    
                } // if my boat is facing left or right
                else if(pirateShip.getOrientation() == 'l' ||
                        pirateShip.getOrientation() == 'r'){
                    
                    if(( shark.getCenterx() > pirateShip.getxCoord() ) &&  // my left bounds
                     ( shark.getCenterx() < (pirateShip.getxCoord() + pirateShip.getWidth()) ) && // my right bounds
                     ( shark.getCentery() < (pirateShip.getyCoord() + pirateShip.getHeight())-9 ) && // my lower bounds
                     ( shark.getCentery() > pirateShip.getyCoord()+40 )) // my upper bounds
                   {
                                               
                        gameOver();
                   }
                    
                    
                }            
                else{  // if my boat is one of the diagonals
                    
                     if(( shark.getCenterx() > pirateShip.getxCoord() ) && 
                     ( shark.getCenterx() < (pirateShip.getxCoord() + pirateShip.getWidth()) ) &&
                     ( shark.getCentery() < (pirateShip.getyCoord() + pirateShip.getHeight()) ) &&
                     ( shark.getCentery() > pirateShip.getyCoord() ))
                   {
                       
                        gameOver();
                   }
                             
                
                   
                    
                }// end if
                
                      
        }
        
        
        
    }// end detect
        
    /**
     * Function that is called if the player loses the game
     */
    public void gameOver(){
        gameLost = true;
        //new GameOverScreen();
    }
    
    public void gameWon(){
        gameWon = true;
        //new WinScreen();
    }
    
    public void updateScore(char typeOfScoreUpgrade){
        
        switch(typeOfScoreUpgrade){            
            case 'c': { // if you killed a boat with a cannon
                playerScore += 300; // update score 
                score.setText("Score: " + playerScore); // update the score's display
                break;
            }
            case 'm': { // if you killed a boat with a missile
                playerScore += 200; // update score
                score.setText("Score: " + playerScore); // update the score's display
                break;
            }
            case 's':{ // if shark kills an enemy
                playerScore += 50;
                score.setText("Score: " + playerScore); // update the score's display
                break;
            }    
            case 'a':{ // if you kill a shark
                playerScore += 2500;
                score.setText("Score: " + playerScore); // update the score's display
                break;
            }
        }// end switch
        
        // If player's score exceeds 10000, then they won
        if(playerScore >= 10000){
            gameWon();
        }
        
        // See if the player has reached enough points to summon the shark
        if(checkForScore){
            if(playerScore >= 2000){
                introduceShark();
                checkForScore = false;
            }
        }
        
    }
    
    public boolean getGameLost(){        
        return this.gameLost;        
    }
    
    public boolean getGameWon(){
        return this.gameWon;
    }
    
    /**
     * Bring the shark onto the board
     */
    public void introduceShark(){
        moveSharksIsOkay = true;
        sharkCanTakeDamage = false;
    }
    
    /**
     * Function that handles situations where one weapon overlaps and enemy
     * @param weapon index of cannonball or rocket in it's array
     * @param enemy index of enemy ship in it's array
     * @param type character type of collision ,cannon collision, rocket coll.
     */
    public void processCollision(int weapon, int enemy, char type){
        
        switch (type) {
            case 'c': { // cannon hits enemy ship
                updateScore('c');   
                cannons.remove(weapon);
                sinkingShips.add(initials.get(enemy));
                initials.remove(enemy);
                initials.add(new TypeOneEnemy(4));
                sunkShip = true;
                buildSinkShipTimer(1);  // buld sink ship timer
                sinkShipTimer.start();
                break;
            }
            case 'm':{ // missile hits enemy ship
                updateScore('m');                 
                fireMissile = false;
                missiles.remove(weapon);
                sinkingShips.add(initials.get(enemy));
                initials.remove(enemy);
                missiles.add(new Missile());
                initials.add(new TypeOneEnemy(4));
                sunkShip = true;
                buildSinkShipTimer(1);  // build sink ship timer
                sinkShipTimer.start();
                break;
            }
            case 's':{ // shark attacks enemy
                updateScore('s');
                sinkingShips.add(initials.get(enemy));
                initials.remove(enemy);
                initials.add(new TypeOneEnemy(4));
                sunkShip = true;
                buildSinkShipTimer(1); // build sink ship timer
                sinkShipTimer.start();
                break;
            }
            case 'a': { // cannon hits shark
                cannons.remove(weapon);
                updateSharkHealth();
                break;
            }
        }
    }
    
    /**
     * Function that updates the health of the shark by callings the shark's
     * updatehealth() function. 
     */
    public void updateSharkHealth(){
        if(sharkCanTakeDamage) {
            for (int i = 0; i < sharks.size(); i++) {
                sharks.get(i).updateHealth();
                if (sharks.get(i).getDead() == true) {
                    killshark = true;
                    updateScore('a');
                    buildSharkKillTimer(1);
                    sharkTimer.start();
                }
            }
        }
        else{
            
        }
        
    }
    
    /**
     * Class that implements ActionListener interface. The purpose of this 
     * class is to handle what happens when the user clicks any of the three
     * buttons on the Instructions splash screen
     */
    public class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(e.getActionCommand().equals("fire")){                
                fireShot();
                updateAmmo('b'); // update ammo b for button
                cannons.add(new CannonBall(710, 697, 'u'));
            }
            
        }// end action performed        
    }// end buttonListener
}// end GamePanel
