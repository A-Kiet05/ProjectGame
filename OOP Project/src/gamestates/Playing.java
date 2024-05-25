package gamestates;

import java.awt.event.MouseEvent;

import Entities.EnemyManager;
import Entities.Player;
import gameObject.ObjectManager;
import levels.levelManager;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import main.Game;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.event.MouseListener;

import ui.CompletedOverlay;
import ui.GameOverlay;
import ui.PauseOverlay;
import ultiz.loadSave;
import static ultiz.Constant.Environment.*;


public class Playing extends State implements stateMethods {

    private Player player;
   
    private levelManager levelmanager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private GameOverlay gameOverlay;
    private CompletedOverlay completedOverlay ;
    private ObjectManager objectManager ;

    private boolean paused = false;
    private boolean gameOver = false;
    private boolean lvlCompleted = false;

    private int xlvlOffset ;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
   
    private int lvlMaxOffsetX;

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

        calculatingLvlOffset();
        loadStartLevel();

    }




     private void initClasses(){

        levelmanager = new levelManager(game);
        player = new Player (200, 200 , (int) (64*GAME_SCALE) ,(int) (40*GAME_SCALE) , this);
        player.loadLevelData(levelmanager.getCurrentLevel().GetLevelData());
        player.setPlayerSqawn(levelmanager.getCurrentLevel().getPlayerSqawn());
        pauseOverlay = new PauseOverlay(this);
        enemyManager = new EnemyManager(this);
        gameOverlay = new GameOverlay(this);
        completedOverlay = new CompletedOverlay(this);
        // objectManager = new ObjectManager(this);

    }

    private void calculatingLvlOffset(){
        lvlMaxOffsetX = levelmanager.getCurrentLevel().getlvlOffset();
    }

    private void loadStartLevel(){
        enemyManager.getCrabbies(levelmanager.getCurrentLevel());
    }
    public void loadNextLevel(){
        resetAll();
        levelmanager.loadNextLevel();
        player.setPlayerSqawn(levelmanager.getCurrentLevel().getPlayerSqawn());
    }

    @Override
    public void update(){

        if(paused){
            pauseOverlay.update();
        }
        else if(lvlCompleted){
            completedOverlay.update();
        }
        else if(!gameOver){
            levelmanager.update();
            objectManager.update();
            player.update();
            checkClosetoBorder();
            enemyManager.update(levelmanager.getCurrentLevel().GetLevelData() , player);
            
        }

        
    }
    @Override
    public void draw(Graphics g ){
       
        g.drawImage(playing_bg, 0,  0 , Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        drawClouds(g, xlvlOffset);
        levelmanager.draw(g , xlvlOffset);
        player.render(g , xlvlOffset);
        enemyManager.draw(g , xlvlOffset);
        objectManager.draw(g, xlvlOffset);

        if(paused){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        pauseOverlay.draw(g);
        }
        else if(gameOver){
            gameOverlay.draw(g);
        }
        else if(lvlCompleted){
            completedOverlay.draw(g);
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
        if(!gameOver){
             if(paused){
                pauseOverlay.mousePressed(e);
             }
             else if(lvlCompleted){
                completedOverlay.mousePressed(e);
             }
        }
    }

    public void mouseDragged(MouseEvent e){
        if(!gameOver)
          if(paused){
            pauseOverlay.mouseDragged(e);
          }
    }

    @Override 
     public void mouseReleased(MouseEvent e){
        if(!gameOver){

        if(paused){
            pauseOverlay.mouseReleased(e);
        }
        else if(lvlCompleted){
            completedOverlay.mouseReleased(e);
         }
      }
    }
     @Override 
     public void mouseClicked(MouseEvent e){
        if(!gameOver)
        if(e.getButton() == MouseEvent.BUTTON1){
           player.setAttacking(true);
         }
     }
     @Override 
     public void mouseMoved(MouseEvent e){
        if(!gameOver){

           if(paused){
            pauseOverlay.mouseMoved(e);
           }

           else if(lvlCompleted){
            completedOverlay.mouseMoved(e);
           }
        }
          
        
     }
     @Override 
     public void keyPressed(KeyEvent e ){
        if(gameOver){
            gameOverlay.keyPressed(e);
        }
        else
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
        if(!gameOver)
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
     public void setLvlOffset(int lvlMaxOffsetX){
        this.lvlMaxOffsetX = lvlMaxOffsetX;
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
     public void resetAll(){
          gameOver = false;
          paused = false;
          lvlCompleted = false;
          enemyManager.resetAllEnemies();
          player.resetAll();
     }

     public void checkEnemyGetHit(Rectangle2D.Float attackBox){
        enemyManager.checkEnemyGetHit(attackBox);
     }
     public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
     }

     public EnemyManager getEnemyManager(){
        return enemyManager;
     }

     public void setlvlCompleted(boolean lvlCompleted){
        this.lvlCompleted = lvlCompleted;
     }

}
