package Engine.Updatable.Combat;

import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.UpdateController;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class SpellController {

    public static ArrayList<Hitbox> hitB = new ArrayList<>();

    private static void checkHits() {
        for(Hitbox h : hitB) {
            //System.out.println(h.getPosX());
            if(h.hit) {
                h.token.hurt(h.weapon.damage);
            }
        }
    }

    public static void update() {
        checkHits();
    }

    public static void updateHitboxes() {
        for(int i = 0; i < hitB.size(); i++) {
            hitB.get(i).update();
        }
    }

    public static void drawHitboxes(BufferStrategy bs) {
        for(int i = 0; i < hitB.size(); i++) {
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            hitB.get(i).draw(g);
            g.dispose();
        }
    }
}
