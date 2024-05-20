package levels;

import main.Game;
import ultiz.loadSave;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import static main.Game.*;

public class levelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private level levelOne;

    public levelManager(Game game){
        this.game = game;
        importOutsideSprites();
        levelOne = new level(loadSave.GetLevelData());
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
            for (int i = 0; i < levelOne.GetLevelData()[0].length; ++i){
                int index = levelOne.getSpritesIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - lvlOffset , TILES_SIZE * j , TILES_SIZE , TILES_SIZE, null);
            }
        }
      
    }

    public void update(){

    }

    public level getCurrentLevel(){
        return levelOne;
    }


}
