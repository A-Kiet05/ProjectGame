package main;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

    public GamePanel(){
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(100, 100, 200, 150);
        g.fillRect(100, 100, 50, 200);
    }
}
