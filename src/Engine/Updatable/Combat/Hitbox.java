package Engine.Updatable.Combat;

import Engine.Main.Main;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.Updatable;
import Engine.Updatable.UpdatableTypes;

import java.awt.*;

/**
 * Created by m on 5/25/17.
 */
public class Hitbox extends Updatable{

    boolean hit = false;
    int duration;
    Token token;
    Weapon weapon;

    public Hitbox(int x, int y, int duration, Weapon weapon) {
        this.updatableType = UpdatableTypes.HITBOX;
        setPosX(x);
        setPosY(y);
        this.duration = duration;
        this.weapon = weapon;
    }

    private void checkSpace() {
        for(Token t : TokenController.searchRange(this, 2)) {
            if(t.getPosX() == this.getPosX() && t.getPosY() == this.getPosY()) {
                hit = true;
                token = t;
            }
        }
    }

    private long timeThen = System.nanoTime()/10000000;
    private void checkDead() {
        long timeNow = System.nanoTime()/10000000;

        if(timeNow > timeThen+duration) {
            destroy();
        }
    }

    @Override
    public void update() {
        //System.out.println(getPosX() + ", " + getPosY());
        checkSpace();
        checkDead();
    }

    @Override
    public void draw(Graphics2D g) {
        if(Main.debug && token != null) {
            g.setColor(new Color(255, 0, 0));
            g.drawRect(token.getPixelX(), token.getPixelY(), 5, 5);
        }
    }
}
