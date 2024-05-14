package main;

public class Game implements Runnable {
    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SETTINGS = 120;

    public Game(){
        gamePanel = new GamePanel();
        window = new Window(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

      
        
       
    }
    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run(){
        double timePerFrames = 1000000000.0/FPS_SETTINGS;
        long lastFrames = System.nanoTime();
        long now = System.nanoTime();
        int frames = 0 ;
        long lastCheck = System.currentTimeMillis();


        while (true) {
             now = System.nanoTime();

             if(now - lastFrames >= timePerFrames){
                gamePanel.repaint();
                lastFrames = now;
                frames++;
             }

             if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS : " + frames);
                frames= 0 ;
            }
            
        }
    }
}
