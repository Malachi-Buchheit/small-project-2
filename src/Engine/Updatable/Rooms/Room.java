package Engine.Updatable.Rooms;

import Engine.Updatable.Updatable;

import java.awt.*;

/**
 * Created by m on 5/24/17.
 */
public class Room extends Updatable {

    private Color color = new Color(200, 200, 200);

    public static final int TILE_WIDTH = 8;
    public static final int SCALE = 2;

    public int width, height;

    public Room() {
        width = 50;
        height = 45;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, width*TILE_WIDTH*SCALE, height*TILE_WIDTH*SCALE);
        g.dispose();
    }

    @Override
    public void destroy() {
        //Destroy all game objects in room
    }

    public void gameOver() {
        color = new Color(250, 50, 50);
    }
}
