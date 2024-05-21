package Entities;
import static main.Game.GAME_SCALE;
import static ultiz.Constant.Enemy.*;
public class Crabby extends Enemy {

    public Crabby(int x , int y ){
        super(x , y ,CRAB_WIDTH , CRAB_HEIGHT , CRAB);
        initHitbox(x, y, (int) (22 * GAME_SCALE),(int) (19 *GAME_SCALE));
    }
    
}
