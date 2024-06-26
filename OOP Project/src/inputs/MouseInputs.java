package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.gameStates;
import main.GamePanel;



public class MouseInputs implements MouseListener , MouseMotionListener {
    private GamePanel gamePanel;
    public MouseInputs ( GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    @Override
    public void mouseDragged(MouseEvent e){
       switch (gameStates.state) {
        case PLAYING:
            gamePanel.getGame().getPlaying().mouseDragged(e);
            break;
        case OPTIONS:
            gamePanel.getGame().getOptions().mouseDragged(e);
            break;
       
        default:
            break;
       }
    }
    @Override
    public void mouseMoved(MouseEvent e ){
        switch (gameStates.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING :
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().mouseMoved(e);
                break;
            default:
                break;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        switch (gameStates.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseClicked(e);
                break;
            case PLAYING :
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().mouseClicked(e);
                break;
            default:
                break;
        }
    }
    @Override
    public void mousePressed(MouseEvent e ){
        switch (gameStates.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING :
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case OPTIONS :
                gamePanel.getGame().getOptions().mousePressed(e);
                break;
            default:
                break;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e){
        switch (gameStates.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING :
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case OPTIONS:
                gamePanel.getGame().getOptions().mouseReleased(e);
                break;
            default:
                break;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e){

    }
}
