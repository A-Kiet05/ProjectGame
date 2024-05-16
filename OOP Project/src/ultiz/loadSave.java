package ultiz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.awt.Color;
import main.Game;
import main.GamePanel;

public class loadSave {
    private int[][] lvldata ;

    public static final String PLAYER_ATLAS = "/images/player_sprites.png";
    public static final String LEVEL_SPRITES = "/images/outside_sprites.png";
    public static final String LEVEL_ONE = "/images/level_one_data.png";

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
    public static int[][] GetLevelData(){
        int[][] lvldata =  new int[Game.TILES_HEIGHT_DEFAULT][Game.TILES_WIDTH_DEFAULT];
        BufferedImage lvlImgs = loadSave.GetSpritesAtlas(LEVEL_ONE);
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
