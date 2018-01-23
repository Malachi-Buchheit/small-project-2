package GameObjects.Tokens;

import Engine.Updatable.Combat.Weapon;
import Engine.Graphics.Animation;
import Engine.Main.Main;
import Engine.Updatable.Rooms.Directions;
import Engine.Main.FileManager;
import Engine.Main.MyKeyListener;
import Engine.Main.MyWindow;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenType;
import Engine.Updatable.UI.HealthBar;
import Engine.Updatable.UI.UIController;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Token {

    MyKeyListener keyListener = MyWindow.keyListener;
    private int nearRange = 10;

    public Player(int x, int y) {
        animation = new Animation(this, "player", 2);
        immunity = 300;
        maxHealth = health = 10;
        objID = 0;
        color = new Color(0, 100, 200);
        icon = 'p';
        speed = 5;
        type = TokenType.PLAYER;
        setPosX(x);
        setPosY(y);
        weapon = new Weapon(this, 1, 10, 10, 80);
        UIController.addElement(new HealthBar(this));
        //noise("scary_song.wav");
    }

    public void controller() {
        if(keyListener.keys[KeyEvent.VK_W])
            move(Directions.UP, spacesPerMove);
        if(keyListener.keys[KeyEvent.VK_A])
            move(Directions.LEFT, spacesPerMove);
        if(keyListener.keys[KeyEvent.VK_D])
            move(Directions.RIGHT, spacesPerMove);
        if(keyListener.keys[KeyEvent.VK_S])
            move(Directions.DOWN, spacesPerMove);
        
        if(keyListener.keys[KeyEvent.VK_Q])
            FileManager.save(FileManager.saveOne);

        if(keyListener.keys[KeyEvent.VK_UP])
            attack(Directions.UP);
        if(keyListener.keys[KeyEvent.VK_DOWN])
            attack(Directions.DOWN);
        if(keyListener.keys[KeyEvent.VK_LEFT])
            attack(Directions.LEFT);
        if(keyListener.keys[KeyEvent.VK_RIGHT])
            attack(Directions.RIGHT);

        if(Main.debug && keyListener.keys[KeyEvent.VK_F1])
            Main.setBuilding(!Main.getBuilding());
    }

    @Override
    public void update() {
        super.update();
        controller();
    }

    @Override
    public void destroy() {
        room.gameOver();
        super.destroy();
    }

    public int getNearRange() {
        return nearRange;
    }
}
