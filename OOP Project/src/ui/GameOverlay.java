package ui;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.awt.event.MouseEvent;

import static main.Game.GAME_SCALE;
import static main.Game.GAME_WIDTH;
import static ultiz.Constant.UI.UrmButton.*;
import java.awt.Color;
import gamestates.Playing;
import gamestates.gameStates;
import main.Game;
import ultiz.loadSave;
public class GameOverlay {
    private Playing playing;
    private BufferedImage deathImgs;
    private int deathX , deathY , deathW , deathH;
    private UrmButton menu , playagain;

    public GameOverlay(Playing playing){
        this.playing = playing ;
        initDeathImgs();
        initButtons();
    }

    public void update(){
        menu.update();
        playagain.update();
    }

    private void initButtons(){
        int  menuX = (int) (330 * GAME_SCALE);
        int  replayX = (int) (443 * GAME_SCALE);
        int y = (int) (190 * GAME_SCALE);
        
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
        playagain = new UrmButton(replayX, y, URM_SIZE, URM_SIZE, 1);
    }

    private void initDeathImgs(){
        deathImgs = loadSave.GetSpritesAtlas(loadSave.DEATH);
        deathW = (int) (deathImgs.getWidth() * GAME_SCALE);
        deathH = (int) (deathImgs.getHeight() * GAME_SCALE);
        deathX = GAME_WIDTH/2 - deathW/2;
        deathY = (int) (95 * GAME_SCALE);
    }

    public void draw(Graphics g ){
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(deathImgs, deathX, deathY, deathW, deathH , null);
        menu.draw(g);
        playagain.draw(g);
    }

    public void keyPressed(KeyEvent e){
        
    }

    private boolean isIn(UrmButton d , MouseEvent e){
        return d.getBounds().contains(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e){
         if(isIn(menu, e)){
            menu.setMousePressed(true);
         }
         else if(isIn(playagain, e)){
            playagain.setMousePressed(true);
         }
    }

    public void mouseReleased(MouseEvent e){
           if(isIn(menu, e)){
              if(menu.isMousePressed()){
                  playing.resetAll();
                  gameStates.state = gameStates.MENU;
                  
              }
           }
           else if(isIn(playagain, e)){
                if(playagain.isMousePressed()){
                   playing.resetAll();
                   playing.unpauseGame();
                
            }
         }

        menu.reserAllBools();
        playagain.reserAllBools();
    }

    public void mouseMoved(MouseEvent e){
             menu.setMouseOver(false);
             playagain.setMouseOver(false);

             if(isIn(menu, e)){
                menu.setMouseOver(true);
             }
             else if(isIn(playagain, e)){
                playagain.setMouseOver(true);
             }
    
    }
}
