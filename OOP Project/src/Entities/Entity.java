package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
public abstract class Entity {

    protected float x , y ;
    protected int width , height;
    protected Rectangle2D.Float hitBox;
    protected int aniIndex , aniTick;
    protected int state ;
    protected float airSpeed;
    protected boolean inAir = false;
    protected float walkSpeed;
    protected   Rectangle2D.Float attackBox;
    protected int maxHealth ;
    protected int currentHealth ;

    public Entity(float x , float y , int width , int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
     
    }

    public void drawHitbox(Graphics g , int lvlOffset ){
        g.setColor(Color.orange);
        g.drawRect((int)hitBox.x - lvlOffset , (int)hitBox.y, (int)hitBox.width,(int) hitBox.height);
    }

    protected void initHitbox( int width , int height){
        hitBox = new Rectangle2D.Float( x , y , width , height);
    }

    // public void updateHitbox(){
    //     hitBox.x = (int) x;
    //     hitBox.y = (int) y ;
    // }

    protected void drawAttackBox(Graphics g , int offsetLvl){
        g.setColor(Color.red);
        g.drawRect((int)attackBox.x - offsetLvl , (int)attackBox.y , (int)attackBox.width , (int)attackBox.height);
      }
      

    public Rectangle2D.Float getHitbox(){
        return hitBox;
    }

    protected int getEnemyState() {
        return state;
    }
    
    protected int getAniIndex(){
        return aniIndex;
    }


}
