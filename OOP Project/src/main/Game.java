package main;

import gamestates.gameStates;


import java.awt.Graphics;
import gamestates.*;

public class Game implements Runnable {
    private Window window;
    private GamePanel gamePanel;
    private Thread gameThread;
    

    private final int FPS_SETTINGS = 120;
    private final int UPS_SETTINGS = 200;

    private Playing playing;
    private Menu menu;


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
        gamePanel.requestFocus();
        gamePanel.setFocusable(true);
        // this line to make GamePanel to pay attention to the interaction along the code
       
        startGameLoop();
     
    }
    private void initClasses(){
        menu = new Menu(this);
        playing = new Playing(this);
    }

    
    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch ( gameStates.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING :
               playing.update();
               break;
        
            default:
                break;
        }
        
         
    }
    public void draw( Graphics g ){
        switch ( gameStates.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING :
                playing.draw(g);
                break;
            case OPTIONS:
            case QUIT :
               System.exit(0);
            default:
                break;
        }
       
       
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
    public void updateWindowFocus(){
       if (gameStates.state == gameStates.PLAYING )
         playing.getPlayer().resetDirection();
       
     }
     public Playing getPlaying(){
        return playing;
     }
     public Menu getMenu(){
        return menu;
     }
}
