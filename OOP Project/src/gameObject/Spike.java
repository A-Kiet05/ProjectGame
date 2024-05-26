package gameObject;

import static main.Game.GAME_SCALE;
import static main.Game.TILES_SIZE;

public class Spike extends GameObject {
    
    public Spike(int x , int y , int objType){
        super(x , y, objType);
        initHitbox(32, 16 );
        xDrawOffset = 0;
        yDrawOffset = (int)(16* GAME_SCALE);

        hitBox.y += yDrawOffset;

    }

    public void update(){
        
    }
    
}
