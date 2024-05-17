package Entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import static ultiz.helpMethods.CanMoveHere;
import main.GamePanel;
import ultiz.loadSave;
import ultiz.Constant.Direction;
import ultiz.Constant.playerConstants;

import static ultiz.Constant.playerConstants;
import static main.Game.GAME_SCALE;
import static main.Game.TILES_SIZE;
import static ultiz.Constant.Direction;

public class Player extends Entity
 {
    private BufferedImage[][] Animations;
    private int aniIndex=0, aniTick=0 , aniSpeed = 25;
    private int playerAction =playerConstants.IDLE;
    private int[][] lvldata;
   
   private boolean isMoving = false , attacking = false;
   private float playerSpeed =  1.5f ;
   private float xDrawOffset = 21 * GAME_SCALE;
   private float yDrawOffset = 4 * GAME_SCALE;
   private boolean left , right , up , down ;

    public Player (float x , float y , int width ,int height ){
        super( x, y, width, height);
        LoadImg();
        initHitbox(x , y , 20*GAME_SCALE , 28*GAME_SCALE);
        

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
       if (!left && !right && !up && !down)
       return ;

       float xSpeed = 0 , ySpeed = 0 ;

       if (left && !right){
        xSpeed = -playerSpeed;
         isMoving = true;
       }
        else if (!left && right){
       xSpeed = playerSpeed;
       isMoving = true;
        }


        if ( up && !down ){
            ySpeed = -playerSpeed;
            isMoving= true;
        }
        else if( !up && down){
            ySpeed = playerSpeed;
            isMoving = true;
        }

      if(CanMoveHere( hitBox.x + xSpeed , hitBox.y + ySpeed , hitBox.width , hitBox.height , lvldata)){
           hitBox.x += xSpeed;
           hitBox.y += ySpeed;
           isMoving = true;
      }

 }

     public void render(Graphics g){
         drawHitbox(g);
         g.drawImage(Animations[playerAction][aniIndex],(int)( hitBox.x  - xDrawOffset) ,(int) (hitBox.y - yDrawOffset),width , height, null);
     }

     public void update(){
        setPosition();
      //   updateHitbox();
        updateAniTick();
        setAnimations();
        
     }

     public void LoadImg(){
        
        BufferedImage img = loadSave.GetSpritesAtlas(loadSave.PLAYER_ATLAS);

          Animations = new BufferedImage[9][6];
        for(int j = 0 ; j < Animations.length ; ++j)
        for (int i= 0 ; i < Animations[j].length ; ++i)
            Animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
        
           
      
      
      
    
     }

     public void loadLevelData( int[][] lvldata){
        this.lvldata = lvldata;
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
