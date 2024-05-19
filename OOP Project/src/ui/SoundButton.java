package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ultiz.loadSave;
import static ultiz.Constant.UI.SoundButton.*;
public class SoundButton extends PauseButton {
    private BufferedImage[][] soundImg;
    private boolean mouseOver , mousePressed;
    private boolean muted;
    private int rowIndex , columnIndex;
    
    public SoundButton(int x , int y , int width , int height){
        super(x , y, width , height);
        loadSoundImgs();
    }

    private void loadSoundImgs(){
        BufferedImage temp = loadSave.GetSpritesAtlas(loadSave.SOUND_BUTTON);
        soundImg = new BufferedImage[2][3];

        for (int j = 0 ; j < soundImg.length ; ++j){
            for (int i = 0 ; i < soundImg[j].length ; ++i){
                soundImg[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    public void update(){

         if(muted)
            rowIndex = 1;
         else
            rowIndex = 0;
         

         columnIndex = 0 ;
         if(mouseOver)
            columnIndex = 1;
         else
            columnIndex = 2;
         
    }

    public void draw(Graphics g ){
       g.drawImage(soundImg[rowIndex][columnIndex], x, y, width , height, null);
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }
    public boolean isMouseOver(){
        return mouseOver;
    }
    public void setMousePressed(boolean mousePressed){
     this.mousePressed = mousePressed;
    }
    public boolean isMousePressed(){
        return mousePressed;
    }
    public void setMuted(boolean muted){
        this.muted =  muted;
    }
    public boolean isMuted(){
        return muted;
    }

    public void resetAllBools(){
        mouseOver = false;
        mousePressed = false;
    }
}
