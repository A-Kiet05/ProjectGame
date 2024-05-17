package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public abstract class Entity {

    protected float x , y ;
    protected int width , height;
    protected Rectangle hitBox;

    public Entity(float x , float y , int width , int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
    }

    public void drawHitbox(Graphics g ){
        g.setColor(Color.orange);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    private void initHitbox(){
        hitBox = new Rectangle((int) x , (int) y , width , height);
    }

    public void updateHitbox(){
        hitBox.x = (int) x;
        hitBox.y = (int) y ;
    }

    public Rectangle getHitbox(){
        return hitBox;
    }
}
