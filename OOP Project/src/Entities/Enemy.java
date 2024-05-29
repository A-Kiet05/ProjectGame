package Entities;

import static main.Game.GAME_SCALE;
import static main.Game.TILES_SIZE;
import static ultiz.Constant.Enemy.*;
import static ultiz.helpMethods.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static ultiz.Constant.GRAVITY;
import static ultiz.Constant.aniSpeed;
import static ultiz.Constant.Direction.*;

abstract class Enemy extends Entity {

    protected boolean firstUpdated = true;
  
    
    
   
    protected int walkDir = LEFT;

    protected int  enemyType;

    protected int tileY ;
    protected int attackSight = TILES_SIZE;

    protected int currentHealth ; 
    protected int maxHealth;

    protected boolean active = true;
    protected boolean attackChecked ;
    
    

    public Enemy(int x, int y, int width, int height, int enemyType) {

        super(x, y, width, height);
        this.enemyType = enemyType;
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
        walkSpeed =  (0.45f * GAME_SCALE);
        

    }

    protected void hurt(int value){
        currentHealth -= value;
        if(currentHealth <= 0){
            newState(DEAD);
        }else{
            newState(HIT);
        }
    }
    protected void checkPlayerGetHit(Rectangle2D.Float attackBox , Player player){
          if(attackBox.intersects(player.hitBox))
              player.changeCurrentHealth(getDmgHealth(enemyType));

            attackChecked = true;
          
    }

    public void draw(Graphics g) {

    }
    protected void resetEnemy(){
        hitBox.x = x;
        hitBox.y = y;
        active = true;
        currentHealth = maxHealth;
        newState(IDLE);
        airSpeed = 0 ;
        firstUpdated = true;
    }

    protected void firstUpdateCheck(int[][] lvldata){
        if (!IsEntityOnTheFloor(hitBox, lvldata))
        inAir = true;
        firstUpdated = false;
    }

    protected void inAirCheck(int[][] lvldata){
        if (CanMoveHere(hitBox.x, hitBox.y, hitBox.width, hitBox.height, lvldata)) {
            hitBox.y += airSpeed;
           airSpeed += GRAVITY;
        } else {
            inAir = false;
            hitBox.y = GetYPosAtRoofOrFalling(hitBox,airSpeed);
            tileY = (int) (hitBox.y / TILES_SIZE);
        }
    }

    protected void movement(int[][] lvldata){
        float xSpeed = 0;
                    if (walkDir == LEFT) {
                        xSpeed = -walkSpeed;
                    } else {
                        xSpeed = walkSpeed;
                    }

                    if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvldata)) {
                        if (IsFloor(hitBox, xSpeed, lvldata)) {
                            hitBox.x += xSpeed;
                            return;
                        }
                    }
                    changeWalkDir();
    }

    protected boolean canSeePlayer(int[][] lvldata , Player player){
        int playerYTile = (int) (player.getHitbox().y / TILES_SIZE);
       if(playerYTile == tileY){
          if(playerInRange(player)){
              if(IsSightClear(lvldata , hitBox, player.hitBox , tileY)){
                 return true;
              }
          }
       }
       return false;

    }
    protected boolean playerInRange(Player player){
        int absValue= (int) Math.abs(player.hitBox.x - hitBox.x);
        return absValue <= attackSight * 4;
    }
    protected boolean playerCloseToAttack(Player player){
        int absValue= (int) Math.abs(player.hitBox.x - hitBox.x);
        return absValue <= attackSight ;
    }

    protected void changeDirTowardPlayer(Player player){
        if(player.hitBox.x > hitBox.x){
            walkDir = RIGHT;
        }else{
            walkDir = LEFT;
        }
    }
    protected void newState( int enemyState){
        this.state = enemyState;
        aniTick = 0;
        aniIndex = 0 ;
    }

  protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetAmountSprites(enemyType, state)) {
                aniIndex = 0;
               
                switch (state) {
                    case ATTACKING , HIT  -> state =  IDLE;

                    case DEAD -> active = false;   
                       
                
                    
                }
            }
        }
    }

   
    protected void changeWalkDir() {
        if (walkDir == LEFT) {
            walkDir = RIGHT;
        } else {
            walkDir = LEFT;
        }
    }

   

    public int getEnemyType() {
        return enemyType;
    }

  
    public void setActive(boolean active){
        this.active = active;
    }
    public boolean isActive(){
        return active;
    }
    
}
