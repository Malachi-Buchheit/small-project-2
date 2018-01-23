package Engine.Updatable.UI;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Created by m on 5/30/17.
 */
public class UIController {

    static ArrayList<UIElement> elements = new ArrayList<>();

    public static void addElement(UIElement e) {
        elements.add(e);
    }

    public static void updateUI() {
        for(int i = 0; i < elements.size(); i++) {
            elements.get(i).update();
        }
    }

    public static void drawUI(BufferStrategy bs) {
        for(int i = 0; i < elements.size(); i++) {
            Graphics2D g = (Graphics2D)bs.getDrawGraphics();
            elements.get(i).draw(g);
            g.dispose();
        }
    }
}