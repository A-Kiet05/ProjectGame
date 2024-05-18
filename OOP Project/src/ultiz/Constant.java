package ultiz;

public class Constant {
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
