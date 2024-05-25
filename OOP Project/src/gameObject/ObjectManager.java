package gameObject;

import static ultiz.Constant.ObjectConstants.BARREL;
import static ultiz.Constant.ObjectConstants.BLUE_POTION;
import static ultiz.Constant.ObjectConstants.BOX;
import static ultiz.Constant.ObjectConstants.CONTAINER_HEIGHT;
import static ultiz.Constant.ObjectConstants.CONTAINER_HEIGHT_DEFAULT;
import static ultiz.Constant.ObjectConstants.CONTAINER_WIDTH;
import static ultiz.Constant.ObjectConstants.CONTAINER_WIDTH_DEFAULT;
import static ultiz.Constant.ObjectConstants.POTION_HEIGHT;
import static ultiz.Constant.ObjectConstants.POTION_HEIGHT_DEFAULT;
import static ultiz.Constant.ObjectConstants.POTION_WIDTH;
import static ultiz.Constant.ObjectConstants.POTION_WIDTH_DEFAULT;
import static ultiz.Constant.ObjectConstants.RED_POTION;
import static ultiz.Constant.ObjectConstants.RED_POTION_VALUE;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import levels.level;
import gamestates.Playing;
import ultiz.loadSave;

public class ObjectManager {
    private Playing playing;
    private BufferedImage[][] potionImgs ,containerImgs;
    private ArrayList<Potion> potions;
    private ArrayList<Container> containers;
    public ObjectManager(Playing playing){
        this.playing = playing;
        initImgs();

        potions = new ArrayList<>();
        containers = new ArrayList<>();

        potions.add(new Potion(300, 300, RED_POTION));
        potions.add(new Potion(400, 300, BLUE_POTION));

        containers.add(new Container(500, 300, BOX));
        containers.add(new Container(600, 300, BARREL));

    }

    public void checkObjectTouched(Rectangle2D.Float hitBox){
         for(Potion p :potions){
            if(p.isActive()){
                if(hitBox.intersects(p.gethitBox())){
                     p.setActive(false);
                     applyEffectPlayer(p);
                }
            }
         }
    }

    public void applyEffectPlayer(Potion p){
        if(p.getObjectType() == BLUE_POTION){
            playing.getPlayer().changeEnergy(BLUE_POTION);
        }
        else if(p.getObjectType() == RED_POTION){
            playing.getPlayer().changeCurrentHealth(RED_POTION_VALUE);
        }

    }

    public void checkObjectGetHit(Rectangle2D.Float attackBox){
       for(Container c : containers){
          if(c.isActive()){
             if(c.gethitBox().intersects(attackBox)){
                 c.setAnimation(true);
                 int type = 0 ;
                  if(c.getObjectType() == BARREL){
                     type = 1;
                     potions.add(new Potion((int)(c.gethitBox().x /2),(int) (c.gethitBox().y), type));
                     
                     return;
                  }
             }
          }
       }
    }

    public void loadObjects(level newLevel){
        potions = newLevel.getPotions();
        containers = newLevel.getContainers();
    }

    private void initImgs(){
        BufferedImage imgs = loadSave.GetSpritesAtlas(loadSave.POTION);
        potionImgs = new BufferedImage[2][7];
        for(int j = 0 ; j < potionImgs.length ;++j){
            for(int i = 0 ; i < potionImgs[j].length ; ++i){
                potionImgs[j][i] = imgs.getSubimage(i * POTION_WIDTH_DEFAULT , j *POTION_HEIGHT_DEFAULT, POTION_WIDTH_DEFAULT, POTION_HEIGHT_DEFAULT);
            }
        }

        BufferedImage img = loadSave.GetSpritesAtlas(loadSave.CONTAINER);
        containerImgs = new BufferedImage[2][8];
        for(int j = 0 ; j <containerImgs.length ; ++j){
            for(int i = 0 ; i < containerImgs[j].length ; ++i){
                containerImgs[j][i] = img.getSubimage( i * CONTAINER_WIDTH_DEFAULT, j * CONTAINER_HEIGHT_DEFAULT, CONTAINER_WIDTH_DEFAULT, CONTAINER_HEIGHT_DEFAULT);
            }
        }
    }

    public void update(){
        for( Potion p : potions){
            p.update();
        }
        for(Container c : containers){
            c.update();
        }
    }

    public void draw(Graphics g , int lvlOffset){
        drawPotions(g , lvlOffset);
        drawContainers(g , lvlOffset);
    }

    public void resetAllObject(){
        for(Potion p: potions){
            p.resetAll();
        }
        for(Container c : containers){
            c.resetAll();
        }
    }

    private void drawPotions(Graphics g , int lvlOffset){
        for(Potion p : potions){
            int typePotions = 0;
            if(p.isActive()){

                if(p.getObjectType() == RED_POTION){
                   typePotions = 1;
                    g.drawImage(potionImgs[typePotions][p.getAniIndex()],(int) p.gethitBox().x - p.getXDrawOffset() - lvlOffset, (int) p.gethitBox().y - p.getYDrawOffset() ,POTION_WIDTH , POTION_HEIGHT, null);

                 }
                 else{
                    g.drawImage(potionImgs[typePotions][p.getAniIndex()],(int) p.gethitBox().x - p.getXDrawOffset() - lvlOffset, (int) p.gethitBox().y - p.getYDrawOffset() ,POTION_WIDTH , POTION_HEIGHT, null);
                 }
             
             }
        }
    }

    private void drawContainers(Graphics g , int lvlOffset){
        for(Container c : containers){
            int typeContainers = 0;
            if(c.isActive()){
                if(c.getObjectType() == BARREL){
                    typeContainers = 1;
                    g.drawImage(containerImgs[typeContainers][c.getAniIndex()], (int) c.gethitBox().x - c.getXDrawOffset() - lvlOffset, (int) c.gethitBox().y - c.getYDrawOffset(), CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
                }
                else{
                    g.drawImage(containerImgs[typeContainers][c.getAniIndex()], (int) c.gethitBox().x - c.getXDrawOffset() - lvlOffset, (int) c.gethitBox().y - c.getYDrawOffset(), CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
                }
            }
        }
    }
}


