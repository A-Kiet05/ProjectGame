package main;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.*;
import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private BufferedImage img ;
    private float xDelta  = 100 , yDelta = 100;
   
  


    public GamePanel(){
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
        LoadImg();
    }
    
    // method to import the image to screen
    public BufferedImage LoadImg(){
       BufferedImage img = null;
       
       try {
          img = ImageIO.read( new File("src/images/player_sprites.png"));
           
       } catch ( IOException ex) {
        // TODO: handle exception
           Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
       }
       return img;
    }

    public void setPanelSize(){
        Dimension size = new Dimension(1280,800);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }


    public void changeXdelta( int value){
        xDelta += value;
       
    }
    public void changeYdelta(int value){
        yDelta+= value;
       
    }
    public void setRecPos( int x , int y){
        this.xDelta = x;
        this.yDelta = y;
       
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
      
        g.drawImage(img, 0, 0, null);
        
       
       
        


        repaint();
    }
    
     
}
