package gameObject;

import static main.Game.GAME_SCALE;

public class Potion extends GameObject {

    public Potion(int x , int y , int objType){
        super(x , y , objType);
        doAnimation = true;
        initHitbox(7, 14);
        xDrawOffset = (int) (2 * GAME_SCALE);
        yDrawOffset = (int) (3*GAME_SCALE);
    }

    public void update(){
        updateAnimationTick();
    }

    
    
}
