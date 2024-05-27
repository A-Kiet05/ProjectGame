package gameObject;

import static main.Game.GAME_SCALE;
import static ultiz.Constant.ObjectConstants.CANNON_HEIGHT;
import static ultiz.Constant.ObjectConstants.CANNON_WIDTH;

import java.awt.geom.Rectangle2D;
import static ultiz.Constant.Projectiles.*;

public class Projectiles {
    private Rectangle2D.Float hitBox;
    private int dir;
    private boolean active = true;

    public Projectiles(int x, int y, int dir) {

        int xOffset = (int) (-3 * GAME_SCALE);
        int yOffset = (int) (5 * GAME_SCALE);
        if (dir == 1) {
            xOffset = (int) (29 * GAME_SCALE);
        }
        hitBox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_WIDTH, CANNON_HEIGHT);
        this.dir = dir;
    }

    public void updatePos() {
        hitBox.x += dir * CANNON_SPEED;
    }

    public void setPos(int x, int y) {
        hitBox.x = x;
        hitBox.y = y;

    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

}
