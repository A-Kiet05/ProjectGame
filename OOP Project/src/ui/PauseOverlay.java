package ui;
import java.awt.image.BufferedImage;


import java.awt.event.MouseEvent;

import main.Game;
import ultiz.loadSave;
import java.awt.Graphics;
import static ultiz.Constant.UI.SoundButton.*;

public class PauseOverlay {

    private BufferedImage pauseImgs;
    private int bgX , bgY , bgWidth , bgHeight;
    private SoundButton musicButton , sfxButton;

    public PauseOverlay(){
        loadImgs();
        loadSoundbutton();
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
    }
    public void draw(Graphics g ){
     // background 
       g.drawImage(pauseImgs, bgX, bgY,bgWidth , bgHeight, null);

       //sound button
       musicButton.draw(g);
       sfxButton.draw(g);
    }

     
    public void mousePressed(MouseEvent e){
           if(isIn(e, musicButton)){
            musicButton.setMousePressed(true);
           }
           else if (isIn(e, sfxButton)){
            sfxButton.setMousePressed(true);
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
           musicButton.resetAllBools();
           sfxButton.resetAllBools();
     }
     
     public void mouseClicked(MouseEvent e){
       
     }
      
     public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);

         if(isIn(e, musicButton)){
            musicButton.setMouseOver(true);
         }
         else if (isIn(e, sfxButton)){
            sfxButton.setMouseOver(true);
         }
     }

     public void mouseDragged(MouseEvent e ){

     }

     private boolean isIn(MouseEvent e , PauseButton pb){
        return pb.getBounds().contains(e.getX(), e.getY());
     }
}
