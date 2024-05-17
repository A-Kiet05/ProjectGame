package ultiz;

import main.Game;
import static main.Game.*;

public class helpMethods {
    private Game game;


    public static boolean CanMoveHere(float x , float y , float  width , float height , int[][] lvldata){
            if(!IsSolid(x, y, lvldata))
              if(!IsSolid(x + width, y, lvldata))
                if(!IsSolid(x, y+ height, lvldata))
                  if(!IsSolid( x + width, y + height, lvldata))
                  return true;
        return false;
    }
    private static boolean IsSolid(float x , float y , int[][] lvldata){

        if(x < 0 || x >= GAME_WIDTH )
        return true;
        if (y < 0 || y >= GAME_HEIGHT)
        return true;

        float xIndex = x/TILES_SIZE;
        float yIndex = y/TILES_SIZE;

        int value = lvldata[(int) yIndex ][(int) xIndex];
        if (value <0 || value >= 48 || value != 11){
            return true;
        }
        return false;
    }
}
