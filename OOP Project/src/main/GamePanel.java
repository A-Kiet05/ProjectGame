package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import inputs.*;
import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta  = 100 , yDelta = 100;
   
    private float xDir= 0.01f , yDir = 0.01f;


    public GamePanel(){
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    public void changeXdelta( int value){
        xDelta += value;
       
    }
    public void changeYdelta(int value){
        yDelta+= value;
       
    }
    public void setRecPos( int x , int y){
        this.xDelta = x;
        this.yDelta = y;
       
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
      
        g.setColor(Color.black);
        updateRectangle();
        g.fillRect((int)xDelta,  (int)yDelta, 150, 200);
        
       
        


        repaint();
    }
    public void updateRectangle(){
        xDelta += xDir;
        if(xDelta > 400 || xDelta < 0 ){
         xDir *= -1;

        }
        yDelta += yDir ;
        if (yDelta > 400 || yDelta < 0){
         yDir *=-1;
        }
     }
}
