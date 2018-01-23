package Engine.Updatable.UI;

import Engine.Main.Main;
import Engine.Updatable.Pieces.Tokens.Token;
import Engine.Updatable.Pieces.Tokens.TokenType;

import java.awt.*;

/**
 * Created by m on 5/30/17.
 */
public class HealthBar extends UIElement{

    private Token token;
    private int baseWidth, baseHeight, width, height;

    public HealthBar(Token t) {
        token = t;

        if(token.getType() == TokenType.PLAYER) {
            baseWidth = (token.getMaxHealth() * 10);
            baseHeight = 20;
            setPixelX(10);
            setPixelY(Main.getWindow().getBounds().height - baseHeight);
        }
        if(token.getType() == TokenType.BOSS) {
            setPixelX(Main.getWindow().getBounds().width - baseWidth / 2);
            setPixelY(0);
        }

        height = baseHeight-baseHeight/10;
    }

    @Override
    public void update() {
        width = baseWidth - ((token.getMaxHealth()-token.getHealth()))*10;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(100, 100 ,100));
        g.fillRect(getPixelX(), getPixelY(), baseWidth, baseHeight);
        g.setColor(new Color(150, 30, 30));
        g.fillRect(getPixelX(), getPixelY(), width, height);
    }
}
