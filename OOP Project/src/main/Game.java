package main;
import Entities.*;
import levels.levelManager;

import java.awt.Graphics;

public class Game implements Runnable {
    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;
    private levelManager levelmanager;

    private final int FPS_SETTINGS = 120;
    private final int UPS_SETTINGS = 200;


    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float GAME_SCALE = 1.5f;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * GAME_SCALE);
    public final static int TILES_WIDTH_DEFAULT = 26;
    public final static int TILES_HEIGHT_DEFAULT = 14;
    public final static int GAME_WIDTH = (TILES_SIZE * TILES_WIDTH_DEFAULT);
    public final static int GAME_HEIGHT = (TILES_SIZE * TILES_HEIGHT_DEFAULT);
    

    public Game(){
        
        initClasses();
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);

        gamePanel.setFocusable(true);
        // this line to make GamePanel to pay attention to the interaction along the code
        gamePanel.requestFocus();
        startGameLoop();
     
    }

    private void initClasses(){

        levelmanager = new levelManager(this);
        player = new Player (200, 200 , (int) (64*GAME_SCALE) ,(int) (40*GAME_SCALE));
        player.loadLevelData(levelmanager.getCurrentLevel().GetLevelData());

    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        levelmanager.update();
         player.update();
         
    }
    public void draw( Graphics g ){
        levelmanager.draw(g);
        player.render(g);
       
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
    public Player getPlayer(){
        return player;
    }
}
