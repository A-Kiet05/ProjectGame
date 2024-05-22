package ultiz;

import main.Game;
import static main.Game.*;

import java.awt.geom.Rectangle2D;

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
       int maxWidth = lvldata[0].length * Game.TILES_SIZE;
        if(x < 0 || x >= maxWidth )
        return true;
        if (y < 0 || y >= GAME_HEIGHT)
        return true;

        float xIndex = x/TILES_SIZE;
        float yIndex = y/TILES_SIZE;

      return IsTileSolid((int)xIndex , (int)yIndex , lvldata);
    }
    public static boolean IsTileSolid(int xTile , int yTile ,int[][] lvldata ){
        int value = lvldata[ yTile ][xTile];
        if (value <0 || value >= 48 || value != 11){
            return true;
        }
        return false;
    }
    public static int GetXPosCollide(Rectangle2D.Float hitBox , float xSpeed ){
        int currentTiles = (int) (hitBox.x /TILES_SIZE);
        if( xSpeed > 0 ){
        //right
        int tileXPos = currentTiles * TILES_SIZE;
        int xOffset = (int) (TILES_SIZE - hitBox.width);
        return tileXPos +  xOffset - 1;
        }
        else{
            //left
            return currentTiles * TILES_SIZE;
        }

    }

    public static int GetYPosAtRoofOrFalling(Rectangle2D.Float hitBox , float airSpeed){
        int currentTiles = (int) (hitBox.y /TILES_SIZE);
        if(airSpeed >0){
            //falling 
            int tileYPos = currentTiles * TILES_SIZE;
            int yOffset = (int) (TILES_SIZE - hitBox.height);
            return tileYPos + yOffset - 1;
        }
        else{
            //jumping
            return currentTiles * TILES_SIZE;
        }
    }

    public static boolean IsEntityOnTheFloor(Rectangle2D.Float hitBox , int[][] lvldata ){
        if(!IsSolid(hitBox.x , hitBox.y + hitBox.height + 1, lvldata))
          if(!IsSolid(hitBox.x + hitBox.width , hitBox.y + hitBox.height +1 , lvldata))
             return false;
        return true;
    }

    public static boolean IsFloor(Rectangle2D.Float hitBox  , float xSpeed ,int[][] lvldata ){
        return IsSolid(hitBox.x + xSpeed, hitBox.y + hitBox.height + 1, lvldata);
    }

    public static boolean WalkableTiles(int starts , int ends , int y , int[][] lvldata){
        for( int i = 0 ; i < (starts - ends); ++i){
            if(IsTileSolid(ends +1, y, lvldata)){
                return false;
            }
            if(!IsTileSolid(ends + 1, y + 1 , lvldata)){
                return false;
            }
        }
        
        return true;
       
    }

    public static boolean IsSightClear(int[][] lvldata ,Rectangle2D.Float firstHitbox , Rectangle2D.Float secondHitbox , int ytile ){
        int firstTileX = (int) ( firstHitbox.x / Game.TILES_SIZE);
        int secondTileX = (int) (secondHitbox.x / Game.TILES_SIZE);

        if(firstTileX > secondTileX)
           return WalkableTiles(firstTileX , secondTileX , ytile , lvldata);
      
        else
           return WalkableTiles(secondTileX, firstTileX, ytile, lvldata);
            
        
      
    
    }
}
