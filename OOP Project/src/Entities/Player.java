package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import static ultiz.helpMethods.GetXPosCollide;
import javax.imageio.ImageIO;

import gamestates.Playing;

import static ultiz.helpMethods.CanMoveHere;
import static ultiz.helpMethods.GetYPosAtRoofOrFalling;
import static ultiz.helpMethods.IsEntityOnTheFloor;

import main.GamePanel;
import ultiz.loadSave;
import ultiz.Constant.Direction;
import ultiz.Constant.playerConstants;

import static ultiz.Constant.playerConstants;
import static ultiz.Constant.Enemy.ATTACKING;
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
   private boolean left , right ,  jump;
   private float airSpeed = 0f;
   private float gravity = 0.04f * GAME_SCALE;
   private float jumpSpeed = -2.25f * GAME_SCALE;
   private float fallAfterCollision = 0.5f * GAME_SCALE;
   private boolean inAir = false ;

   private BufferedImage statusBar;

   //STATUS UI
   private int statusWidth = (int)(192 *GAME_SCALE);
   private int statusHeight = (int)(58 *GAME_SCALE);
   private int statusX = (int)(10 *GAME_SCALE) ;
   private int statusY = (int)(10 *GAME_SCALE);

   // health bar
   private int healthBarWidth =(int) (150 *GAME_SCALE);
   private int healthBarHeight= (int)(4 *GAME_SCALE);
   private int healthX = (int) (34 *GAME_SCALE);
   private int healthY = (int) (14 *GAME_SCALE);

   private int maxHealth = 100;
   private int currentHealth = maxHealth;
   private int healthWidth = healthBarWidth;


   //attackBox
   private Rectangle2D.Float attackBox;
   //flip the animation 
    private int flipX  ;
    private int flipW  ;
    
    private Playing playing;
    private boolean attackChecked;
   
   

    public Player (float x , float y , int width ,int height, Playing playing){
        super( x, y, width, height);
        this.playing = playing;
        LoadImg();
        initHitbox(x , y , (int) (20*GAME_SCALE) ,  (int) (27 *GAME_SCALE));
      
        initAttackBox();
        
        

    }

   private void initAttackBox(){
    attackBox = new Rectangle2D.Float(x , y, (int)(20*GAME_SCALE) , (int)(20*GAME_SCALE));
   }

   public void updateHealth(){
     healthWidth = (int) ((currentHealth /(float) maxHealth) * healthBarWidth);
  }
 


    public void render(Graphics g , int lvlOffset){
        
      g.drawImage(Animations[playerAction][aniIndex],(int)(( hitBox.x  - xDrawOffset) - lvlOffset + flipX) ,(int) (hitBox.y - yDrawOffset), width * flipW, height, null);
     //  drawHitbox(g, lvlOffset);
    //  drawAttackBox(g , lvlOffset);
      drawStatusBar(g);
  }

  private void drawAttackBox(Graphics g , int offsetLvl){
    g.setColor(Color.red);
    g.drawRect((int)attackBox.x - offsetLvl , (int)attackBox.y , (int)attackBox.width , (int)attackBox.height);
  }
  


  private void drawStatusBar(Graphics g ){
       g.drawImage(statusBar, statusX, statusY,statusWidth , statusHeight ,  null);
       g.setColor(Color.RED);
       g.fillRect(healthX +statusX , healthY +statusY , healthWidth , healthBarHeight);
      
  }

  public void update(){

    updateHealth(); 
    if(currentHealth <=0){
      playing.setGameOver(true);
      return;
    }

    updateAttackBox();

     setPosition();
     if(attacking)
       checkAttackHit();
     updateAniTick();
     setAnimations();
     
  }
  private void checkAttackHit(){
     if(attackChecked || aniIndex != 1){
         return;
     }
     attackChecked = true;
     playing.checkEnemyGetHit(attackBox);
  }

  private void updateAttackBox(){
    if(left){
      attackBox.x = hitBox.x - hitBox.width - (int)(10 * GAME_SCALE);
    }else if(right){
      attackBox.x = hitBox.x +hitBox.width +(int) (10*GAME_SCALE);
    }

  
      attackBox.y = hitBox.y +(int) (10 *GAME_SCALE);
   
  }


   
    private void updateAniTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
          aniTick = 0;
          aniIndex++;
          if(aniIndex >= playerConstants.GetAmountSprites(playerAction)){
              aniIndex = 0 ;
              attacking = false;
              attackChecked = false;
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
    
    
    if(inAir){
       if (airSpeed < 0 )
           playerAction = playerConstants.JUMP;
       else
           playerAction = playerConstants.FALLING;
    }
    
    if(attacking){
        playerAction = playerConstants.ATTACKING;
        if(startAnimation != playerConstants.ATTACKING){
          aniIndex = 1;
          aniTick= 0;
          return;
        }

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
      isMoving = false;
     

      if(jump){

         jump();
      }

       
      if ( !inAir){
          if((!left && !right) || (right && left)) {
                return;
          }
      }
       
      float xSpeed = 0 ;
     
     

      if (left )
        xSpeed -= playerSpeed;
        flipX = width ;
        flipW = -1;
        
       
      if ( right)
       xSpeed += playerSpeed;
       flipX = 0 ;
       flipW = 1;
      
      

      
      
      if(!inAir)
         if(!IsEntityOnTheFloor(hitBox, lvldata))
             inAir = true;
          
       
         

       if(inAir){
         if(CanMoveHere( hitBox.x  , hitBox.y + airSpeed , hitBox.width, hitBox.height , lvldata)){
             hitBox.y += airSpeed;
             airSpeed += gravity;
            
             updateXPos(xSpeed);
            
           
           
         }
         else {
           hitBox.y = GetYPosAtRoofOrFalling(hitBox , airSpeed);
           if(airSpeed > 0){
              inAirReset();
           }
           else{
            // airSpeed < 0 
               airSpeed = fallAfterCollision;
               
               updateXPos(xSpeed);
           }
              
              
              
         }
      }
       else{
         updateXPos(xSpeed);
           isMoving = true;
      }

 }
 private void jump(){
   if(inAir)
        return;
   inAir = true;
   airSpeed = jumpSpeed;
    
}

 private void inAirReset(){
   inAir = false;
   airSpeed = 0 ;
  
 }
 private void updateXPos(float xSpeed){
    if(CanMoveHere( hitBox.x + xSpeed , hitBox.y  , hitBox.width , hitBox.height , lvldata)){
           hitBox.x += xSpeed;
           
      }
    else{
           hitBox.x = GetXPosCollide(hitBox ,xSpeed);
      }
 }

    
     public void changeCurrentHealth(int value){

      currentHealth += value;
       if(currentHealth <= 0 ){
        currentHealth = 0;

      }
       else if(currentHealth >= maxHealth){
        currentHealth = maxHealth;
       }
     }

     public void LoadImg(){
     
        
        BufferedImage img = loadSave.GetSpritesAtlas(loadSave.PLAYER_ATLAS);

          Animations = new BufferedImage[7][8];
        for(int j = 0 ; j < Animations.length ; ++j)
        for (int i= 0 ; i < Animations[j].length ; ++i)
            Animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
        
        statusBar = loadSave.GetSpritesAtlas(loadSave.STATUS_BAR);
      
      
      
    
     }

     public void loadLevelData( int[][] lvldata){
        this.lvldata = lvldata;
        if(!IsEntityOnTheFloor(hitBox, lvldata))
              inAir = true ;
           
        
     }
     public void resetDirection(){
       
        right = false;
        left = false;
     }

  

     public void setAttacking( boolean attacking){
         this.attacking = attacking;
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
     public void setJump(boolean jump){
      this.jump = jump;
     }

     public void resetAll(){
      isMoving = false;
      inAir = false;
      attacking = false;
      hitBox.x = x;
      hitBox.y = y;
      resetDirection();
      currentHealth = maxHealth;
      playerAction = playerConstants.IDLE;

        if(!IsEntityOnTheFloor(hitBox, lvldata))
            inAir = true ;

     }


}
