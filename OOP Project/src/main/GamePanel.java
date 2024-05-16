package main;


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.*;
import inputs.KeyBoardInputs;




public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Game game;

   


    public GamePanel(Game game){
        this.game = game;
        mouseInputs = new MouseInputs(this);
        setPanelSize();
       
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }
    
  

   
  

    public void setPanelSize(){
        Dimension size = new Dimension(1280,800);
       
        setPreferredSize(size);
    }


    
   
   
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.draw(g);
      
    }
    public Game getGame(){
        return game;
    }
    
    
     
}
