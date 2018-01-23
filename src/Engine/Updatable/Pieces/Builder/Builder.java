package Engine.Updatable.Pieces.Builder;

import Engine.Main.FileManager;
import Engine.Main.Main;
import Engine.Main.MyKeyListener;
import Engine.Main.MyWindow;
import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.Pieces.Tokens.TokenType;
import Engine.Updatable.UpdateController;
import GameObjects.Tokens.Wall;
import GameObjects.Tokens.Zombie;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by m on 5/31/17.
 */
public class Builder extends Token{

    MyKeyListener keyListener = MyWindow.keyListener;

    public Builder(int x, int y) {
        setPosX(x);
        setPosY(y);

        type = TokenType.BUILDER;

        killable = false;
        passable = true;
        ethereal = true;
        speed = 5;
        color = new Color(255, 0, 0);
        icon = 'B';
    }

    private void save() {
        Scanner scan = new Scanner(System.in);

        Main.setBuilding(false);
        FileManager.save(new File("saves/" + scan.nextLine()));
        Main.setBuilding(true);
    }

    public void controller() {
        if(keyListener.keys[KeyEvent.VK_W] || keyListener.keys[KeyEvent.VK_UP])
            move(Directions.UP, spacesPerMove);
        if(keyListener.keys[KeyEvent.VK_A] || keyListener.keys[KeyEvent.VK_LEFT])
            move(Directions.LEFT, spacesPerMove);
        if(keyListener.keys[KeyEvent.VK_D] || keyListener.keys[KeyEvent.VK_RIGHT])
            move(Directions.RIGHT, spacesPerMove);
        if(keyListener.keys[KeyEvent.VK_S] || keyListener.keys[KeyEvent.VK_DOWN])
            move(Directions.DOWN, spacesPerMove);

        if(keyListener.keys[KeyEvent.VK_Q])
            save();
        if(keyListener.keys[KeyEvent.VK_F1])
            Main.setBuilding(!Main.getBuilding());

        if(keyListener.keys[KeyEvent.VK_SPACE]) {
            ArrayList<Token> t = TokenController.searchRange(this, 1);
            if(t.size() != 0) TokenController.removeToken(t.get(0));
        }
        if(keyListener.keys[KeyEvent.VK_1])
            UpdateController.addToken(new Wall(getPosX(), getPosY()));
        if(keyListener.keys[KeyEvent.VK_2])
            UpdateController.addToken(new Zombie(getPosX(), getPosY()));
    }

    @Override
    public void update() {
        super.update();
        controller();
    }
}
