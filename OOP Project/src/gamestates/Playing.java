package gamestates;

import java.awt.event.MouseEvent;

import Entities.Player;
import levels.levelManager;

import static main.Game.GAME_SCALE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;


public class Playing extends State implements stateMethods {

    private Player player;
    private levelManager levelmanager;

    public Playing(Game game){
        super(game);
        initClasses();
    }


     private void initClasses(){

        levelmanager = new levelManager(game);
        player = new Player (200, 200 , (int) (64*GAME_SCALE) ,(int) (40*GAME_SCALE));
        player.loadLevelData(levelmanager.getCurrentLevel().GetLevelData());

    }



    @Override
    public void update(){
         levelmanager.update();
         player.update();
    }
    @Override
    public void draw(Graphics g ){
        levelmanager.draw(g);
        player.render(g);
    }
    @Override 
    public void mousePressed(MouseEvent e){

    }
    @Override 
     public void mouseReleased(MouseEvent e){

     }
     @Override 
     public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
           player.setAttacking(true);
         }
     }
     @Override 
     public void mouseMoved(MouseEvent e){

     }
     @Override 
     public void keyPressed(KeyEvent e ){
        switch (e.getKeyCode()){
     
            case KeyEvent.VK_A:
           player.setLeft(true);
            break;
            
            case KeyEvent.VK_D:
            player.setRight(true);
            break;

            case KeyEvent.VK_SPACE:
            player.setJump(true);
            break;
            case KeyEvent.VK_BACK_SPACE:
            gameStates.state = gameStates.MENU;
            break;
        }
      
     }
     @Override 
     public void keyReleased(KeyEvent e ){
        switch (e.getKeyCode()){
      
            case KeyEvent.VK_A:
            player.setLeft(false);
            break;
           
            case KeyEvent.VK_D:
           player.setRight(false);
            break;

            case KeyEvent.VK_SPACE:
           player.setJump(false);
            break;
        }
     }

     public Player getPlayer(){
        return player;
    }
    public void updateWindowFocus(){
        player.resetDirection();
     }

}
