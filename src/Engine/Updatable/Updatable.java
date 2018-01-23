package Engine.Updatable;

import Engine.Updatable.Rooms.Room;
import Engine.Updatable.Rooms.RoomController;

import java.awt.*;

/**
 * Created by m on 5/22/17.
 */
public abstract class Updatable {
    private int posX = 0, posY = 0;
    protected Room room;
    protected UpdatableTypes updatableType;

    public int getPixelX() {
        return posX;
    }

    public int getPixelY() {
        return posY;
    }

    public void setPixelX(int x) {
        posX = x;
    }

    public void setPixelY(int y) {
        posY = y;
    }

    public int getPosX() {
        return posX/RoomController.room.TILE_WIDTH/RoomController.room.SCALE;
    }

    public int getPosY() {
        return posY/RoomController.room.TILE_WIDTH/RoomController.room.SCALE;
    }

    public void setPosX(int x) {
        posX = x*RoomController.room.TILE_WIDTH*RoomController.room.SCALE;
    }

    public void setPosY(int y) {
        posY = y*RoomController.room.TILE_WIDTH*RoomController.room.SCALE;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g);
    public void destroy() {
        UpdateController.remove(this);
    }
}
