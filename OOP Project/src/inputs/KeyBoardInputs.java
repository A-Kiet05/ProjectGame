package inputs;
import main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static ultiz.Constant.Direction;

public class KeyBoardInputs implements KeyListener {
  private GamePanel gamePanel;

  public KeyBoardInputs(GamePanel gamePanel){
    this.gamePanel = gamePanel;
  }
  @Override
  public void keyTyped(KeyEvent e){

  }
  @Override
  public void keyPressed(KeyEvent e){
     switch (e.getKeyCode()){
      case KeyEvent.VK_W:
      gamePanel.setDirection(Direction.UP);
      break;
      case KeyEvent.VK_A:
      gamePanel.setDirection(Direction.LEFT);
      break;
      case KeyEvent.VK_S:
      gamePanel.setDirection(Direction.DOWN);
      break;
      case KeyEvent.VK_D:
      gamePanel.setDirection(Direction.RIGHT);
      break;

     }
  }
  @Override
  public void keyReleased(KeyEvent e ){
    switch (e.getKeyCode()){
      case KeyEvent.VK_W:
      case KeyEvent.VK_A:
      case KeyEvent.VK_S:
      case KeyEvent.VK_D:
      gamePanel.setMoving(false);
  }
  }
    
}
