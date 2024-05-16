package main;

public class Game implements Runnable {
    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SETTINGS = 120;
    private final int UPS_SETTINGS = 200;

    public Game(){
        gamePanel = new GamePanel();
        window = new Window(gamePanel);

        gamePanel.setFocusable(true);
        // this line to make GamePanel to pay attention to the interaction along the code
        gamePanel.requestFocus();
        startGameLoop();
     
    }
    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
         gamePanel.updateGame();
        
    }


    @Override
    public void run(){
        double timePerFrames = 1000000000.0/FPS_SETTINGS;
        double timePerUpdates = 1000000000.0/UPS_SETTINGS;

    
        long previousTime = System.nanoTime();
        long currentTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int updates = 0 ;
        int frames = 0 ;
        double deltaU = 0 ;
        double deltaF = 0 ;
        
        while (true) {
            
              currentTime = System.nanoTime();

             deltaU += (currentTime - previousTime) / timePerUpdates;
             deltaF += (currentTime - previousTime) / timePerFrames;
             previousTime = currentTime;

             if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
             }
             if(deltaF >= 1){
                gamePanel.repaint();
                
                frames++;
                deltaF--;
             }


            

             if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS : " + frames + " | UPS : " + updates);
                frames= 0 ;
                updates = 0 ;
            }
            
        }
    }
}
