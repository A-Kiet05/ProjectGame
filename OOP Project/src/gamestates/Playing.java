package gamestates;

import java.awt.event.MouseEvent;

import Entities.EnemyManager;
import Entities.Player;
import levels.levelManager;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.event.MouseListener;
import ui.PauseOverlay;
import ultiz.loadSave;
import static ultiz.Constant.Environment.*;


public class Playing extends State implements stateMethods {

    private Player player;
   
    private levelManager levelmanager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;

    private int xlvlOffset ;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWidth = loadSave.GetLevelData()[0].length ;
    private int maxTilesOffset = lvlTilesWidth -  Game.TILES_WIDTH_DEFAULT;
    private int lvlMaxOffsetX = maxTilesOffset * Game.TILES_SIZE;

    private BufferedImage playing_bg , bigClouds, smallClouds;
    private int[] smallCloudPos;
    private Random random = new Random();

    public Playing(Game game){
        super(game);
        initClasses();

        playing_bg = loadSave.GetSpritesAtlas(loadSave.PLAYING_BG);
        bigClouds = loadSave.GetSpritesAtlas(loadSave.BIG_CLOUDS);
        smallClouds = loadSave.GetSpritesAtlas(loadSave.SMALL_CLOUDS);
        smallCloudPos = new int[8];
        for(int i = 0 ; i <smallCloudPos.length ; ++i){
            smallCloudPos[i] = (int) (100 *GAME_SCALE) + random.nextInt((int) (100 * GAME_SCALE));
        }
    }


     private void initClasses(){

        levelmanager = new levelManager(game);
        player = new Player (200, 200 , (int) (64*GAME_SCALE) ,(int) (40*GAME_SCALE));
        player.loadLevelData(levelmanager.getCurrentLevel().GetLevelData());
        pauseOverlay = new PauseOverlay(this);
        enemyManager = new EnemyManager(this);

    }



    @Override
    public void update(){

        if(!paused){

         levelmanager.update();
         player.update();
         checkClosetoBorder();
         enemyManager.update(levelmanager.getCurrentLevel().GetLevelData() , player);
        }

         else{
         pauseOverlay.update();
         }
    }
    @Override
    public void draw(Graphics g ){

        g.drawImage(playing_bg, 0,  0 , Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        drawClouds(g, xlvlOffset);
        levelmanager.draw(g , xlvlOffset);
        player.render(g , xlvlOffset);
        enemyManager.draw(g , xlvlOffset);

        if(paused){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        pauseOverlay.draw(g);
        }
    }

    private void drawClouds(Graphics g , int lvlOffset){

        //big clouds
         for (int i = 0 ; i < 3; ++i){
             g.drawImage(bigClouds, (i * BIG_CLOUDS_WIDTH ) - (int) (lvlOffset * 0.3 ) , (int) (220 *GAME_SCALE), BIG_CLOUDS_WIDTH , BIG_CLOUDS_HEIGHT,null);
         }

         //small clouds
         for(int i = 0 ; i < smallCloudPos.length ; ++i){
            g.drawImage(smallClouds, (i* 4 *SMALL_CLOUDS_WIDTH) - (int) (lvlOffset * 0.8), smallCloudPos[i] , SMALL_CLOUDS_WIDTH , SMALL_CLOUDS_HEIGHT, null);
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
