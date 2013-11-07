
package practice_ball_control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameOverScreen extends JFrame{
    
    private static final int WINDOW_WIDTH = 600, WINDOW_HEIGHT=800;
    JPanel panel;
    JButton entireScreen;
    ImageIcon gameOverIcon = new ImageIcon("img/gameOverv2.png");
    
    public GameOverScreen() {
        // frame
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Gameplay basics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //panel
        panel = new JPanel();
        
        // button
        entireScreen = new JButton("buton");
        entireScreen.setIcon(gameOverIcon);
        entireScreen.setContentAreaFilled(false);
        entireScreen.setActionCommand("exit");
        entireScreen.addActionListener(new buttonListener());
                
        panel.add(entireScreen);        
        this.add(panel);
        
        setVisible(true);
    }
    
    public class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(e.getActionCommand().equals("exit")){
                System.exit(0);
            }
        }
    }
        
}
