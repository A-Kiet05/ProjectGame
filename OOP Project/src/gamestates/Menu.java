package gamestates;

import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;
import ui.MenuButton;

import ultiz.loadSave;

import java.awt.image.BufferedImage;



public class Menu extends State implements stateMethods {

     private MenuButton[] button = new MenuButton[3];
     private BufferedImage backgroundImg;
     private int menuX , menuY , menuWidth, menuHeight;
    public Menu(Game game){
        super(game);
        loadButtons();
        loadbackgroundImgs();
    }


    private void loadbackgroundImgs(){

      backgroundImg = loadSave.GetSpritesAtlas(loadSave.MENU_BACKGROUND);
      menuWidth = (int) (backgroundImg.getWidth() * game.GAME_SCALE);
      menuHeight = (int) (backgroundImg.getHeight() * game.GAME_SCALE);
      menuX = game.GAME_WIDTH/2 - menuWidth/2;
      menuY = (int) (40 * game.GAME_SCALE);
    }


    private void loadButtons(){
      button[0] = new MenuButton(game.GAME_WIDTH/2, (int) (140 * game.GAME_SCALE), 0, gameStates.PLAYING);
      button[1] = new MenuButton(game.GAME_WIDTH/2, (int) (215 * game.GAME_SCALE), 1, gameStates.OPTIONS);
      button[2] = new MenuButton(game.GAME_WIDTH/2, (int) (290 * game.GAME_SCALE), 2, gameStates.QUIT);
    }

      @Override
    public void update(){
        
        for ( MenuButton mb : button){
          mb.update();
        }
    }

    @Override
    public void draw(Graphics g ){

      g.drawImage(backgroundImg, menuX, menuY, menuWidth ,menuHeight , null);


      for ( MenuButton mb : button){
        mb.draw(g);
      }
    }

    @Override 
    public void mousePressed(MouseEvent e){
      for ( MenuButton mb : button){
        if(isIn(e, mb)){
          mb.setMousePressed(true);
        }
      }
    }

    @Override 
     public void mouseReleased(MouseEvent e){
      for ( MenuButton mb : button){
         if(isIn(e, mb)){
            if(mb.isMousePressed()){
               mb.applyTheState();
              break;
            }
         }
            
      }
       resetAllButton();
     }

     private void resetAllButton(){
      for(MenuButton mb : button){
        mb.resetAllBools();
      }
     }

     @Override 
     public void mouseClicked(MouseEvent e){

     }

     @Override 
     public void mouseMoved(MouseEvent e){
      for (MenuButton mb : button){
        mb.setMouseOver(false);
      }

      for (MenuButton mb : button){
        if(isIn(e, mb)){
            mb.setMouseOver(true);
            break;
        }
      }
     }

     @Override 
     public void keyPressed(KeyEvent e ){
         
     }


     @Override 
     public void keyReleased(KeyEvent e ){

     }

     

}
