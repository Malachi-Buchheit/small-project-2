package Engine.Updatable.Combat;

import Engine.Updatable.Rooms.Directions;
import Engine.Updatable.UpdateController;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Created by m on 5/25/17.
 */
public class MeleeController {

    public static ArrayList<Hitbox> hitB = new ArrayList<>();

    public static void attack(Directions d, Weapon w) {
        switch (d) {
            case UP:
                hitB.add(new Hitbox(w.t.getPosX(), w.t.getPosY()-1, w.duration, w));
                break;
            case DOWN:
                hitB.add(new Hitbox(w.t.getPosX(), w.t.getPosY()+1, w.duration, w));
                break;
            case LEFT:
                hitB.add(new Hitbox(w.t.getPosX()-1, w.t.getPosY(), w.duration, w));
                break;
            case RIGHT:
                hitB.add(new Hitbox(w.t.getPosX()+1, w.t.getPosY(), w.duration, w));
                break;
            default:
                hitB.add(new Hitbox(0, 0, 0, w));
                break;
        }
        UpdateController.addToken(hitB.get(hitB.size()-1));
        //System.out.println(hitB.size());
    }

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
