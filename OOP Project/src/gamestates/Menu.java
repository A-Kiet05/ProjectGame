package gamestates;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;
import static main.Game.*;

public class Menu extends State implements stateMethods {
     
    public Menu(Game game){
        super(game);
    }

      @Override
    public void update(){

    }

    @Override
    public void draw(Graphics g ){
        g.setColor(Color.black);
        g.drawString("BLACKPINK", game.GAME_WIDTH/2, 200);
    }

    @Override 
    public void mousePressed(MouseEvent e){

    }

    @Override 
     public void mouseReleased(MouseEvent e){

     }

     @Override 
     public void mouseClicked(MouseEvent e){

     }

     @Override 
     public void mouseMoved(MouseEvent e){

     }

     @Override 
     public void keyPressed(KeyEvent e ){
          if(e.getKeyCode() == e.VK_ENTER){
            gameStates.state = gameStates.PLAYING;
          }
     }


     @Override 
     public void keyReleased(KeyEvent e ){

     }

}
