package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Crabby;
import gameObject.Cannon;
import gameObject.Container;
import gameObject.Potion;
import gameObject.Spike;

import ultiz.helpMethods;

import static main.Game.TILES_SIZE;
import static main.Game.TILES_WIDTH_DEFAULT;
import static ultiz.helpMethods.GetLevelDatas;
import static ultiz.helpMethods.getCrabs;
import static ultiz.helpMethods.GetPlayerSqawn;


public class level {
    private int[][] lvldata;
    private BufferedImage imgs ;
    private ArrayList<Crabby> crabs;
    private ArrayList<Potion> potions;
    private ArrayList<Container> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<Cannon> cannons;

 
    private int lvlTilesWidth ;
    private int maxTilesOffset ;
    private int lvlMaxOffsetX ;
    private Point player;
    


    public level(BufferedImage imgs){
          this.imgs = imgs;
          initLevels();
          createEnemies();
          createPotions();
          createContainer();
          createSpikes();
          createCannons();
          calculateLvlOffset(imgs);
          calculatePlayerSqawn();
    }

    private void createCannons(){
        cannons = helpMethods.getCannons(imgs);
    }

    private void createSpikes(){
        spikes = helpMethods.getSpikes(imgs);
    }

    private void createPotions(){
        potions = helpMethods.getPotions(imgs);
    }

    private void createContainer(){
        containers = helpMethods.getContainers(imgs);
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

    public ArrayList<Potion> getPotions(){
        return potions;
    }
    
    public ArrayList<Container> getContainers(){
        return containers;
    }

    public ArrayList<Spike> getSpikes(){
        return spikes;
    }

    public ArrayList<Cannon> getCannons(){
        return cannons;
    }
    
}
