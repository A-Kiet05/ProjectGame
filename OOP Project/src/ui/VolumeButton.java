package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import static ultiz.Constant.UI.VolumeButton.*;
import ultiz.loadSave;

public class VolumeButton extends PauseButton {
    private BufferedImage[] volButtons;
    private BufferedImage slider;

    private boolean mouseOver, mousePressed;
    private int index, buttonX, max, min;

    public VolumeButton(int x, int y, int width, int height) {

        super((x + width / 2), y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH / 2;
        this.x = x;
        this.width = width;
        buttonX = (x + width / 2);
        min = x + VOLUME_WIDTH / 2;
        max = x + width - VOLUME_WIDTH / 2;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage imgs = loadSave.GetSpritesAtlas(loadSave.VOLUME_BUTTON);
        volButtons = new BufferedImage[3];
        for (int i = 0; i < volButtons.length; ++i) {
            volButtons[i] = imgs.getSubimage(i * VOLUME_WIDTH_DEFAULT, 0, VOLUME_WIDTH_DEFAULT, VOLUME_HEIGHT_DEFAULT);

            slider = imgs.getSubimage(3 * VOLUME_WIDTH_DEFAULT, 0, SLIDER_WIDTH_DEFAULT, VOLUME_HEIGHT_DEFAULT);
        }

    }

    public void update() {
        index = 0;

        if (mouseOver)
            index = 1;

        if (mousePressed)
            index = 2;

    }

    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(volButtons[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);
    }

    public void changeXPos(int x) {
        if (x < min)
            buttonX = min;
        else if (x > max)
            buttonX = max;
        else
            buttonX = x;

        bounds.x = buttonX - VOLUME_WIDTH / 2;

    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void reserAllBools() {
        mouseOver = false;
        mousePressed = false;
    }

}
