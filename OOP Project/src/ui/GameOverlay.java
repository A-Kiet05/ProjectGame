package ui;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import gamestates.Playing;
import gamestates.gameStates;
import main.Game;
public class GameOverlay {
    private Playing playing;
    public GameOverlay(Playing playing){
        this.playing = playing ;
    }

    public void draw(Graphics g ){
         g.setColor(new Color(0,0,0,200));
         g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

         g.setColor(Color.white);
         g.drawString("Game Over", Game.GAME_WIDTH/2, 100);
         g.drawString("Please enter escape to go to main menu", Game.GAME_WIDTH/2, 200);
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            playing.resetAll();
            //to reset all of things in the playing class
            gameStates.state = gameStates.MENU;
        }
    }
}
