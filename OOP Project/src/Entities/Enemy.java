 package Entities;
 import static ultiz.Constant.Enemy.*;

import java.awt.Graphics;
 abstract class Enemy extends Entity {

     private int aniIndex , enemyType , enemyState;
     private int aniTick , aniSpeed = 25;
     
    public Enemy (int x , int y , int width , int height , int enemyType ){
        super(x, y, width , height);
        this.enemyType = enemyType;

    }

    public void update(){
         updateAnimationTick();
    }

    public void draw(Graphics g ){

    }


    private void updateAnimationTick(){
        aniTick ++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex ++;
            if(aniIndex >= GetAmountSprites(enemyType, enemyState)){
                aniIndex = 0 ;
            }
        }
    }

    public int getEnemyState(){
        return enemyState;
    }

    public int getEnemyType(){
        return enemyType;
    }
    public int getAniIndex (){
        return aniIndex;
    }
}
