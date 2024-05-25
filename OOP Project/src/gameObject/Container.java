package gameObject;

import static main.Game.GAME_SCALE;
import static ultiz.Constant.ObjectConstants.BOX;

public class Container extends GameObject{

    public Container(int x , int y , int objType){
        super(x , y, objType);
        doAnimation = false;
        createHitbox();
    }

    private void createHitbox(){
        if(objType == BOX){
            initHitbox(25, 18);
            xDrawOffset = (int) (7 * GAME_SCALE);
            yDrawOffset = (int) (12 *GAME_SCALE);

        }else{
             initHitbox(23 , 25);
             xDrawOffset = (int)(8 * GAME_SCALE);
             yDrawOffset = (int) (5 *GAME_SCALE);

        }
    }

    public void update(){
        if(doAnimation){
        updateAnimationTick();
        }
    }
    
}
