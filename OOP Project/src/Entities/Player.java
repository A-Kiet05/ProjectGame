package Entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import ultiz.Constant.Direction;
import ultiz.Constant.playerConstants;

import static ultiz.Constant.playerConstants;
import static ultiz.Constant.Direction;

public class Player extends Entity
 {
    private BufferedImage[][] Animations;
    private int aniIndex=0, aniTick=0 , aniSpeed = 25;
    private int playerAction =playerConstants.IDLE;
   
   private boolean isMoving = false , attacking = false;
   private float playerSpeed =  1.5f ;
   private boolean left , right , up , down ;

    public Player (float x , float y){
        super(x,y);
        LoadImg();

    }

    private void updateAniTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
          aniTick = 0;
          aniIndex++;
          if(aniIndex >= playerConstants.GetAmountSprites(playerAction)){
              aniIndex = 0 ;
              attacking = false;
          }
        }
  }

 

 
  
private void setAnimations(){
    int startAnimation = playerAction;
    if(isMoving){
        playerAction = playerConstants.RUNNING;
    }
    else{
        playerAction = playerConstants.IDLE;
    }
    if(attacking){
        playerAction = playerConstants.ATTACKING;
    }
    
    if ( startAnimation != playerAction){
        resetAnimation();
    }
}
   public void resetAnimation(){
    aniTick = 0 ;
    aniIndex = 0 ;
   }

 private void setPosition(){
       isMoving =false;
       if (left && !right){
         x -= playerSpeed;
         isMoving = true;
       }
        else if (!left && right){
       x += playerSpeed;
       isMoving = true;
        }


        if ( up && !down ){
            y -= playerSpeed;
            isMoving= true;
        }
        else if( !up && down){
            y += playerSpeed;
            isMoving = true;
        }

 }

     public void render(Graphics g){
         
         g.drawImage(Animations[playerAction][aniIndex],(int) x,(int) y,128 , 80, null);
     }
     public void update(){
        
        updateAniTick();
        setAnimations();
        setPosition();
     }

     public void LoadImg(){
         InputStream is = GamePanel.class.getResourceAsStream("/images/player_sprites.png");
       
       try {
         BufferedImage img = ImageIO.read(is);

          Animations = new BufferedImage[9][6];
        for(int j = 0 ; j < Animations.length ; ++j)
        for (int i= 0 ; i < Animations[j].length ; ++i)
            Animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
        
           
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
     public void resetDirection(){
        up = false;
        down = false;
        right = false;
        left = false;
     }

     public void updateWindowFocus(){
        resetDirection();
     }

     public void setAttacking( boolean attacking){
         this.attacking = attacking;
     }

     public boolean isUp(){
        return up;
     }
     public void setUp(boolean up){
        this.up = up;
     }
     public boolean isDown(){
        return down;
     }
     public void setDown(boolean down){
        this.down = down;
     }
     public boolean isRight(){
        return right;
     }
     public void setRight(boolean right){
        this.right = right;
     }
     public boolean isLeft(){
        return left;
     }
     public void setLeft(boolean left){
        this.left = left;
     }


}
