package ultiz;

import static main.Game.GAME_SCALE;

import main.Game;

public class Constant {
    
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
        public static final int HURT = 4;
        public static final int GROUND = 5;
        
        public static final int ATTACKING = 6;
        public static final int JUMP_ATTACK_1 = 7;
        public static final int JUMP_ATTACK_2 = 8 ;
        public static final int BACKGROUND = 9;

    
        public static int GetAmountSprites( int player_action){

              switch (player_action) {
                case IDLE:
                    return 5;
                   
                case RUNNING :
                    return 6;
                case JUMP :
                    return 3;
                case ATTACKING :
                case JUMP_ATTACK_1:
                case JUMP_ATTACK_2:
                return 3;

                
               case GROUND:
                return 2;
               
                
               case HURT:
               return 2;

               case FALLING :
               default:
                return 1;
                   
              }
        }
    }
}
