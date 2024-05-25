package levels;

import main.Game;
import ultiz.loadSave;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import gamestates.*;

import static main.Game.*;

public class levelManager {
    private Game game;
    private BufferedImage[] levelSprite;
   private ArrayList<level> levels ;
   private int lvlIndex = 0 ;


    public levelManager(Game game){
        this.game = game;
        importOutsideSprites();
        levels = new ArrayList<>();
       createAllLevels();
    }

    public void loadNextLevel(){
        lvlIndex++;
        if(lvlIndex >= levels.size()){
            lvlIndex = 0 ;
            System.out.println("no more level for playing");
            gameStates.state = gameStates.MENU;
        }

        level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().getCrabbies(newLevel);
        game.getPlaying().getPlayer().loadLevelData(newLevel.GetLevelData());
        game.getPlaying().setLvlOffset(newLevel.getlvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    private void createAllLevels(){
        BufferedImage[] allLevels = loadSave.GetAllLevels();
       for( BufferedImage level : allLevels){
            levels.add(new level(level));
       }
    }

    public void importOutsideSprites (){

         BufferedImage img = loadSave.GetSpritesAtlas(loadSave.LEVEL_SPRITES);

         levelSprite = new BufferedImage[48];

         for (int j = 0 ; j < 4 ; ++j){
            for (int i = 0 ; i < 12 ; ++i ){

               int index = j*12 + i;

               levelSprite[index] = img.getSubimage(i*32, j*32, 32,32);
            }
         }
    }

    public void draw (Graphics g , int lvlOffset){

        for(int j = 0 ; j < TILES_HEIGHT_DEFAULT ; ++j){
            for (int i = 0; i < levels.get(lvlIndex).GetLevelData()[0].length; ++i){
                int index = levels.get(lvlIndex).getSpritesIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - lvlOffset , TILES_SIZE * j , TILES_SIZE , TILES_SIZE, null);
            }
        }
      
    }

    public void update(){

    }

    public level getCurrentLevel(){
        return levels.get(lvlIndex);
    }

    public int getAmountOfLevel(){
        return levels.size();
    }


}
