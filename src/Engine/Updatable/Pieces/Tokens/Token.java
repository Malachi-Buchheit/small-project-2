package Engine.Updatable.Pieces.Tokens;

import Engine.Updatable.Combat.MeleeController;
import Engine.Updatable.Combat.Weapon;
import Engine.Graphics.Animation;
import Engine.Graphics.AnimationMovement;
import Engine.Updatable.Pieces.Piece;
import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.Rooms.RoomController;
import Engine.Updatable.TextBox;
import Engine.Updatable.Updatable;
import Engine.Updatable.UpdatableTypes;
import Engine.Updatable.UpdateController;

import java.awt.*;
import java.util.ArrayList;

public abstract class Token extends Piece {
    //Tokens are game entities that get drawn to the board in a space.

    public Token() {
        super.updatableType = UpdatableTypes.TOKEN;
    }

    protected int spacesPerMove = 1, maxHealth = 1, health = maxHealth, immunity = 10;

    public int objID;

    protected TokenType type;
    protected Weapon weapon = new Weapon(this, 0, 0, 0, 1);

    private long id = System.nanoTime();

    private boolean isTouching(Token t) {
        if(this.getPosX() == t.getPosX() && this.getPosY() == t.getPosY())
            return true;
        return false;
    }

    private long attackThen = System.nanoTime()/10000000;
    public void attack(Directions d) {
        long timeNow = System.nanoTime()/10000000;

        if(weapon != null && timeNow > attackThen + weapon.cooldown) {
            attackThen = System.nanoTime()/10000000;
            MeleeController.attack(d, weapon);
        }
    }

    protected void noise(String url) {
        //SoundController.playSound(url);
    }

    public void draw(Graphics2D g) {
        if(animation == null) {
            g.setColor(color);
            g.setFont(new Font(font, fontWeight, size));
            g.fillRect(getPixelX(), getPixelY(), size, size);
            //g.drawString(icon+"", getPixelX(), getPixelY());
        } else {
            animation.draw(g);
        }
    }

    private long hurtThen = System.nanoTime()/1000000;
    public void hurt(int damage) {
        long timeNow = System.nanoTime()/1000000;

        if(timeNow > hurtThen+immunity) {
            hurtThen = timeNow;
            health -= damage;
        }
    }

    public boolean isAlive() {
        if(health> 0)
            return true;
        return false;
    }

    @Override
    public void move(Directions d, int spaces) {
        super.move(d, spaces);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public TokenType getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void say(Token t, String[] string, Color color, int timespan) {
        UpdateController.addToken(new TextBox(t.getPosX(), t.getPosY(), t, string, color, timespan));
    }

    public void update() {
        super.update();
        if(killable && health <= 0)
            destroy();
    }
}
