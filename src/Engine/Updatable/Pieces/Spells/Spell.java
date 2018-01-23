package Engine.Updatable.Pieces.Spells;

import Engine.Graphics.Animation;
import Engine.Updatable.Pieces.Piece;

import java.awt.*;

public class Spell extends Piece {

    protected int basePower = -1, timeSpan = -1, rate = -1, speed = -1;
    Animation animation;
    SpellType spellType;

    @Override
    public void update() {
        switch (spellType) {
            case DAMAGE:
                break;
            case HEAL:
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if(animation != null) animation.draw(g);
        else {
            g.setColor(new Color(250, 30, 40));
            g.fillOval(getPixelX(), getPixelY(), size, size);
        }
    }
}
