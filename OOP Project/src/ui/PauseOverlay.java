package ui;
import java.awt.image.BufferedImage;

import gamestates.gameStates;

import java.awt.event.MouseEvent;

import main.Game;
import ultiz.loadSave;
import java.awt.Graphics;
import static ultiz.Constant.UI.SoundButton.*;
import static ultiz.Constant.UI.UrmButton.URM_SIZE;
import static ultiz.Constant.UI.VolumeButton.SLIDER_WIDTH;
import static ultiz.Constant.UI.VolumeButton.VOLUME_HEIGHT;

import gamestates.Playing;
public class PauseOverlay {

    private BufferedImage pauseImgs;
    private int bgX , bgY , bgWidth , bgHeight;
    private SoundButton musicButton , sfxButton;
    private UrmButton menuB , replayB , unpauseB;
    private VolumeButton volumeButton ; 
    private Playing playing;
    private UIOptions uiOptions;

    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadImgs();
        
        loadUrmButton();
        uiOptions = playing.getGame().geUiOptions();
    }

  

    private void loadUrmButton(){
       int menuX = (int) (313 * Game.GAME_SCALE);
       int replayX = (int) (388 * Game.GAME_SCALE);
       int unpauseX = (int) (465 * Game.GAME_SCALE);
       int urmY = (int) (320 * Game.GAME_SCALE);

       menuB = new UrmButton(menuX, urmY, URM_SIZE, URM_SIZE, 2);
       replayB = new UrmButton(replayX, urmY, URM_SIZE, URM_SIZE, 1);
       unpauseB = new UrmButton(unpauseX, urmY, URM_SIZE, URM_SIZE, 0);
    }

   

    private void loadImgs(){
        pauseImgs = loadSave.GetSpritesAtlas(loadSave.PAUSE_MENU);
        bgWidth = (int) (pauseImgs.getWidth() * Game.GAME_SCALE);
        bgHeight = (int) (pauseImgs.getHeight() * Game.GAME_SCALE);
        bgX = Game.GAME_WIDTH/2 - bgWidth/2;
        bgY = (int) (23 * Game.GAME_SCALE);
    }

    public void update(){
        
        
         menuB.update();
         replayB.update();
         unpauseB.update();
         uiOptions.update();
    }
    public void draw(Graphics g ){
     // background 
       g.drawImage(pauseImgs, bgX, bgY,bgWidth , bgHeight, null);

      
       //urm buttons
       menuB.draw(g);
       replayB.draw(g);
       unpauseB.draw(g);

      

       //ui options
        uiOptions.draw(g);
    }

     
    public void mousePressed(MouseEvent e){
          
            if (isIn(e, menuB)){
            menuB.setMousePressed(true);
           }
           else if (isIn(e, replayB)){
            replayB.setMousePressed(true);
           }
           else if (isIn(e, unpauseB)){
            unpauseB.setMousePressed(true);
           }
           else{
              uiOptions.mousePressed(e);
           }
    }
    
     public void mouseReleased(MouseEvent e){
           
            if (isIn(e, menuB)){
                  if(menuB.isMousePressed()){
                     playing.resetAll();
                     playing.unpauseGame();
                     gameStates.state = gameStates.MENU;
                  }
           }
           else if (isIn(e, replayB)){
                  if(replayB.isMousePressed()){
                    playing.resetAll();
                    playing.unpauseGame();
            }
           }
           else if (isIn(e, unpauseB)){
                   if(unpauseB.isMousePressed()){
                      playing.unpauseGame();
               }
            }
            else{
                uiOptions.mouseReleased(e);
            }
     


          
          
           menuB.reserAllBools();
           replayB.reserAllBools();
           unpauseB.reserAllBools();
           uiOptions.reserAllBools();
     }
     
     public void mouseClicked(MouseEvent e){
       
     }
      
     public void mouseMoved(MouseEvent e){
        
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
       

         
          if (isIn(e, menuB)){
            menuB.setMouseOver(true);
           }
         else if (isIn(e, replayB)){
            replayB.setMouseOver(true);
           }
         else if (isIn(e, unpauseB)){
            unpauseB.setMouseOver(true);
           }
         else{
              uiOptions.mouseMoved(e);
           }
     }

     public void mouseDragged(MouseEvent e ){
         uiOptions.mouseDragged(e);
     }

     private boolean isIn(MouseEvent e , PauseButton pb){
        return pb.getBounds().contains(e.getX(), e.getY());
     }
}
