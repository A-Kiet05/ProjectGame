package ui;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static ultiz.Constant.UI.UrmButton.*;
import ultiz.loadSave;

public class UrmButton  extends PauseButton{
    private int rowIndex;
    private BufferedImage[] urmImgs;
    private int index ;
    private boolean mouseOver , mousePressed;

    public UrmButton (int x , int y , int width , int height , int rowIndex){
        super(x , y , width , height);
        this.rowIndex = rowIndex;
        loadImgs();
    }

    private void loadImgs(){
        BufferedImage temp = loadSave.GetSpritesAtlas(loadSave.URM_BUTTON);
        urmImgs = new BufferedImage[3];
        for (int i = 0 ; i < urmImgs.length ; ++i){
            urmImgs[i] = temp.getSubimage(i * URM_SIZE_DEFAULT , rowIndex * URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT);
        }
    }


    public void update (){
      index = 0;

      if(mouseOver)
         index = 1;

      if(mousePressed)
         index = 2;
    }

    public void draw(Graphics g){
       g.drawImage(urmImgs[index], x, y, width , height ,null);
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

    public void reserAllBools(){
        mouseOver = false;
        mousePressed= false;
    }
}
