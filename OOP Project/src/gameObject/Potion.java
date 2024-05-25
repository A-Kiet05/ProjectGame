package gameObject;

import static main.Game.GAME_SCALE;

public class Potion extends GameObject {

    private float hoverOffset;
    private int maxHoverOffset , hoverDir = 1;


    public Potion(int x , int y , int objType){
        super(x , y , objType);
        doAnimation = true;
        initHitbox(7, 14);
        xDrawOffset = (int) (2 * GAME_SCALE);
        yDrawOffset = (int) (3*GAME_SCALE);

        maxHoverOffset = (int) (10 * GAME_SCALE);
    }

    public void update(){
        updateAnimationTick();
        updateHover();
    }
    private void updateHover(){
       hoverOffset += (0.09f * GAME_SCALE * hoverDir);

       if(hoverOffset >= maxHoverOffset){
           hoverDir = -1;

       }
       else if(hoverOffset < 0){
          hoverDir = 1;
       }

       hitBox.y = y + hoverOffset;
    }

    
    
}
