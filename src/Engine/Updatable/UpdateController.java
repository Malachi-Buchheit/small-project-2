package Engine.Updatable;

import Engine.Updatable.Combat.MeleeController;
import Engine.Updatable.Rooms.RoomController;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.UI.UIController;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class UpdateController {

    private static ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();

    public static void addToken(Updatable t) {
        if(t.updatableType == UpdatableTypes.TEXT_BOX)
            textBoxes.add((TextBox) t);
        if(t.updatableType == UpdatableTypes.TOKEN)
            TokenController.addToken((Token) t);
    }

    public static void updateAll() {
        RoomController.updateRoom();
        TokenController.updateTokens();
        MeleeController.updateHitboxes();
        MeleeController.update();
        UIController.updateUI();

        for(int i = 0; i < textBoxes.size(); i++) {
            textBoxes.get(i).update();
        }
    }

    public static void drawAll(BufferStrategy bs) {
        RoomController.drawRoom(bs);
        TokenController.drawTokens(bs);
        MeleeController.drawHitboxes(bs);
        UIController.drawUI(bs);


        for(TextBox t : textBoxes) {
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();

            t.draw(g);
            g.dispose();
        }

        bs.show();
    }

    public static void remove(Updatable item) {
        switch(item.updatableType) {
            case TOKEN:
                TokenController.removeToken((Token) item);
                break;
            case TEXT_BOX:
                textBoxes.remove((TextBox) item);
                break;
            case HITBOX:
                MeleeController.hitB.remove(item);
            default:
                break;
        }
    }
}
