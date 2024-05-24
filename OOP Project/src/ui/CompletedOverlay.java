package ui;
import static main.Game.GAME_SCALE;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import gamestates.gameStates;
import main.Game;
import ultiz.loadSave;
import static ultiz.Constant.UI.UrmButton.*;

public class CompletedOverlay {

    private Playing playing ;
    private BufferedImage img;
    private UrmButton menu , next ;
    private int completedX , completedY, completedW , completedH;

    public CompletedOverlay(Playing playing ){
        this.playing = playing;
        initImgs();
        initMenuAndNext();
    }

    private void initImgs(){
        img = loadSave.GetSpritesAtlas(loadSave.COMPLETED_SPRITE);
        completedW = (int) (img.getWidth() *GAME_SCALE);
        completedH =(int) (img.getHeight() *GAME_SCALE);
        completedX = Game.GAME_WIDTH/2 - completedW/2;
        completedY = (int) (80 * Game.GAME_SCALE);
    }

    private void initMenuAndNext(){
      int  menuX = (int) (330 * GAME_SCALE);
      int  nextX = (int) (450 * GAME_SCALE);
      int y = (int) (200 * GAME_SCALE);
      menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
      next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
    }

    public void update(){
       menu.update();
       next.update();
    }
    public void draw(Graphics g ){
          g.drawImage(img, completedX, completedY,completedW , completedH , null);
          menu.draw(g);
          next.draw(g);
    }

    private boolean isIn(UrmButton u , MouseEvent e){
        return u.getBounds().contains(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e){
         if(isIn(menu, e)){
            menu.setMousePressed(true);
         }
         else if(isIn(next, e)){
            next.setMousePressed(true);
         }
    }

    public void mouseReleased(MouseEvent e){
           if(isIn(menu, e)){
              if(menu.isMousePressed()){
                  playing.resetAll();
                  gameStates.state = gameStates.MENU;
                  
              }
           }
           else if(isIn(next, e)){
                if(next.isMousePressed()){
                
                 playing.loadNextLevel();
            }
         }

         menu.reserAllBools();
         next.reserAllBools();
    }

    public void mouseMoved(MouseEvent e){
             menu.setMouseOver(false);
             next.setMouseOver(false);

             if(isIn(menu, e)){
                menu.setMouseOver(true);
             }
             else if(isIn(next, e)){
                next.setMouseOver(true);
             }
    }
}
