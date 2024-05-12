package main;

public class Game {
    private Window window;
    private GamePanel gamePanel;
    public Game(){
        gamePanel = new GamePanel();
        window = new Window(gamePanel);
        gamePanel.requestFocus();
      
        
       
    }
}
