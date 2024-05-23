package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import ultiz.loadSave;
import static ultiz.Constant.Enemy.*;

public class EnemyManager {
    private Playing playing ;
    private BufferedImage[][] crabsImgs;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    public EnemyManager(Playing playing){
      this.playing = playing;
      loadEnemyImgs();
      getCrabbies();
    }

    private void getCrabbies(){
        crabbies = loadSave.getCrabs();
        System.out.println("the number of crabs :" + crabbies.size());
    }

    public void update(int[][] lvldata , Player player){
       for(Crabby c : crabbies){
        if(c.isActive()){
          c.update(lvldata , player);
        }
       }
    }

    public void draw(Graphics g , int lvlOffset ){
        drawCrabs(g,lvlOffset);
    }
    private void drawCrabs(Graphics g , int lvlOffset ){
         for( Crabby c : crabbies){
            if(c.isActive()){
             g.drawImage(crabsImgs[c.getEnemyState()][c.getAniIndex()], (int) (c.getHitbox().x) - lvlOffset - CRAB_DRAWOFFSETX + c.getFLipX() ,(int) (c.getHitbox().y) - CRAB_DRAWOFFSETY, CRAB_WIDTH * c.getFlipW(), CRAB_HEIGHT, null);
            //  c.drawAttackBoxEnemy(g, lvlOffset);
            }
         }
    }

    public void checkEnemyGetHit(Rectangle2D.Float attackBox){
        for(Crabby c: crabbies){
            if(c.isActive()){
            if(attackBox.intersects(c.getHitbox())){
                c.hurt(10);
                return;
                
            }
        }
        }
    }

    public void resetAllEnemies(){
        for( Crabby c : crabbies){
            c.resetEnemy();
        }
    }

    private void loadEnemyImgs(){
        BufferedImage temp = loadSave.GetSpritesAtlas(loadSave.CRABBY);
        crabsImgs = new BufferedImage[5][9];

        for(int j = 0 ; j < crabsImgs.length ; ++j){
           for (int i = 0 ; i < crabsImgs[j].length ; ++i){
               crabsImgs[j][i] = temp.getSubimage(i * CRAB_WIDTH_DEFAULT , j *CRAB_HEIGHT_DEFAULT, CRAB_WIDTH_DEFAULT, CRAB_HEIGHT_DEFAULT);
           }
        }
    }
}
