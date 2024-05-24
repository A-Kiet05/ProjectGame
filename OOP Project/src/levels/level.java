package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Crabby;
import main.Game;

import static main.Game.TILES_SIZE;
import static main.Game.TILES_WIDTH_DEFAULT;
import static ultiz.helpMethods.GetLevelDatas;
import static ultiz.helpMethods.getCrabs;
import static ultiz.helpMethods.GetPlayerSqawn;

public class level {
    private int[][] lvldata;
    private BufferedImage imgs ;
    private ArrayList<Crabby> crabs;

    private int lvlIndex = 0;
     private int lvlTilesWidth ;
    private int maxTilesOffset ;
    private int lvlMaxOffsetX ;
    private Point player;


    public level(BufferedImage imgs){
          this.imgs = imgs;
          initLevels();
          createEnemies();
          calculateLvlOffset(imgs);
          calculatePlayerSqawn();
    }

    private void calculatePlayerSqawn(){
       player = GetPlayerSqawn(imgs);
    }

    private void initLevels(){
        lvldata = GetLevelDatas(imgs);
    }

    private void createEnemies(){
        crabs = getCrabs(imgs);
    }

    private void calculateLvlOffset(BufferedImage imgs){
         lvlTilesWidth = imgs.getWidth();
         maxTilesOffset = lvlTilesWidth - TILES_WIDTH_DEFAULT;
         lvlMaxOffsetX = maxTilesOffset * TILES_SIZE;
    }


    public int getSpritesIndex(int x , int y){
        return lvldata[y][x];
    }

    public int[][] GetLevelData(){
        return lvldata;
    }

    public ArrayList<Crabby> getCrab(){
        return crabs;
    }

    public int getlvlOffset(){
        return lvlMaxOffsetX;
    }

    public Point getPlayerSqawn(){
        return player;
    }
    
}
