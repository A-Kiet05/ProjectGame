package ultiz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;

public class loadSave {
  

    public static final String PLAYER_ATLAS = "/images/player_sprites.png";
    public static final String LEVEL_SPRITES = "/images/outside_sprites.png";
    // public static final String LEVEL_ONE = "/images/level_one_data.png";
    // public static final String LEVEL_ONE = "/images/level_one_data_long.png";
    public static final String MENU_BUTTON = "/images/button_atlas.png";
    public static final String MENU_BACKGROUND = "/images/menu_background.png";
    public static final String PAUSE_MENU = "/images/pause_menu.png";
    public static final String SOUND_BUTTON = "/images/sound_button.png";
    public static final String URM_BUTTON = "/images/urm_buttons.png";
    public static final String VOLUME_BUTTON = "/images/volume_buttons.png";
    public static final String BACKGROUND_TREE = "/images/forest.png";
    public static final String PLAYING_BG = "/images/sunset.png";
    public static final String BIG_CLOUDS = "/images/big_clouds.png";
    public static final String SMALL_CLOUDS = "/images/small_clouds.png";
    public static final String CRABBY = "/images/crabby_sprite.png";
    public static final String STATUS_BAR = "/images/health_power_bar.png";
    public static final String COMPLETED_SPRITE = "/images/completed_sprite.png";
    public static final String POTION = "/images/potions_sprites.png";
    public static final String CONTAINER = "/images/objects_sprites.png";
    public static final String TRAPS_ATLAS = "/images/trap_atlas.png";
    public static final String CANNON_ATLAS = "/images/cannon_atlas.png";
    public static final String CANNON_BALL = "/images/ball.png";

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

    public static BufferedImage[] GetAllLevels(){
        URL url = loadSave.class.getResource("/lvls");
        File file  = null;

       try {
          file = new File(url.toURI());
       } catch (URISyntaxException e) {
           e.printStackTrace();
       }
       
       File[] files = file.listFiles();
       File[] fileSorted = new File[files.length];
       for(int i = 0 ; i < fileSorted.length ; ++i){
           for(int j = 0 ; j < files.length ; ++j){
               if(files[j].getName().equals(i + 1 + ".png") ){
                      fileSorted[i] = files[j];
               }
           }
       }

       BufferedImage[] imgs = new BufferedImage[fileSorted.length];
       for(int i = 0 ; i <fileSorted.length ; ++i){
         try {
            imgs[i] = ImageIO.read(fileSorted[i]);
         } catch (Exception e) {
            e.printStackTrace();
         }
       }
       return imgs;
         
       
       
    }


   
   

}
