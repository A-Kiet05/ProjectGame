package Entities;

import static main.Game.GAME_SCALE;
import static main.Game.TILES_SIZE;
import static ultiz.Constant.Enemy.*;
import static ultiz.helpMethods.*;
import java.awt.Graphics;
import static ultiz.Constant.Direction.*;

abstract class Enemy extends Entity {

    protected boolean firstUpdated = true;
    protected boolean inAir = false;
    protected float fallSpeed;
    protected float gravity = 0.04f * GAME_SCALE;
    protected float enemySpeed = 0.45f * GAME_SCALE;
    protected int walkDir = LEFT;

    protected int aniIndex, enemyType, enemyState;
    protected int aniTick, aniSpeed = 25;
    protected int tileY ;
    protected int attackSight = TILES_SIZE;

    public Enemy(int x, int y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;

    }


    public void draw(Graphics g) {

    }

    protected void firstUpdateCheck(int[][] lvldata){
        if (!IsEntityOnTheFloor(hitBox, lvldata))
        inAir = true;
        firstUpdated = false;
    }

    protected void inAirCheck(int[][] lvldata){
        if (CanMoveHere(hitBox.x, hitBox.y, hitBox.width, hitBox.height, lvldata)) {
            hitBox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitBox.y = GetYPosAtRoofOrFalling(hitBox, fallSpeed);
            tileY = (int) (hitBox.y / TILES_SIZE);
        }
    }

    protected void movement(int[][] lvldata){
        float xSpeed = 0;
                    if (walkDir == LEFT) {
                        xSpeed = -enemySpeed;
                    } else {
                        xSpeed = enemySpeed;
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
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0 ;
    }

  protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetAmountSprites(enemyType, enemyState)) {
                aniIndex = 0;
                if(enemyState == ATTACKING){
                    enemyState = IDLE;
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

    public int getEnemyState() {
        return enemyState;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getAniIndex() {
        return aniIndex;
    }
}
