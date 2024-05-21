package Entities;
import static ultiz.Constant.Enemy.*;
public class Crabby extends Enemy {

    public Crabby(int x , int y ){
        super(x , y ,CRAB_WIDTH , CRAB_HEIGHT , CRAB);
        initHitbox(x, y, CRAB_WIDTH, CRAB_HEIGHT);
    }
    
}
