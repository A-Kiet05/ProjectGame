package Entities;
import static main.Game.GAME_SCALE;
import static ultiz.Constant.Enemy.*;
import static ultiz.helpMethods.*;
import static ultiz.Constant.Direction.*;
public class Crabby extends Enemy {

    public Crabby(int x , int y ){
        super(x , y ,CRAB_WIDTH , CRAB_HEIGHT , CRAB);
        initHitbox(x, y, (int) (22 * GAME_SCALE),(int) (19 *GAME_SCALE));
    }


    
    public void update(int[][] lvldata , Player player) {
        updateMove(lvldata , player);
        updateAnimationTick();
    }

    public void updateMove(int[][] lvldata , Player player) {
        if (firstUpdated) {
           firstUpdateCheck(lvldata);
        }

        if (inAir) {
            inAirCheck(lvldata);
        } else {
            switch (enemyState) {
                case IDLE:
                   newState(RUNNING);
                    break;
                case RUNNING:
                   movement(lvldata);
                   if(canSeePlayer(lvldata, player)){
                        changeDirTowardPlayer(player);
                        if(playerCloseToAttack(player)){
                            newState(ATTACKING);
                        }
                   }
                    break;

                default:
                    break;
            }

        }

    }

    
}
