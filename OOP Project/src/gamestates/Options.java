package gamestates;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_SCALE;
import static main.Game.GAME_WIDTH;
import static ultiz.Constant.UI.UrmButton.URM_SIZE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.UIOptions;
import ui.UrmButton;
import ultiz.loadSave;

public class Options extends State implements stateMethods {

    private Game game;
    private BufferedImage backgrOptionsImgs , optionsImgs;
    private int optionsX , optionsY , optionsW , optionsH;
    private UrmButton menu;
    private UIOptions uiOptions;

    public Options(Game game){
        super(game);
        initBackgroundImgs();
        initOptionsImgs();
        initMenuButton();
        uiOptions = game.geUiOptions();
        

    }

    private void initMenuButton(){
        int menuX = (int) (388 * GAME_SCALE);
        int menuY = (int) (330 * GAME_SCALE);

        menu = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
    }

    private void initBackgroundImgs(){
        backgrOptionsImgs = loadSave.GetSpritesAtlas(loadSave.BACKGROUND_TREE);

    }
    private void initOptionsImgs(){
        optionsImgs = loadSave.GetSpritesAtlas(loadSave.OPTIONS);
        optionsW = (int)(optionsImgs.getWidth() * GAME_SCALE);
        optionsH = (int) (optionsImgs.getHeight() * GAME_SCALE);
        optionsX = GAME_WIDTH/2 - optionsW/2;
        optionsY = (int) (30 * GAME_SCALE);
    }

    @Override 
    public void update(){
        menu.update();
        uiOptions.update();
    }

    @Override 
    public void draw(Graphics g){
        g.drawImage(backgrOptionsImgs, 0, 0,GAME_WIDTH , GAME_HEIGHT ,null);
        g.drawImage(optionsImgs, optionsX, optionsY, optionsW, optionsH,  null);
        //menu
        menu.draw(g);

        //ui options
        uiOptions.draw(g);
    }

    public void mouseDragged(MouseEvent e){
           uiOptions.mouseDragged(e);
    }

    @Override 
    public void mouseMoved(MouseEvent e){
         uiOptions.mouseMoved(e);
         menu.setMouseOver(false);

         if(isIn(e, menu)){
               menu.setMouseOver(true);
         }
    }

    @Override
    public void mousePressed(MouseEvent e){
        uiOptions.mousePressed(e);

        if(isIn(e, menu)){
            menu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
         uiOptions.mouseReleased(e);

         if(isIn(e, menu)){
             if(menu.isMousePressed()){
                 gameStates.state = gameStates.MENU;
             }
         }
    }
     
    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override 
    public void keyPressed(KeyEvent e){
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
             gameStates.state = gameStates.MENU;
         }
    }

    public void keyReleased(KeyEvent e){

    }

    private boolean isIn(MouseEvent e , UrmButton u){
        return u.getBounds().contains(e.getX(), e.getY());
    }

}
