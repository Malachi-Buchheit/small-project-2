package Engine.Graphics;

import Engine.Updatable.Pieces.Tokens.Token;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Animation {

    private Image[][] frames;
        //frames[4][8]

    private Token owner;
    int direction = 0;
        /*
        0 = DOWN
        1 = RIGHT
        2 = UP
        3 = LEFT
         */

    private byte currentFrame = 0, speed = 1, maxFrames = 4;
    private String animationFolder;


    public Animation(Token t, String animationFolder, int speed) {
        this.owner = t;
        this.speed = (byte) speed;
        this.frames = frames;
        this.animationFolder = animationFolder;
        init();
    }

    public Animation(Token t, String animationFolder, int speed, byte currentFrame) {
        this.owner = t;
        this.speed = (byte) speed;
        this.currentFrame = currentFrame;
        this.animationFolder = animationFolder;
        init();
    }

    private void init() {
        frames = animationLoader();
    }

    long timeThen = System.nanoTime(), timeNow;
    void nextFrame() {
        timeNow = System.nanoTime();
        if(timeThen/100 < timeNow/100 - 5000000/speed) {
            timeThen = timeNow;
            currentFrame++;
            if (currentFrame >= maxFrames)
                currentFrame = 0;
        }
    }

    public Image[][] animationLoader() {
        Image[][] out = new Image[4][maxFrames];
        for(int d = 0; d < 4; d++) {
            for(int f = 0; f < maxFrames; f++) {
                String dir;
                try {
                    switch (d) {
                        case 0:
                            dir = "DOWN";
                            break;
                        case 1:
                            dir = "RIGHT";
                            break;
                        case 2:
                            dir = "UP";
                            break;
                        case 3:
                            dir = "LEFT";
                            break;
                        default:
                            new Exception().printStackTrace();
                            dir = "";
                            break;
                    }
                    out[d][f] = ImageIO.read(new File("res/" + animationFolder + "/" + dir + f + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out;
    }

    public void draw(Graphics2D g) {
        g.drawImage(frames[direction][currentFrame], owner.getPixelX(), owner.getPixelY(), null);
    }

    public void update() {
        switch (owner.direction) {
            case DOWN:
                direction = 0;
                break;
            case RIGHT:
                direction = 1;
                break;
            case UP:
                direction = 2;
                break;
            case LEFT:
                direction = 3;
                break;
            default:
                new Exception().printStackTrace();
                break;
        }
        if(owner.getMoving()) {
            nextFrame();
        } else {
            currentFrame = 0;
        }
    }
}
