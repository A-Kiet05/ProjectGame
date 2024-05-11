package main;

public class Game {
    private Window window;
    private GamePanel gamePanel;
    public Game(){
        gamePanel = new GamePanel();
        window = new Window(gamePanel);
      
        
        System.out.println("i am alive again !");
    }
}
