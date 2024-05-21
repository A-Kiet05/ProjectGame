package ultiz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entities.Crabby;

import static main.Game.TILES_SIZE;
import static ultiz.Constant.Enemy.CRAB;

import java.awt.Color;
import main.Game;
import main.GamePanel;

public class loadSave {
    private int[][] lvldata ;

    public static final String PLAYER_ATLAS = "/images/player_sprites.png";
    public static final String LEVEL_SPRITES = "/images/outside_sprites.png";
    // public static final String LEVEL_ONE = "/images/level_one_data.png";
    public static final String LEVEL_ONE = "/images/level_one_data_long.png";
    public static final String MENU_BUTTON = "/images/button_atlas.png";
    public static final String MENU_BACKGROUND = "/images/menu_background.png";
    public static final String PAUSE_MENU = "/images/pause_menu.png";
    public static final String SOUND_BUTTON = "/images/sound_button.png";
    public static final String URM_BUTTON = "/images/urm_buttons.png";
    public static final String VOLUME_BUTTON = "/images/volume_buttons.png";
    public static final String BACKGROUND_TREE = "/images/forest.png";
    public static final String PLAYING_BG = "/images/day.png";
    public static final String BIG_CLOUDS = "/images/big_clouds.png";
    public static final String SMALL_CLOUDS = "/images/small_clouds.png";
    public static final String CRABBY = "/images/crabby_sprite.png";

    public static BufferedImage GetSpritesAtlas(String fileName){
        BufferedImage img = null;
         InputStream is = loadSave.class.getResourceAsStream(fileName);
       
       try {
          img = ImageIO.read(is);
     } catch ( IOException e) {
        // TODO: handle exception
           e.printStackTrace();
       }finally{
        try {
            is.close();
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
       }
       return img;
    }


    public static ArrayList<Crabby> getCrabs(){
        BufferedImage lvlImgs = loadSave.GetSpritesAtlas(LEVEL_ONE);
        ArrayList<Crabby> crabsList = new ArrayList<>();
       
       
        for (int j = 0 ; j < lvlImgs.getHeight() ; ++j){
            for (int i = 0 ; i < lvlImgs.getWidth() ; ++i){
             Color color = new Color(lvlImgs.getRGB(i, j));
             int value = color.getGreen();
             if (value == CRAB){
                 crabsList.add(new Crabby( i * TILES_SIZE, j*TILES_SIZE));
             }
             
        }
    }
         return crabsList;

    }

    public static int[][] GetLevelData(){
        BufferedImage lvlImgs = loadSave.GetSpritesAtlas(LEVEL_ONE);
        int[][] lvldata =  new int[lvlImgs.getHeight()][lvlImgs.getWidth()];
       
        for (int j = 0 ; j < lvlImgs.getHeight() ; ++j){
            for (int i = 0 ; i < lvlImgs.getWidth() ; ++i){
             Color color = new Color(lvlImgs.getRGB(i, j));
             int value = color.getRed();
             if (value >= 48){
                value = 0 ;
             }
             lvldata[j][i] = value;
        }
    }
    return lvldata;
 }

}
