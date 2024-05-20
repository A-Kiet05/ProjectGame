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

    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadImgs();
        loadSoundbutton();
        loadUrmButton();
        loadVolumeButtons();
    }

    private void loadVolumeButtons(){
      int volumeX = (int) (308 * Game.GAME_SCALE);
      int volumeY = (int) (270 *  Game.GAME_SCALE);

      volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
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

    private void loadSoundbutton(){
        int soundX = (int) (450 * Game.GAME_SCALE);
        int musicY = (int) (140 * Game.GAME_SCALE);
        int sfxY = (int) (187 * Game.GAME_SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadImgs(){
        pauseImgs = loadSave.GetSpritesAtlas(loadSave.PAUSE_MENU);
        bgWidth = (int) (pauseImgs.getWidth() * Game.GAME_SCALE);
        bgHeight = (int) (pauseImgs.getHeight() * Game.GAME_SCALE);
        bgX = Game.GAME_WIDTH/2 - bgWidth/2;
        bgY = (int) (23 * Game.GAME_SCALE);
    }

    public void update(){
        
         musicButton.update();
         sfxButton.update();
         menuB.update();
         replayB.update();
         unpauseB.update();
         volumeButton.update();
    }
    public void draw(Graphics g ){
     // background 
       g.drawImage(pauseImgs, bgX, bgY,bgWidth , bgHeight, null);

       //sound button
       musicButton.draw(g);
       sfxButton.draw(g);

       //urm buttons
       menuB.draw(g);
       replayB.draw(g);
       unpauseB.draw(g);

       //volume button
       volumeButton.draw(g);
    }

     
    public void mousePressed(MouseEvent e){
           if(isIn(e, musicButton)){
            musicButton.setMousePressed(true);
           }
           else if (isIn(e, sfxButton)){
            sfxButton.setMousePressed(true);
           }
           else if (isIn(e, menuB)){
            menuB.setMousePressed(true);
           }
           else if (isIn(e, replayB)){
            replayB.setMousePressed(true);
           }
           else if (isIn(e, unpauseB)){
            unpauseB.setMousePressed(true);
           }
           else if (isIn(e, volumeButton)){
            volumeButton.setMousePressed(true);
           }
    }
    
     public void mouseReleased(MouseEvent e){
           if(isIn(e, musicButton)){
              if(musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
              }
           }
           else if (isIn(e, sfxButton)){
                  if(sfxButton.isMousePressed()){
                     sfxButton.setMuted(!sfxButton.isMuted());
                  }
           }
           else if (isIn(e, menuB)){
                  if(menuB.isMousePressed()){
                     gameStates.state = gameStates.MENU;
                  }
           }
           else if (isIn(e, replayB)){
                  if(replayB.isMousePressed()){
                    System.out.println("lvl replay !");
            }
           }
           else if (isIn(e, unpauseB)){
                   if(unpauseB.isMousePressed()){
                      playing.unpauseGame();
            }
     }


          
           musicButton.resetAllBools();
           sfxButton.resetAllBools();
           menuB.reserAllBools();
           replayB.reserAllBools();
           unpauseB.reserAllBools();
           volumeButton.reserAllBools();
     }
     
     public void mouseClicked(MouseEvent e){
       
     }
      
     public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);

         if(isIn(e, musicButton)){
            musicButton.setMouseOver(true);
         }
         else if (isIn(e, sfxButton)){
            sfxButton.setMouseOver(true);
         }
         else if (isIn(e, menuB)){
            menuB.setMouseOver(true);
           }
         else if (isIn(e, replayB)){
            replayB.setMouseOver(true);
           }
         else if (isIn(e, unpauseB)){
            unpauseB.setMouseOver(true);
           }
           else if (isIn(e, volumeButton)){
            volumeButton.setMouseOver(true);
           }
     }

     public void mouseDragged(MouseEvent e ){
          if(volumeButton.isMousePressed()){
              volumeButton.changeXPos(e.getX());
          }
     }

     private boolean isIn(MouseEvent e , PauseButton pb){
        return pb.getBounds().contains(e.getX(), e.getY());
     }
}
