package ultiz;

import static main.Game.GAME_SCALE;
import static main.Game.GAME_WIDTH;

import org.w3c.dom.html.HTMLIsIndexElement;

import main.Game;
import main.GamePanel;

public class Constant {

    public static final float GRAVITY = 0.04f * GAME_SCALE;
    public static final int aniSpeed = 25;

    public static class ObjectConstants{
        public static final int RED_POTION = 0;
        public static final int BLUE_POTION = 1;
        public static final int BARREL = 2;
        public static final int BOX = 3;

        public static final int RED_POTION_VALUE = 15;
        public static final int BLUE_POTION_VALUE = 10;
        
        public static final int CONTAINER_WIDTH_DEFAULT = 30;
        public static final int CONTAINER_HEIGHT_DEFAULT = 40;
        public static final int CONTAINER_HEIGHT = (int) (CONTAINER_HEIGHT_DEFAULT * GAME_SCALE);
        public static final int CONTAINER_WIDTH = (int) (CONTAINER_WIDTH_DEFAULT * GAME_SCALE);

        public static final int POTION_WIDTH_DEFAULT = 12;
        public static final int POTION_HEIGHT_DEFAULT = 16;
        public static final int POTION_WIDTH = (int) (POTION_WIDTH_DEFAULT * GAME_SCALE);
        public static final int POTION_HEIGHT =(int) (POTION_HEIGHT_DEFAULT * GAME_SCALE);

        public static int GetAmountSprites(int objType){
            switch (objType) {
                case BLUE_POTION , RED_POTION:
                    return 7;
                case BARREL , BOX:
                    return 8;
            }
            return 0;
        }
        
        
    }

    public static class Enemy{
        public static final int CRAB = 0;

        public static final int IDLE = 0 ;
        public static final int RUNNING = 1 ;
        public static final int ATTACKING = 2 ;
        public static final int HIT = 3 ;
        public static final int DEAD = 4 ;

        public static final int CRAB_WIDTH_DEFAULT = 72;
        public static final int CRAB_HEIGHT_DEFAULT = 32;

        public static final int CRAB_WIDTH = (int) (CRAB_WIDTH_DEFAULT * GAME_SCALE);
        public static final int CRAB_HEIGHT = (int) (CRAB_HEIGHT_DEFAULT * GAME_SCALE);

        public static final int CRAB_DRAWOFFSETX = (int) (26 * GAME_SCALE);
        public static final int CRAB_DRAWOFFSETY = (int) (9 * GAME_SCALE);

        public static int GetAmountSprites(int enemyType , int enemyState){
            switch(enemyType){
                case CRAB:
                 switch (enemyState) {
                    case IDLE:
                        return 9;
                        
                    case RUNNING :
                        return 6;
                        
                    case ATTACKING :
                        return 7;
                        
                    case HIT :
                        return 4;
                    case DEAD :
                        return 5;
                 
                    
                 }
                
            }
            return 0;
        }

        public static int getMaxHealth(int enemyType){
            switch(enemyType){
                case CRAB:
                 return 10;
                
                default:
                return 1;
            }
        }

        public static int getDmgHealth(int enemyType){
            switch(enemyType){

                case CRAB:
                  return -15;
                  

                default:
                  return 0;

            }
        }
    }
    
    public static class Environment{
        public static final int BIG_CLOUDS_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUDS_HEIGHT_DEFAULT = 101;
        public static final int SMALL_CLOUDS_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUDS_HEIGHT_DEFAULT = 24;

        public static final int BIG_CLOUDS_WIDTH = (int) (BIG_CLOUDS_WIDTH_DEFAULT * Game.GAME_SCALE);
        public static final int BIG_CLOUDS_HEIGHT = (int) ( BIG_CLOUDS_HEIGHT_DEFAULT * Game.GAME_SCALE);
        public static final int SMALL_CLOUDS_WIDTH = (int) ( SMALL_CLOUDS_WIDTH_DEFAULT * Game.GAME_SCALE);
        public static final int SMALL_CLOUDS_HEIGHT = (int) ( SMALL_CLOUDS_HEIGHT_DEFAULT * Game.GAME_SCALE);
    }

    public static class UI{
        public static class MenuButton{
            
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.GAME_SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.GAME_SCALE);
        }

        public static class SoundButton{
            public static final int SOUND_SIZE_DEFAULT = 40;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.GAME_SCALE);
        }
        
        public static class UrmButton {
            public static final int URM_SIZE_DEFAULT = 56;
            public static final int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.GAME_SCALE);
        }

        public static class VolumeButton{
            public static final int VOLUME_WIDTH_DEFAULT = 28;
            public static final int VOLUME_HEIGHT_DEFAULT = 44;
            public static final int SLIDER_WIDTH_DEFAULT = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_WIDTH_DEFAULT * GAME_SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_HEIGHT_DEFAULT * GAME_SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_WIDTH_DEFAULT * GAME_SCALE);

        }
    }
    public static class Direction {
      public static final int UP = 0;
      public static final int DOWN = 1;
      public static final int LEFT = 2;
      public static final int RIGHT = 3;

        
    }
    public static class playerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
       
        
        
        public static final int ATTACKING = 4;
        public static final int HURT = 5;
        public static final int DEAD = 6;
        
    
        public static int GetAmountSprites( int player_action){

              switch (player_action) {
                case IDLE:
                    return 5;
                   
                case RUNNING :
                    return 6;
                case JUMP :
                    return 3;
                case ATTACKING :
                
                return 3;

                
               case HURT:

               
                return 4;
                case DEAD:
                return 8;
               
                
               

               case FALLING :
               default:
                return 1;
                   
              }
        }
    }
}
