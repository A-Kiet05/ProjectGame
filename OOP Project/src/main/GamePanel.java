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

import static ultiz.Constant.playerConstants;
import static ultiz.Constant.*;


public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private BufferedImage img; 
    private BufferedImage[][] Animations;

    private float xDelta  = 100 , yDelta = 100;
    private int aniIndex=0, aniTick=0 , aniSpeed = 25;
    private int playerAction =playerConstants.IDLE;
    private int playerDirection = -1;
   private boolean isMoving = false;
  


    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        LoadImg();
        loadAnimation();

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }
    
    private void loadAnimation(){
        Animations = new BufferedImage[9][6];
        for(int j = 0 ; j < Animations.length ; ++j){
        for (int i= 0 ; i < Animations[j].length ; ++i){
            Animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
        }
    }
    }

    // method to import the image to screen
    public void LoadImg(){
      InputStream is = GamePanel.class.getResourceAsStream("/images/player_sprites.png");
       
       try {
          img = ImageIO.read(is);
           
       } catch ( IOException e) {
        // TODO: handle exception
           e.printStackTrace();
       }finally{
        try {
            is.close();
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
       }
      
      
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
    
    private void updateAniTick(){
          aniTick++;
          if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= playerConstants.GetAmountSprites(playerAction)){
                aniIndex = 0 ;
            }
          }
    }

    public void setDirection(int direction){
        this.playerDirection = direction;
        isMoving = true;
    }
    public void setMoving (boolean isMoving){
        this.isMoving = isMoving;
        
    }

    private void setAnimations(){
        if(isMoving){
            playerAction = playerConstants.RUNNING;
        }
        else{
            playerAction = playerConstants.IDLE;
        }
    }

    private void setPosition(){
        if(isMoving){
        switch (playerDirection) {
            case Direction.LEFT:
                xDelta -= 5;
                break;
            case Direction.RIGHT:
                xDelta += 5;
                break;
            case Direction.UP:
                yDelta -= 5;
                break;
            case Direction.DOWN:
                yDelta += 5;
                break;

        
            default:
                break;
        }
    }
 }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
      
       updateAniTick();
       setAnimations();
       setPosition();

        g.drawImage(Animations[playerAction][aniIndex],(int) xDelta,(int) yDelta,128 , 80, null);
        
       
       
        


        repaint();
    }
    
     
}
