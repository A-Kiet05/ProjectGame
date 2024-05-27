package Entities;
import static main.Game.GAME_SCALE;
import static ultiz.Constant.Enemy.*;
import java.awt.geom.Rectangle2D;
import static ultiz.Constant.Direction.*;

public class Crabby extends Enemy {
    
   
    private int attackBoxOffsetX;


    public Crabby(int x , int y ){
        super(x , y ,CRAB_WIDTH , CRAB_HEIGHT , CRAB);
        initHitbox( (int) (22 * GAME_SCALE),(int) (19 *GAME_SCALE));
        initAttackBox();
    }

   private void initAttackBox(){
    attackBox = new Rectangle2D.Float(x , y , (int) 82*GAME_SCALE , (int) (19 *GAME_SCALE)) ;
    attackBoxOffsetX = (int)(30*GAME_SCALE);
   }
    
  

    
    public void update(int[][] lvldata , Player player) {
        updateBehaviour(lvldata , player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox(){
       attackBox.x = hitBox.x - attackBoxOffsetX;
       attackBox.y = hitBox.y;
    }

    public void updateBehaviour(int[][] lvldata , Player player) {
        if (firstUpdated) {
           firstUpdateCheck(lvldata);
        }

        if (inAir) {
            inAirCheck(lvldata);
        } else {
            switch (state) {
                case IDLE:
                   newState(RUNNING);
                    break;
                case RUNNING:
                   
                   if(canSeePlayer(lvldata, player)){
                        changeDirTowardPlayer(player);
                   if(playerCloseToAttack(player))
                            newState(ATTACKING);
                   }
                        
                    movement(lvldata);
                    break;
                case ATTACKING:

                    if (aniIndex ==0){
                        attackChecked = false;
                    }
                    if(aniIndex ==3 && !attackChecked){
                        checkPlayerGetHit(attackBox , player);
                    }


                   break;
                case HIT:
                    break;

                default:
                    break;
            }

        }

    }

   

    public int getFLipX(){
        if(walkDir == LEFT){
            return 0 ;
        }else{
            return width;
        }
    }

    public int getFlipW(){
        if(walkDir == LEFT){
            return 1;
        }else{
            return -1;
        }
    }

    
}
