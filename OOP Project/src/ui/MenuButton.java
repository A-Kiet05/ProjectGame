package ui;

import gamestates.gameStates;
import ultiz.loadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static ultiz.Constant.UI.MenuButton.*;
import java.awt.Rectangle;

public class MenuButton {
    private int xPos , yPos, index;
    private int xOffsetCenter = B_WIDTH/2;
    private gameStates state;
    private int rowIndex;
    private BufferedImage[] img;
    private boolean mouseOver , mousePressed;
    private Rectangle bounds;

    public MenuButton (int xPos , int yPos , int rowIndex , gameStates state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds(){
        bounds = new Rectangle(xPos -xOffsetCenter , yPos , B_WIDTH , B_HEIGHT);
    }

    private void loadImgs(){
      img = new BufferedImage[3];
      BufferedImage temporary = loadSave.GetSpritesAtlas(loadSave.MENU_BUTTON);
      for (int i = 0 ; i < img.length ; ++i){
        img[i] = temporary.getSubimage(i * B_WIDTH_DEFAULT , rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT ,B_HEIGHT_DEFAULT );
      }
    }

    public void draw (Graphics g ){
        g.drawImage(img[index] , xPos - xOffsetCenter , yPos , B_WIDTH , B_HEIGHT , null);
    }

    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
    }
    
    public void applyTheState(){
        gameStates.state = state;
    }

    public void resetAllBools(){
        mouseOver = false;
        mousePressed = false;
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

    public Rectangle getBounds(){
        return bounds;
    }

}
