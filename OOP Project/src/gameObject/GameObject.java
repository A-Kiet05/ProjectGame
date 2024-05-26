package gameObject;
import static main.Game.GAME_SCALE;
import static ultiz.Constant.aniSpeed;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import static ultiz.Constant.ObjectConstants.*;
import java.awt.Graphics;
import java.awt.Color;


public class GameObject {
    protected int x , y , objType;
    protected Rectangle2D.Float hitBox;
    protected boolean doAnimation , active = true;
    protected int xDrawOffset , yDrawOffset;
    protected int aniIndex , aniTick;

    public GameObject(int x , int y , int objType){
        this.x = x;
        this.y = y;
        this.objType = objType;
        
    }

    protected void initHitbox(int width , int height){
        hitBox = new Rectangle2D.Float(x , y , (int) (width * GAME_SCALE) ,(int)( height *GAME_SCALE));
    }

    protected void drawHitbox(Graphics g , int lvlOffset ){
        g.setColor(Color.orange);
        g.drawRect((int)hitBox.x - lvlOffset , (int)hitBox.y, (int)hitBox.width,(int) hitBox.height);
    }

   protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetAmountSprites(objType)) {
                aniIndex = 0;
                if(objType == BARREL || objType == BOX){
                    doAnimation = false;
                    active = false;
                }

            }
        }
    }

    public void update(){
         updateAnimationTick();
    }

    public void resetAll(){
        aniIndex = 0;
        aniTick = 0;
        active = true;
        

       if(objType == BOX || objType == BARREL){
          doAnimation = false;
       }else{
          doAnimation = true;
       }
       
    }

    public int getAniIndex(){
        return aniIndex;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive(){
        return active;
    }
     public Rectangle2D.Float gethitBox(){
        return hitBox;
     }

     public int getObjectType(){
        return objType;
     }

     public int getXDrawOffset(){
        return xDrawOffset;
     }
     public int getYDrawOffset(){
        return yDrawOffset;
     }
     public void setAnimation(boolean doAnimation){
        this.doAnimation = doAnimation;
     }
}
