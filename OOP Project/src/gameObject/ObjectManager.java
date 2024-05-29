package gameObject;

import static main.Game.TILES_SIZE;
import static ultiz.Constant.CANNON_DAMAGE;
import static ultiz.Constant.ObjectConstants.BARREL;
import static ultiz.Constant.ObjectConstants.BLUE_POTION;
import static ultiz.Constant.ObjectConstants.CANNON_HEIGHT;
import static ultiz.Constant.ObjectConstants.CANNON_HEIGHT_DEFAULT;
import static ultiz.Constant.ObjectConstants.CANNON_LEFT;
import static ultiz.Constant.ObjectConstants.CANNON_RIGHT;
import static ultiz.Constant.ObjectConstants.CANNON_WIDTH;
import static ultiz.Constant.ObjectConstants.CANNON_WIDTH_DEFAULT;
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
import static ultiz.Constant.ObjectConstants.SPIKE_HEIGHT;
import static ultiz.Constant.ObjectConstants.SPIKE_WIDTH;
import static ultiz.Constant.Projectiles.CANNON_BALL_HEIGHT;
import static ultiz.Constant.Projectiles.CANNON_BALL_WIDTH;
import static ultiz.helpMethods.CheckCannonsCanShoot;
import static ultiz.helpMethods.CannonBallCollisingToSolid;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Player;
import levels.level;
import gamestates.Playing;
import ultiz.loadSave;

public class ObjectManager {
    private Playing playing;
    
    private BufferedImage[][] potionImgs ,containerImgs ;
    private BufferedImage spikeImgs , cannonballImgs;
    private BufferedImage[]  cannonsImgs;

    private ArrayList<Potion> potions;
    private ArrayList<Container> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<Cannon> cannons;
    private ArrayList<Projectiles> projectiles = new ArrayList<>();

    public ObjectManager(Playing playing){
        this.playing = playing;
        initImgs();

        potions = new ArrayList<>();
        containers = new ArrayList<>();
        spikes = new ArrayList<>();
        cannons = new ArrayList<>();

      

    }

    public void loadObjects(level newLevel){
        potions = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
        spikes = newLevel.getSpikes();
        cannons = newLevel.getCannons();
        projectiles.clear();
    }

    public void update(int[][] lvldata , Player pl ){
        for( Potion p : potions){
            if(p.isActive()){
               p.update();
            }
        }
        for(Container c : containers){
          if(c.isActive()){
            c.update();
          }
        }

       updateCannons(lvldata , pl);
       updateCannonBall(lvldata , pl);
    }

    public boolean playerInRange(Player pl , Cannon cn){
        int absValue= (int) Math.abs(pl.getHitbox().x - cn.gethitBox().x );
        return absValue <= TILES_SIZE * 4;
    }

    private boolean checkPlayerInFrontOf(Player pl , Cannon cn){
        if(cn.getObjectType() == CANNON_LEFT){
            if(pl.getHitbox().x < cn.gethitBox().x)
                return true;
            
        }
        else if(cn.getObjectType() == CANNON_RIGHT){
             if(pl.getHitbox().x > cn.gethitBox().x)
                return true;
             
        }
        return false;
    }

    private void updateCannons(int[][] lvldata, Player pl){
        for(Cannon cn : cannons){
             if(!cn.doAnimation){
                if(cn.getTileY() == pl.getYtile() ){
                    if(playerInRange(pl, cn)){
                        if(checkPlayerInFrontOf(pl , cn)){
                            if(CheckCannonsCanShoot(lvldata , pl.getHitbox() , cn.gethitBox() , cn.getTileY())){
                                //shoot !!
                                cn.setAnimation(true);
                                
                            }
                        }
                    }
                }
             }


            cn.update();
            if(cn.getAniIndex() == 4 && cn.getAniTick() ==0 ){
                shoot(cn);
               
            }
        }

        // if cannons do not animate 
        // ytile == cannon.y
        // player in range 
        // sight clear
        // Size that cannons can shoot
        // shoot !!!
    }

    private void shoot(Cannon cn ){
     

       int dir = 1;
       if(cn.getObjectType() == CANNON_LEFT)
           dir = -1;
        
      projectiles.add(new Projectiles((int)cn.gethitBox().x,(int) cn.gethitBox().y, dir));
       
          
       
    }

    private void updateCannonBall(int[][] lvldata , Player pl){
        for(Projectiles pro : projectiles){
            if(pro.isActive()){
                pro.updatePos();
                if(pro.getHitBox().intersects(pl.getHitbox())){
                    pl.changeCurrentHealth(-CANNON_DAMAGE);
                    pro.setActive(false);
                }
                else if(CannonBallCollisingToSolid(lvldata , pro)){
                    pro.setActive(false);
                }
            }
            
        }
    }

    public void checkTrapsTouched(Player p){
        for(Spike s : spikes){
            if(s.gethitBox().intersects(p.getHitbox())){
                p.hurt();
            }
        }
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
            playing.getPlayer().changePower(BLUE_POTION);
        }
        else if(p.getObjectType() == RED_POTION){
            playing.getPlayer().changeCurrentHealth(RED_POTION_VALUE);
        }

    }

    public void checkObjectGetHit(Rectangle2D.Float attackBox){
       for(Container c : containers){
          if(c.isActive() && !c.doAnimation){
             if(c.gethitBox().intersects(attackBox)){
                 c.setAnimation(true);
                
                 int type = 0 ;
                  if(c.getObjectType() == BARREL){
                     type = 1;
                     potions.add(new Potion((int)(c.gethitBox().x + c.gethitBox().width /2),(int) (c.gethitBox().y + c.gethitBox().height/4), type));
                     return;
                  }
                 
             }
          }

       }
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

        spikeImgs = loadSave.GetSpritesAtlas(loadSave.TRAPS_ATLAS);

        cannonsImgs = new BufferedImage[7];
        BufferedImage temp = loadSave.GetSpritesAtlas(loadSave.CANNON_ATLAS);
        for(int i = 0; i <cannonsImgs.length ; ++i){
             cannonsImgs[i] = temp.getSubimage(i * CANNON_WIDTH_DEFAULT, 0, CANNON_WIDTH_DEFAULT, CANNON_HEIGHT_DEFAULT);
        }

        cannonballImgs = loadSave.GetSpritesAtlas(loadSave.CANNON_BALL);
    }

   

    public void draw(Graphics g , int lvlOffset){
        drawPotions(g , lvlOffset);
        drawContainers(g , lvlOffset);
        drawSpikes(g ,lvlOffset);
        drawCannons(g ,lvlOffset);
        drawCannonBalls(g , lvlOffset);
    }

    public void resetAllObject(){
        loadObjects(playing.getLevelManager().getCurrentLevel());

        for(Potion p: potions){
            p.resetAll();
        }
        for(Container c : containers){
            c.resetAll();
        }
        for(Cannon cn : cannons){
            cn.resetAll();
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

    private void drawSpikes(Graphics g , int lvlOffset ){
        for(Spike s : spikes){
            g.drawImage(spikeImgs,(int)( s.gethitBox().x - lvlOffset),(int) (s.gethitBox().y - s.getYDrawOffset()),SPIKE_WIDTH , SPIKE_HEIGHT ,  null);
        }
    }

    private void drawCannons(Graphics g , int lvlOffset){
        for(Cannon cn : cannons){
            int x = (int) (cn.gethitBox().x - lvlOffset);
            int width = CANNON_WIDTH;
            if(cn.getObjectType() == CANNON_RIGHT){
                x += width;
                width *= -1;
                g.drawImage(cannonsImgs[cn.getAniIndex()], x, (int) cn.gethitBox().y , width , CANNON_HEIGHT, null);
            }
            else{
                g.drawImage(cannonsImgs[cn.getAniIndex()], x, (int) cn.gethitBox().y , width , CANNON_HEIGHT, null);
            }
        }
    }

    private void drawCannonBalls(Graphics g , int lvlOffset){
           for(Projectiles pro : projectiles)
               if(pro.isActive()){
               g.drawImage(cannonballImgs,(int) (pro.getHitBox().x -  lvlOffset),(int) pro.getHitBox().y , CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT, null);
           }
        
    }
}


