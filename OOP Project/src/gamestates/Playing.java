package gamestates;

import java.awt.event.MouseEvent;

import Entities.Player;
import levels.levelManager;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import ui.PauseOverlay;
import ultiz.loadSave;


public class Playing extends State implements stateMethods {

    private Player player;
   
    private levelManager levelmanager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;

    private int xlvlOffset ;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWidth = loadSave.GetLevelData()[0].length ;
    private int maxTilesOffset = lvlTilesWidth -  Game.TILES_WIDTH_DEFAULT;
    private int lvlMaxOffsetX = maxTilesOffset * Game.TILES_SIZE;

    public Playing(Game game){
        super(game);
        initClasses();
    }


     private void initClasses(){

        levelmanager = new levelManager(game);
        player = new Player (200, 200 , (int) (64*GAME_SCALE) ,(int) (40*GAME_SCALE));
        player.loadLevelData(levelmanager.getCurrentLevel().GetLevelData());
        pauseOverlay = new PauseOverlay(this);

    }



    @Override
    public void update(){

        if(!paused){

         levelmanager.update();
         player.update();
         checkClosetoBorder();
        }

         else{
         pauseOverlay.update();
         }
    }
    @Override
    public void draw(Graphics g ){
        levelmanager.draw(g , xlvlOffset);
        player.render(g , xlvlOffset);

        if(paused){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        pauseOverlay.draw(g);
        }
    }

    private void checkClosetoBorder(){
        
        int playerX = (int) player.getHitbox().x; 
        int differ =playerX - xlvlOffset;

        if(differ > rightBorder){
            xlvlOffset += differ - rightBorder;
        }
        else if( differ < leftBorder){
            xlvlOffset += differ - leftBorder;
        }


        if(xlvlOffset > lvlMaxOffsetX){
            xlvlOffset = lvlMaxOffsetX;
        }
        else if (xlvlOffset < 0){
            xlvlOffset =  0;
        }
    }
    @Override 
    public void mousePressed(MouseEvent e){
             if(paused){
                pauseOverlay.mousePressed(e);
             }
    }

    public void mouseDragged(MouseEvent e){
          if(paused){
            pauseOverlay.mouseDragged(e);
          }
    }

    @Override 
     public void mouseReleased(MouseEvent e){
        if(paused){
            pauseOverlay.mouseReleased(e);
        }
     }
     @Override 
     public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
           player.setAttacking(true);
         }
     }
     @Override 
     public void mouseMoved(MouseEvent e){
           if(paused){
            pauseOverlay.mouseMoved(e);
           }
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
            
            case KeyEvent.VK_ESCAPE:
             paused = !paused;
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
     public void unpauseGame(){
        paused = false;
     }

     public Player getPlayer(){
        return player;
    }
    public void updateWindowFocus(){
        player.resetDirection();
     }

}
