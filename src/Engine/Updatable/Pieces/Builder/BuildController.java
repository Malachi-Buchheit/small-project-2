package Engine.Updatable.Pieces.Builder;

import Engine.Updatable.Pieces.Tokens.TokenController;
import Engine.Updatable.UpdateController;

import java.awt.image.BufferStrategy;

/**
 * Created by m on 5/31/17.
 */
public class BuildController {

    private static Builder builder;

    public static void update() {
        builder.update();
    }

    public static void draw(BufferStrategy bs) {
        UpdateController.drawAll(bs);

        /*
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        builder.draw(g);
        g.dispose();
        */
    }

    public static void newBuilder() {
        if(builder == null)
            builder = new Builder(TokenController.getPlayer().getPosX(), TokenController.getPlayer().getPosY());
        else
            builder.setLocation(TokenController.getPlayer().getPosX(), TokenController.getPlayer().getPosY());
        TokenController.forceAddToken(builder);
    }

    public static void removeBuilder() {
        builder.destroy();
    }

    public static Builder getBuilder() {
        return builder;
    }
}
