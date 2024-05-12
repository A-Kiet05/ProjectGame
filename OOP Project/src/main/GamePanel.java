package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import inputs.*;
import inputs.KeyBoardInputs;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private int xDelta  = 100 , yDelta = 100;

    public GamePanel(){
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    public void changeXdelta( int value){
        xDelta += value;
        repaint();
    }
    public void changeYdelta(int value){
        yDelta+= value;
        repaint();
    }
    public void setRecPos( int x , int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
       
       
        g.fillRect(xDelta,  yDelta, 150, 200);
    }
}
