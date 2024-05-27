package gameObject;

import static main.Game.GAME_SCALE;
import static main.Game.TILES_SIZE;

public class Cannon extends GameObject {
    private int tileY;
    
    
    public Cannon (int x , int y , int objType){
        super(x , y, objType);
        initHitbox(40, 26);
        tileY = y / TILES_SIZE;
        hitBox.x -= (int) (4*GAME_SCALE);
        hitBox.y += (int) (6 * GAME_SCALE);

    }

    public void update (){
        if(doAnimation){
            updateAnimationTick();
        }
    }

    public int getTileY(){
        return tileY;
    }

   
}
