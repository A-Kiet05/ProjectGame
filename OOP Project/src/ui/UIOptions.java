package ui;

import main.Game;
import java.awt.Graphics;



import java.awt.event.MouseEvent;
import static ultiz.Constant.UI.SoundButton.*;
import static ultiz.Constant.UI.VolumeButton.*;

public class UIOptions {

    private SoundButton musicButton , sfxButton;
    private VolumeButton volumeButton ; 
    
    public UIOptions(){
         
        loadSoundbutton();
        loadVolumeButtons();
    }

    public void update(){
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }

      private void loadVolumeButtons(){
      int volumeX = (int) (308 * Game.GAME_SCALE);
      int volumeY = (int) (270 *  Game.GAME_SCALE);

      volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void loadSoundbutton(){
        int soundX = (int) (450 * Game.GAME_SCALE);
        int musicY = (int) (140 * Game.GAME_SCALE);
        int sfxY = (int) (187 * Game.GAME_SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    public void draw(Graphics g ){
         //sound button
       musicButton.draw(g);
       sfxButton.draw(g);
      
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
        
           musicButton.resetAllBools();
           sfxButton.resetAllBools();
           
           volumeButton.reserAllBools();
     }
     
     public void mouseClicked(MouseEvent e){
       
     }
      
     public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

         if(isIn(e, musicButton)){
            musicButton.setMouseOver(true);
         }
         else if (isIn(e, sfxButton)){
            sfxButton.setMouseOver(true);
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

     public void reserAllBools(){
        musicButton.resetAllBools();
        sfxButton.resetAllBools();
        volumeButton.reserAllBools();
     }



}
