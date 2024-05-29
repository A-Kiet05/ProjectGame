package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


import static ultiz.helpMethods.GetXPosCollide;


import gamestates.Playing;
import main.Game;

import static ultiz.helpMethods.CanMoveHere;
import static ultiz.helpMethods.GetYPosAtRoofOrFalling;
import static ultiz.helpMethods.IsEntityOnTheFloor;

import ultiz.Constant.playerConstants;
import ultiz.loadSave;
import static ultiz.Constant.aniSpeed;


import static ultiz.Constant.playerConstants;

import static ultiz.Constant.Enemy.IDLE;
import static main.Game.GAME_SCALE;
import static main.Game.TILES_SIZE;
import static ultiz.Constant.GRAVITY;


public class Player extends Entity
 {
    private BufferedImage[][] Animations;
    
    
    private int[][] lvldata;
   
   private boolean isMoving = false , attacking = false;
   
   private float xDrawOffset = 21 * GAME_SCALE;
   private float yDrawOffset = 4 * GAME_SCALE;
  
   
  
   private float jumpSpeed = -2.25f * GAME_SCALE;
   private float fallAfterCollision = 0.5f * GAME_SCALE;
   

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

   private int healthWidth = healthBarWidth;
   //power bar
   private int powerBarWidth= (int) (104 * GAME_SCALE);
   private int powerBarHeight = (int) (2 * GAME_SCALE);
   private int powerBarX = (int) (44 * GAME_SCALE);
   private int powerBarY = (int) (34 * GAME_SCALE);

   private int powerMaxValue =200;
   private int powerCurrentValue = powerMaxValue;
   private int powerWidth = powerBarWidth;

   private boolean powerAttack;
   private int powerAttackTick;
   private int powerSpeedGrow = 15;
   private int powerGrowTick;


   private int tileY;
   
   
   private boolean left , right ,  jump;
   private int flipW = 1 ;
   private int flipX = 0 ;
   private Playing playing;
   private boolean attackChecked;
   
   

    public Player (float x , float y , int width ,int height, Playing playing){

        super( x, y, width, height);
        this.playing = playing;
        this.state = IDLE;
        this.walkSpeed = (1.05f *GAME_SCALE);
        this.maxHealth = 100;
        this.currentHealth = 65;
        LoadImg();
        initHitbox( (int) (20*GAME_SCALE) ,  (int) (27 *GAME_SCALE));
        initAttackBox();
        
        

    }

   

    public void setPlayerSqawn( Point sqawn){
         this.x = sqawn.x;
         this.y = sqawn.y;
         hitBox.x = x;
         hitBox.y = y;
    }

   private void initAttackBox(){
    attackBox = new Rectangle2D.Float(x , y, (int)(20*GAME_SCALE) , (int)(20*GAME_SCALE));
   }

   public void updateHealth(){
     healthWidth = (int) ((currentHealth /(float) maxHealth) * healthBarWidth);
  }
  public void updatePowerBar(){
     powerWidth = (int) ((powerCurrentValue /(float) powerMaxValue) * powerBarWidth);

     powerGrowTick++;
     if(powerGrowTick >= powerSpeedGrow){
        powerGrowTick = 0 ;
        changePower(2);
     }
  }

  public void hurt(){
     currentHealth = 0;
  }
  public void changePower(int value){
      powerCurrentValue += value;
      if(powerCurrentValue >= powerMaxValue){
         powerCurrentValue = powerMaxValue;
      }else if(powerCurrentValue <= 0 ){
         powerCurrentValue = 0 ;
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


  private void drawStatusBar(Graphics g ){
       //background status bar
       g.drawImage(statusBar, statusX, statusY,statusWidth , statusHeight ,  null);

       //health bar
       g.setColor(Color.RED);
       g.fillRect(healthX +statusX , healthY +statusY , healthWidth , healthBarHeight);

       //power bar
       g.setColor(Color.BLUE);
       g.fillRect(powerBarX + statusX, powerBarY + statusY, powerWidth, powerBarHeight);
      
  }

  public void update(){

    updateHealth(); 
    updatePowerBar();

    if(currentHealth <=0){
       if( state != playerConstants.DEAD){
          state = playerConstants.DEAD; 
          resetAnimation();
          playing.setPlayerDie(true);

       }else if (aniIndex == playerConstants.GetAmountSprites(playerConstants.DEAD) - 1 && aniTick >= aniSpeed - 1 ){
          playing.setGameOver(true);
       }
       else
          updateAniTick();
       
        return;
    }

     updateAttackBox();

     setPosition();
     if(isMoving){
       checkPotionsTouched();
       checkTrapsTouched();
       tileY = (int) (hitBox.y /TILES_SIZE);
       if(powerAttack){
           powerAttackTick++;
            if(powerAttackTick >= 35){
                 powerAttackTick = 0;
                 powerAttack = false;
            }
       }
     }
     if(attacking || powerAttack)
       checkAttackHit();
       updateAniTick();
       setAnimations();
     
  }

  
  public void render(Graphics g , int lvlOffset){
   
   if(left && !right ){ 
      flipW = -1;
      flipX = width;
      g.drawImage(Animations[state][aniIndex],(int)( hitBox.x  - xDrawOffset - lvlOffset + flipX) ,(int) (hitBox.y - yDrawOffset),( width * flipW) , height, null);
      
   }
   else if(right && !left){
      g.drawImage(Animations[state][aniIndex],(int)( hitBox.x  - xDrawOffset - lvlOffset + flipX) ,(int) (hitBox.y - yDrawOffset),( width * flipW) , height, null);
   } 
   else if ((!left && !right) || ( left && right)){
      g.drawImage(Animations[state][aniIndex],(int)( hitBox.x  - xDrawOffset - lvlOffset + flipX) ,(int) (hitBox.y - yDrawOffset),( width * flipW) , height, null);
   }
   //  drawHitbox(g, lvlOffset);
   //  drawAttackBox(g , lvlOffset);
    drawStatusBar(g);
}

  private void checkTrapsTouched(){
     playing.checkTrapsInteractive(this);
  }

  public void checkPotionsTouched(){
    playing.checkPotionsTouched(hitBox);
  }
  private void checkAttackHit(){
     if(attackChecked || aniIndex != 1){
         return;
     }
     attackChecked = true;
     if(powerAttack){
        attackChecked = false;
     }
     
     playing.checkEnemyGetHit(attackBox);
     playing.checkObjectGetHit(attackBox);
  }

  private void updateAttackBox(){
     
     resetAttackBox();
    
    if(left || (powerAttack && flipW == -1))
      attackBox.x = hitBox.x - hitBox.width - (int)(10 * GAME_SCALE);

    else if(right || (powerAttack && flipW == 1))
      attackBox.x = hitBox.x +hitBox.width +(int) (10*GAME_SCALE);
    

  
      attackBox.y = hitBox.y +(int) (10 *GAME_SCALE);
   
  }


   
    private void updateAniTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
          aniTick = 0;
          aniIndex++;
          if(aniIndex >= playerConstants.GetAmountSprites(state)){
              aniIndex = 0 ;
              attacking = false;
              attackChecked = false;
          }
        }
  }

 

 
  
private void setAnimations(){
    int startAnimation =state;
    
    if(isMoving){
      state = playerConstants.RUNNING;
    }
    
    else{
      state = playerConstants.IDLE;
    }
    
    
    if(inAir){
       if (airSpeed < 0 )
       state = playerConstants.JUMP;
       else
       state = playerConstants.FALLING;
    }

    if(powerAttack){
       state = playerConstants.ATTACKING;
       aniIndex = 1;
       aniTick = 0;
       return;
    }
    
    if(attacking){
      state = playerConstants.ATTACKING;
        if(startAnimation != playerConstants.ATTACKING){
          aniIndex = 1;
          aniTick= 0;
          return;
        }

    }
    
    
    if ( startAnimation !=  state){
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
         if(!powerAttack){
          if((!left && !right) || (right && left)) {
                return;
          }
        }
      }
       
      float xSpeed = 0 ;
     
     

      if (left && !right )
        xSpeed -= walkSpeed;
        flipW = -1;
        flipX = width ;
        
        
       
      if ( right && !left)
       xSpeed += walkSpeed;
       flipW = 1;
       flipX = 0;
       
      if(powerAttack){
          if((!left && !right) || (left && right) ){

             if (flipW == 1)
                xSpeed = walkSpeed;

             else if(flipW == -1)
                xSpeed = -walkSpeed;
             
          }
        xSpeed *= 2 ;
      }
      

      
      
      if(!inAir)
         if(!IsEntityOnTheFloor(hitBox, lvldata))
             inAir = true;
          
       
         

       if(inAir && !powerAttack){
         if(CanMoveHere( hitBox.x  , hitBox.y + airSpeed , hitBox.width, hitBox.height , lvldata)){
             hitBox.y += airSpeed;
             airSpeed += GRAVITY;
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

    public void power(){
      if(powerAttack)
         return;
      if (powerCurrentValue >= 65){
          powerAttack = true;
          changePower(-65);
      }
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
     public int getYtile(){
      return tileY;
     }

    

     public void resetAll(){

      isMoving = false;
      inAir = false;
      attacking = false;
      airSpeed = 0 ;
      hitBox.x = x;
      hitBox.y = y;
      resetDirection();
      currentHealth = maxHealth;
      state =IDLE;
       
      resetAttackBox();

        if(!IsEntityOnTheFloor(hitBox, lvldata))
            inAir = true ;

     }

     private void resetAttackBox(){
       if( flipW == -1)
         attackBox.x = hitBox.x - hitBox.width - (int)(10 * GAME_SCALE);
       
       
       else if ( flipW == 1)
         attackBox.x = hitBox.x + hitBox.width +(int) (10*GAME_SCALE);
       
     }


}
