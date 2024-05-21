package Entities;

import static main.Game.GAME_SCALE;
import static ultiz.Constant.Enemy.*;
import static ultiz.helpMethods.*;
import java.awt.Graphics;
import static ultiz.Constant.Direction.*;

abstract class Enemy extends Entity {

    private boolean firstUpdated = true;
    private boolean inAir = false;
    private float fallSpeed;
    private float gravity = 0.04f * GAME_SCALE;
    private float enemySpeed = 0.45f * GAME_SCALE;
    private int walkDir = LEFT;

    private int aniIndex, enemyType, enemyState;
    private int aniTick, aniSpeed = 25;

    public Enemy(int x, int y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;

    }

    public void update(int[][] lvldata) {
        updateMove(lvldata);
        updateAnimationTick();
    }

    public void draw(Graphics g) {

    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetAmountSprites(enemyType, enemyState)) {
                aniIndex = 0;
            }
        }
    }

    public void updateMove(int[][] lvldata) {
        if (firstUpdated) {
            if (!IsEntityOnTheFloor(hitBox, lvldata))
                inAir = true;
            firstUpdated = false;
        }

        if (inAir) {
            if (CanMoveHere(hitBox.x, hitBox.y, hitBox.width, hitBox.height, lvldata)) {
                hitBox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitBox.y = GetYPosAtRoofOrFalling(hitBox, fallSpeed);
            }
        } else {
            switch (enemyState) {
                case IDLE:
                    enemyState = RUNNING;
                    break;
                case RUNNING:
                    float xSpeed = 0;
                    if (walkDir == LEFT) {
                        xSpeed = -enemySpeed;
                    } else {
                        xSpeed = enemySpeed;
                    }

                    if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvldata)) {
                        if (IsFloor(hitBox, xSpeed, lvldata)) {
                            hitBox.x += xSpeed;
                            return;
                        }
                    }
                    changeWalkDir();

                    break;

                default:
                    break;
            }

        }

    }

    private void changeWalkDir() {
        if (walkDir == LEFT) {
            walkDir = RIGHT;
        } else {
            walkDir = LEFT;
        }
    }

    public int getEnemyState() {
        return enemyState;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getAniIndex() {
        return aniIndex;
    }
}
