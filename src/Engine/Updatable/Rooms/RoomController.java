package Engine.Updatable.Rooms;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by m on 5/24/17.
 */
public class RoomController {

    public static Room room = new Room();

    public static void updateRoom() {
        room.update();
    }

    public static void drawRoom(BufferStrategy bs) {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        room.draw(g);
        g.dispose();
    }
}
